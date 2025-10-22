package estudantes.entidades;

import professor.entidades.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Armazena o estado agregado de um processo para otimizar a validação de regras de negócio.
 *
 * <p>Esta classe serve como um cache de informações sobre um processo, permitindo
 * que a validade da adição de um novo documento seja verificada rapidamente,
 * sem a necessidade de iterar sobre todos os documentos já existentes no processo
 * a cada verificação.</p>
 *
 * @author Bruno da Silva Rocha, Frederico Marques da Silva Barcelos
 * @version 1.0
 * @see Processo
 */
public class ProcessoMetadata {
    /**
     * A soma do número de páginas de todos os documentos no processo.
     */
    public int paginas = 0;
    /**
     * O número total de documentos no processo.
     */
    public int docCount = 0;
    /**
     * Indica se o processo contém documentos de cursos de graduação.
     */
    public boolean temGraduacao = false;
    /**
     * Indica se o processo contém documentos de cursos de pós-graduação.
     */
    public boolean temPosGraduacao = false;
    /**
     * Indica se o processo contém documentos do tipo Administrativo.
     */
    public boolean temAdmin = false;
    /**
     * Indica se o processo contém documentos do tipo Acadêmico.
     */
    public boolean temAcad = false;
    /**
     * Indica se o processo contém um documento substancial (Edital ou Portaria com >= 100 páginas).
     */
    public boolean temDocumentoSubstancial = false;
    /**
     * Indica se o processo contém pelo menos um Diploma.
     */
    public boolean temDiploma = false;
    /**
     * Indica se o processo com diploma contém algum documento que não seja Diploma, Certificado ou Ata.
     */
    public boolean temDocumentoNaoRelacionadoADiploma = false;
    /**
     * Armazena a categoria do primeiro Atestado adicionado, para garantir que todos os outros tenham a mesma.
     */
    public String categoriaAtestadoUnica = null;
    /**
     * Indica se foram adicionados Atestados com categorias diferentes.
     */
    public boolean atestadosIncompativeis = false;
    /**
     * O número de Ofícios e Circulares no processo.
     */
    public int oficiosECircularesCount = 0;
    /**
     * O conjunto de destinatários comuns a todos os Ofícios e Circulares no processo.
     */
    public Set<String> destinatariosComuns = null;
    /**
     * Indica se a interseção de destinatários entre Ofícios e Circulares se tornou vazia.
     */
    public boolean oficiosSemIntersecao = false;

    /**
     * Constrói e calcula os metadados iniciais a partir de um processo existente.
     *
     * @param p O processo existente para o qual os metadados serão gerados.
     */
    public ProcessoMetadata(Processo p) {
        for (Documento doc : p.pegarCopiaDoProcesso()) {
            this.atualizarCom(doc);
        }
    }

    /**
     * Atualiza os metadados do processo com a adição de um novo documento.
     *
     * <p>Este método recalcula todos os campos de metadados com base nas
     * propriedades do novo documento adicionado.</p>
     *
     * @param doc O novo documento a ser incorporado nos metadados.
     */
    public void atualizarCom(Documento doc) {
        this.paginas += doc.getPaginas();
        this.docCount++;

        CodigoCurso codigo = doc.getCodigoCurso();
        if (codigo == CodigoCurso.POS_GRADUACAO_ENGENHARIA ||
            codigo == CodigoCurso.POS_GRADUACAO_ENGENHARIA_ELETRICA ||
            codigo == CodigoCurso.POS_GRADUACAO_ENGENHARIA_SOFTWARE) {
            this.temPosGraduacao = true;
        } else {
            this.temGraduacao = true;
        }

        if (doc instanceof DocumentoAdministrativo) this.temAdmin = true;
        if (doc instanceof DocumentoAcademico) this.temAcad = true;

        if ((doc instanceof Edital || doc instanceof Portaria) && doc.getPaginas() >= 100 && ((Norma) doc).getValido()) {
            this.temDocumentoSubstancial = true;
        }

        if (doc instanceof Diploma) this.temDiploma = true;
        if (!(doc instanceof Diploma || doc instanceof Certificado || doc instanceof Ata)) {
            this.temDocumentoNaoRelacionadoADiploma = true;
        }

        if (doc instanceof Atestado) {
            String cat = ((Atestado) doc).getCategoria();
            if (cat != null) {
                if (this.categoriaAtestadoUnica == null) {
                    this.categoriaAtestadoUnica = cat;
                } else if (!this.categoriaAtestadoUnica.equals(cat)) {
                    this.atestadosIncompativeis = true;
                }
            }
        }

        if (doc instanceof Oficio || doc instanceof Circular) {
            this.oficiosECircularesCount++;
            if (this.oficiosSemIntersecao) return;

            Set<String> destinatariosAtuais = new HashSet<>();
            if (doc instanceof Oficio) {
                Oficio oficio = (Oficio) doc;
                if (oficio.getDestinatario() != null) destinatariosAtuais.add(oficio.getDestinatario());
            } else {
                Circular circular = (Circular) doc;
                if (circular.getDestinatarios() != null) destinatariosAtuais.addAll(Arrays.asList(circular.getDestinatarios()));
            }

            if (this.destinatariosComuns == null) {
                this.destinatariosComuns = destinatariosAtuais;
            } else if (!destinatariosAtuais.isEmpty()) {
                this.destinatariosComuns.retainAll(destinatariosAtuais);
                if (this.destinatariosComuns.isEmpty()) {
                    this.oficiosSemIntersecao = true;
                }
            }
        }
    }
}
