package estudantes.entidades;

import professor.entidades.*;

import java.util.*;

/**
 * Classe que traz a lógica do algoritmo de organização e despacho de processos.
 * <br><br>
 * Revertido para uma estratégia estável e de estresse zero. A abordagem é
 * "document-centric first-fit": os documentos são ordenados por prioridade e tamanho,
 * e cada um é colocado no primeiro processo compatível encontrado. A validação
 * é feita inspecionando a lista real de documentos para garantir 100% de correção
 * nas regras e evitar estresse.
 *
 * @author Bruno da Silva Rocha, Frederico de Oliveira
 */
public class Burocrata {
    private int estresse = 0;
    private final Mesa mesa;
    private final Universidade universidade;

    public Burocrata(Mesa m, Universidade u) {
        this.mesa = m;
        this.universidade = u;
    }

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

    public void estressarMuito() {
        estresse += 10;
    }

    public void estressar() {
        estresse++;
    }

    public int getEstresse() {
        return this.estresse;
    }

    /**
     * Metadados de um processo para otimizar a validação de forma correta.
     * Esta classe armazena o estado agregado de um processo para que as regras
     * de validação possam ser checadas sem iterar sobre todos os documentos.
     */
    private static class ProcessoMetadata {
        int paginas = 0;
        int docCount = 0;
        boolean temGraduacao = false;
        boolean temPosGraduacao = false;
        boolean temAdmin = false;
        boolean temAcad = false;
        boolean temDocumentoSubstancial = false;
        boolean temDiploma = false;
        boolean temDocumentoNaoRelacionadoADiploma = false;
        String categoriaAtestadoUnica = null;
        boolean atestadosIncompativeis = false; // Flag para o caso de conflito interno de atestados
        int oficiosECircularesCount = 0;
        Set<String> destinatariosComuns = null;
        boolean oficiosSemIntersecao = false; // Flag para o caso de ofícios sem destinatário comum

        /**
         * Construtor que calcula os metadados a partir de um processo existente.
         */
        ProcessoMetadata(Processo p) {
            for (Documento doc : p.pegarCopiaDoProcesso()) {
                this.atualizarCom(doc);
            }
        }

        /**
         * Atualiza os metadados com um novo documento.
         */
        void atualizarCom(Documento doc) {
            this.paginas += doc.getPaginas();
            this.docCount++;

            // Regra de mistura de cursos
            CodigoCurso codigo = doc.getCodigoCurso();
            if (codigo == CodigoCurso.POS_GRADUACAO_ENGENHARIA ||
                codigo == CodigoCurso.POS_GRADUACAO_ENGENHARIA_ELETRICA ||
                codigo == CodigoCurso.POS_GRADUACAO_ENGENHARIA_SOFTWARE) {
                this.temPosGraduacao = true;
            } else {
                this.temGraduacao = true;
            }

            // Regra de tipos de documentos
            if (doc instanceof DocumentoAdministrativo) this.temAdmin = true;
            if (doc instanceof DocumentoAcademico) this.temAcad = true;

            // Regra de documento substancial
            if ((doc instanceof Edital || doc instanceof Portaria) && doc.getPaginas() >= 100 && ((Norma) doc).getValido()) {
                this.temDocumentoSubstancial = true;
            }

            // Regra de Diplomas
            if (doc instanceof Diploma) this.temDiploma = true;
            if (!(doc instanceof Diploma || doc instanceof Certificado || doc instanceof Ata)) {
                this.temDocumentoNaoRelacionadoADiploma = true;
            }

            // Regra de Atestados
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

            // Regra de Ofícios e Circulares
            if (doc instanceof Oficio || doc instanceof Circular) {
                this.oficiosECircularesCount++;
                if (this.oficiosSemIntersecao) return; // Se já falhou, não há o que fazer.

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

    /**
     * Validador otimizado que usa metadados para evitar loops,
     * garantindo 100% de equivalência com a lógica original.
     */
    private static class ValidadorDeProcesso {

        public boolean ehAdicaoValida(ProcessoMetadata meta, Documento novoDoc) {
            // Regra 1: Páginas
            if (meta.paginas + novoDoc.getPaginas() > 250) return false;

            // Regra 2: Mistura de Cursos
            boolean novoEhPos = (novoDoc.getCodigoCurso() == CodigoCurso.POS_GRADUACAO_ENGENHARIA ||
                                novoDoc.getCodigoCurso() == CodigoCurso.POS_GRADUACAO_ENGENHARIA_ELETRICA ||
                                novoDoc.getCodigoCurso() == CodigoCurso.POS_GRADUACAO_ENGENHARIA_SOFTWARE);
            if ((meta.temGraduacao && novoEhPos) || (meta.temPosGraduacao && !novoEhPos)) return false;

            // Regra 3: Tipos de Documentos
            boolean novoEhAdmin = novoDoc instanceof DocumentoAdministrativo;
            boolean novoEhAcad = novoDoc instanceof DocumentoAcademico;
            if ((meta.temAdmin && novoEhAcad) || (meta.temAcad && novoEhAdmin)) return false;

            // Regra 4: Documento Substancial (deve estar sozinho no processo)
            boolean novoEhSubstancial = (novoDoc instanceof Edital || novoDoc instanceof Portaria) && novoDoc.getPaginas() >= 100 && ((Norma) novoDoc).getValido();
            if ((novoEhSubstancial && meta.docCount > 0) || meta.temDocumentoSubstancial) return false;

            // Regra 5: Diplomas (se houver, todos os docs devem ser Diploma, Certificado ou Ata)
            boolean novoEhDiploma = novoDoc instanceof Diploma;
            boolean novoEhRelacionadoADiploma = novoDoc instanceof Diploma || novoDoc instanceof Certificado || novoDoc instanceof Ata;
            if ((meta.temDiploma && !novoEhRelacionadoADiploma) || (novoEhDiploma && meta.temDocumentoNaoRelacionadoADiploma)) return false;

            // Regra 6: Atestados (todos devem ter a mesma categoria, se não for nula)
            if (meta.atestadosIncompativeis) return false;
            if (novoDoc instanceof Atestado) {
                String catNova = ((Atestado) novoDoc).getCategoria();
                if (catNova != null && meta.categoriaAtestadoUnica != null && !meta.categoriaAtestadoUnica.equals(catNova)) {
                    return false;
                }
            }

            // Regra 7: Ofícios e Circulares (devem ter pelo menos um destinatário em comum)
            if (meta.oficiosSemIntersecao) return false;
            if (novoDoc instanceof Oficio || novoDoc instanceof Circular) {
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
                        if (intersecao.isEmpty()) return false;
                    }
                }
            }

            return true;
        }
    }

    /**
     * Estratégia de empacotamento otimizada com cache de metadados.
     */
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
                for (Processo processo : mesa.getProcessos()) {
                    if (processo == null) continue;

                    ProcessoMetadata meta = metadados.get(processo);
                    if (validador.ehAdicaoValida(meta, doc)) {
                        processo.adicionarDocumento(doc);
                        meta.atualizarCom(doc); // Atualiza o cache de metadados
                        docIterator.remove();
                        break;
                    }
                }
            }
        }

        private void ordenarDocumentos() {
            documentosDisponiveis.sort(Comparator
                    .comparing(this::getPrioridadeTipoDocumento)
                    .thenComparing(Documento::getPaginas, Comparator.reverseOrder()));
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