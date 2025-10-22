package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Validador otimizado que usa metadados para evitar loops.
 * <p>
 * Garante 100% de equivalência com a lógica original de validação de processos,
 * verificando um conjunto de regras de negócio para determinar se um documento
 * pode ser adicionado a um processo.
 * </p>
 *
 * @author Bruno da Silva Rocha, Frederico Marques da Silva Barcelos
 * @version 1.0
 */
public class ValidadorDeProcesso {

    /**
     * Verifica se a adição de um novo documento a um processo é válida.
     *
     * @param meta Os metadados do processo existente.
     * @param novoDoc O novo documento a ser adicionado.
     * @return {@code true} se a adição for válida; {@code false} caso contrário.
     */
    public boolean ehAdicaoValida(ProcessoMetadata meta, Documento novoDoc) {
        return getRazaoDeFalha(meta, novoDoc) == null;
    }

    /**
     * Obtém a razão pela qual um documento não pode ser adicionado a um processo.
     * <p>
     * Este método testa uma série de regras de negócio em sequência. A primeira
     * regra violada é retornada como uma String. Se todas as regras forem satisfeitas,
     * o método retorna {@code null}.
     * </p>
     *
     * @param meta Os metadados do processo existente.
     * @param novoDoc O novo documento a ser validado.
     * @return Uma {@code String} descrevendo a primeira regra violada, ou {@code null} se a adição for válida.
     */
    public String getRazaoDeFalha(ProcessoMetadata meta, Documento novoDoc) {
        String razao = null;

        // Regra 1: Páginas
        if (meta.paginas + novoDoc.getPaginas() > 250) {
            razao = "Excede o limite de 250 páginas";
        }

        // Regra 2: Mistura de Cursos
        if (razao == null) {
            boolean novoEhPos = (novoDoc.getCodigoCurso() == CodigoCurso.POS_GRADUACAO_ENGENHARIA ||
                                novoDoc.getCodigoCurso() == CodigoCurso.POS_GRADUACAO_ENGENHARIA_ELETRICA ||
                                novoDoc.getCodigoCurso() == CodigoCurso.POS_GRADUACAO_ENGENHARIA_SOFTWARE);
            if ((meta.temGraduacao && novoEhPos) || (meta.temPosGraduacao && !novoEhPos)) {
                razao = "Viola regra de mistura de cursos (Graduação/Pós)";
            }
        }

        // Regra 3: Tipos de Documentos
        if (razao == null) {
            boolean novoEhAdmin = novoDoc instanceof DocumentoAdministrativo;
            boolean novoEhAcad = novoDoc instanceof DocumentoAcademico;
            if ((meta.temAdmin && novoEhAcad) || (meta.temAcad && novoEhAdmin)) {
                razao = "Viola regra de mistura de tipos (Administrativo/Acadêmico)";
            }
        }

        // Regra 4: Documento Substancial (deve estar sozinho no processo)
        if (razao == null) {
            boolean novoEhSubstancial = (novoDoc instanceof Edital || novoDoc instanceof Portaria) && novoDoc.getPaginas() >= 100 && ((Norma) novoDoc).getValido();
            if ((novoEhSubstancial && meta.docCount > 0) || meta.temDocumentoSubstancial) {
                razao = "Viola regra de documento substancial (deve estar sozinho)";
            }
        }

        // Regra 5: Diplomas (se houver, todos os docs devem ser Diploma, Certificado ou Ata)
        if (razao == null) {
            boolean novoEhDiploma = novoDoc instanceof Diploma;
            boolean novoEhRelacionadoADiploma = novoDoc instanceof Diploma || novoDoc instanceof Certificado || novoDoc instanceof Ata;
            if ((meta.temDiploma && !novoEhRelacionadoADiploma) || (novoEhDiploma && meta.temDocumentoNaoRelacionadoADiploma)) {
                razao = "Viola regra de Diplomas (documento incompatível adicionado a processo com diploma)";
            }
        }

        // Regra 6: Atestados (todos devem ter a mesma categoria, se não for nula)
        if (razao == null) {
            if (meta.atestadosIncompativeis) {
                razao = "Viola regra de Atestados (categorias incompatíveis já existem no processo)";
            } else if (novoDoc instanceof Atestado) {
                String catNova = ((Atestado) novoDoc).getCategoria();
                if (catNova != null && meta.categoriaAtestadoUnica != null && !meta.categoriaAtestadoUnica.equals(catNova)) {
                    razao = "Viola regra de Atestados (nova categoria '" + catNova + "' é incompatível com a existente '" + meta.categoriaAtestadoUnica + "')";
                }
            }
        }

        // Regra 7: Ofícios e Circulares (devem ter pelo menos um destinatário em comum)
        if (razao == null) {
            if (meta.oficiosSemIntersecao) {
                razao = "Viola regra de Ofícios/Circulares (interseção de destinatários já é vazia no processo)";
            } else if (novoDoc instanceof Oficio || novoDoc instanceof Circular) {
                if (meta.oficiosECircularesCount > 0 && meta.destinatariosComuns != null && !meta.destinatariosComuns.isEmpty()) {
                    Set<String> destNovo = new HashSet<>();
                    if (novoDoc instanceof Oficio) {
                        Oficio oficio = (Oficio) novoDoc;
                        if (oficio.getDestinatario() != null) destNovo.add(oficio.getDestinatario());
                    } else {
                        Circular circular = (Circular) novoDoc;
                        if (circular.getDestinatarios() != null) destNovo.addAll(Arrays.asList(circular.getDestinatarios()));
                    }

                    if (!destNovo.isEmpty()) {
                        Set<String> intersecao = new HashSet<>(meta.destinatariosComuns);
                        intersecao.retainAll(destNovo);
                        if (intersecao.isEmpty()) {
                            razao = "Viola regra de Ofícios/Circulares (novo documento não compartilha destinatários)";
                        }
                    }
                }
            }
        }

        return razao;
    }
}
