package estudantes.entidades;

import professor.entidades.*;
import java.util.*;

/**
 * Representa o Burocrata, responsável por organizar documentos em processos e despachá-los.
 *
 * <p>O Burocrata aplica uma estratégia de empacotamento para agrupar documentos
 * de acordo com um conjunto de regras de negócio. Ele interage com a Mesa para
 * acessar os processos e com a Universidade para obter os documentos e despachar
 * os processos finalizados.</p>
 *
 * @author Bruno da Silva Rocha, Frederico Marques da Silva Barcelos
 * @version 3.0
 * @see Mesa
 * @see Universidade
 * @see Processo
 * @see Documento
 */
public class Burocrata {
    private int estresse = 0;
    private final Mesa mesa;
    private final Universidade universidade;

    /**
     * Construtor da classe Burocrata.
     *
     * @param m A mesa de trabalho do burocrata, onde os processos são mantidos.
     * @param u A instância da universidade, de onde os documentos são retirados e para onde os processos são enviados.
     */
    public Burocrata(Mesa m, Universidade u) {
        this.mesa = m;
        this.universidade = u;
    }

    /**
     * Executa o ciclo de trabalho principal do burocrata, orquestrando o processamento de documentos.
     *
     * <p>A operação é dividida em quatro etapas principais:</p>
     * <ol>
     *   <li><b>Coleta:</b> Todos os documentos de todos os montes de cursos na universidade são recolhidos para processamento.</li>
     *   <li><b>Empacotamento:</b> Uma estratégia de empacotamento é utilizada para tentar alocar os documentos recolhidos nos processos vazios ou parcialmente preenchidos que estão na mesa, com base em um conjunto de regras de validação.</li>
     *   <li><b>Despacho:</b> Após o empacotamento, todos os processos na mesa que contêm pelo menos um documento são despachados para a universidade para validação final.</li>
     *   <li><b>Devolução:</b> Quaisquer documentos que não puderam ser alocados em nenhum processo durante a etapa de empacotamento são devolvidos aos seus montes de curso de origem.</li>
     * </ol>
     * <p>Este método representa um ciclo completo da lógica de negócio do burocrata.</p>
     */
    public void trabalhar() {
        List<Documento> documentosParaProcessar = new ArrayList<>();
        for (CodigoCurso codigo : CodigoCurso.values()) {
            Documento[] monte = universidade.pegarCopiaDoMonteDoCurso(codigo);
            for (Documento doc : monte) {
                universidade.removerDocumentoDoMonteDoCurso(doc, doc.getCodigoCurso());
                documentosParaProcessar.add(doc);
            }
        }

        if (documentosParaProcessar.isEmpty()) {
            return;
        }

        EstrategiaDeEmpacotamento estrategia = new EstrategiaDeEmpacotamento(documentosParaProcessar);
        estrategia.empacotar();

        for (Processo p : mesa.getProcessos()) {
            if (p != null && p.pegarCopiaDoProcesso().length > 0) {
                universidade.despachar(p);
            }
        }

        for (Documento doc : estrategia.getDocumentosNaoUtilizados()) {
            universidade.devolverDocumentoParaMonteDoCurso(doc, doc.getCodigoCurso());
        }
    }

    /**
     * Aumenta o nível de estresse do burocrata em 10 pontos.
     * <p>Este método é invocado em situações de erro grave no processamento.</p>
     */
    public void estressarMuito() {
        estresse += 10;
    }

    /**
     * Aumenta o nível de estresse do burocrata em 1 ponto.
     * <p>Este método é invocado quando ocorrem erros leves ou inconsistências.</p>
     */
    public void estressar() {
        estresse++;
    }

    /**
     * Retorna o nível de estresse atual do burocrata.
     *
     * @return O valor inteiro do estresse acumulado.
     */
    public int getEstresse() {
        return this.estresse;
    }

    private static class ValidadorDeProcesso {

        public boolean ehAdicaoValida(ProcessoMetadata meta, Documento novoDoc) {
            return getRazaoDeFalha(meta, novoDoc) == null;
        }

        public String getRazaoDeFalha(ProcessoMetadata meta, Documento novoDoc) {
            String razao = null;

            if (meta.paginas + novoDoc.getPaginas() > 250) {
                razao = "Excede o limite de 250 páginas";
            }

            if (razao == null) {
                boolean novoEhPos = (novoDoc.getCodigoCurso() == CodigoCurso.POS_GRADUACAO_ENGENHARIA ||
                                    novoDoc.getCodigoCurso() == CodigoCurso.POS_GRADUACAO_ENGENHARIA_ELETRICA ||
                                    novoDoc.getCodigoCurso() == CodigoCurso.POS_GRADUACAO_ENGENHARIA_SOFTWARE);
                if ((meta.temGraduacao && novoEhPos) || (meta.temPosGraduacao && !novoEhPos)) {
                    razao = "Viola regra de mistura de cursos (Graduação/Pós)";
                }
            }

            if (razao == null) {
                boolean novoEhAdmin = novoDoc instanceof DocumentoAdministrativo;
                boolean novoEhAcad = novoDoc instanceof DocumentoAcademico;
                if ((meta.temAdmin && novoEhAcad) || (meta.temAcad && novoEhAdmin)) {
                    razao = "Viola regra de mistura de tipos (Administrativo/Acadêmico)";
                }
            }

            if (razao == null) {
                boolean novoEhSubstancial = (novoDoc instanceof Edital || novoDoc instanceof Portaria) && novoDoc.getPaginas() >= 100 && ((Norma) novoDoc).getValido();
                if ((novoEhSubstancial && meta.docCount > 0) || meta.temDocumentoSubstancial) {
                    razao = "Viola regra de documento substancial (deve estar sozinho)";
                }
            }

            if (razao == null) {
                boolean novoEhDiploma = novoDoc instanceof Diploma;
                boolean novoEhRelacionadoADiploma = novoDoc instanceof Diploma || novoDoc instanceof Certificado || novoDoc instanceof Ata;
                if ((meta.temDiploma && !novoEhRelacionadoADiploma) || (novoEhDiploma && meta.temDocumentoNaoRelacionadoADiploma)) {
                    razao = "Viola regra de Diplomas (documento incompatível adicionado a processo com diploma)";
                }
            }

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

    private class EstrategiaDeEmpacotamento {
        private final List<Documento> documentosDisponiveis;
        private final ValidadorDeProcesso validador = new ValidadorDeProcesso();
        private final Map<Processo, ProcessoMetadata> metadados;

        public EstrategiaDeEmpacotamento(List<Documento> documentos) {
            this.documentosDisponiveis = documentos;
            this.metadados = new HashMap<>();
            for (Processo p : mesa.getProcessos()) {
                if (p != null) {
                    metadados.put(p, new ProcessoMetadata(p));
                }
            }
        }

        public void empacotar() {
            ordenarDocumentos();

            Iterator<Documento> docIterator = documentosDisponiveis.iterator();
            while (docIterator.hasNext()) {
                Documento doc = docIterator.next();
                Processo melhorProcesso = null;
                int maxPaginas = -1;

                for (Processo processo : mesa.getProcessos()) {
                    if (processo == null) continue;

                    ProcessoMetadata meta = metadados.get(processo);
                    if (validador.ehAdicaoValida(meta, doc)) {
                        if (meta.paginas > maxPaginas) {
                            maxPaginas = meta.paginas;
                            melhorProcesso = processo;
                        }
                    }
                }

                if (melhorProcesso != null) {
                    ProcessoMetadata meta = metadados.get(melhorProcesso);
                    melhorProcesso.adicionarDocumento(doc);
                    meta.atualizarCom(doc);
                    docIterator.remove();
                }
            }
        }

        private void ordenarDocumentos() {
            documentosDisponiveis.sort(Comparator
                    .comparing(this::getPrioridadeTipoDocumento)
                    .thenComparing(Documento::getPaginas));
        }

        private int getPrioridadeTipoDocumento(Documento doc) {
            if ((doc instanceof Edital || doc instanceof Portaria) && doc.getPaginas() >= 100 && ((Norma) doc).getValido())
                return 0;
            if (doc instanceof Diploma) return 1;
            if (doc instanceof Atestado) return 2;
            if (doc instanceof Oficio || doc instanceof Circular) return 3;
            return 4;
        }

        public List<Documento> getDocumentosNaoUtilizados() {
            return documentosDisponiveis;
        }
    }
}