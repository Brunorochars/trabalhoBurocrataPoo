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
     * Validador que inspeciona a lista real de documentos para garantir 100% de correção.
     */
    private static class ValidadorDeProcesso {

        public boolean ehAdicaoValida(Processo processo, Documento novoDoc) {
            Documento[] docsAtuais = processo.pegarCopiaDoProcesso();

            int paginasAtuais = 0;
            for (Documento d : docsAtuais) {
                paginasAtuais += d.getPaginas();
            }
            if (paginasAtuais + novoDoc.getPaginas() > 250) {
                return false;
            }

            List<Documento> docsFuturos = new ArrayList<>(docsAtuais.length + 1);
            docsFuturos.addAll(Arrays.asList(docsAtuais));
            docsFuturos.add(novoDoc);

            return !violaRegraMisturaCursos(docsFuturos) &&
                    !violaRegraTiposDocumentos(docsFuturos) &&
                    !violaRegraDocumentoSubstancial(docsFuturos) &&
                    !violaRegraDiplomas(docsFuturos) &&
                    !violaRegraAtestados(docsFuturos) &&
                    !violaRegraOficiosCirculares(docsFuturos);
        }

        private boolean violaRegraMisturaCursos(List<Documento> docs) {
            boolean temGraduacao = false;
            boolean temPosGraduacao = false;
            for (Documento doc : docs) {
                CodigoCurso codigo = doc.getCodigoCurso();
                if (codigo == CodigoCurso.POS_GRADUACAO_ENGENHARIA ||
                    codigo == CodigoCurso.POS_GRADUACAO_ENGENHARIA_ELETRICA ||
                    codigo == CodigoCurso.POS_GRADUACAO_ENGENHARIA_SOFTWARE) {
                    temPosGraduacao = true;
                } else {
                    temGraduacao = true;
                }
            }
            return temGraduacao && temPosGraduacao;
        }

        private boolean violaRegraTiposDocumentos(List<Documento> docs) {
            boolean temAdmin = false;
            boolean temAcad = false;
            for (Documento doc : docs) {
                if (doc instanceof DocumentoAdministrativo) temAdmin = true;
                if (doc instanceof DocumentoAcademico) temAcad = true;
            }
            return temAdmin && temAcad;
        }

        private boolean violaRegraDocumentoSubstancial(List<Documento> docs) {
            if (docs.size() <= 1) return false;
            for (Documento doc : docs) {
                if ((doc instanceof Edital || doc instanceof Portaria) && doc.getPaginas() >= 100 && ((Norma) doc).getValido()) {
                    return true;
                }
            }
            return false;
        }

        private boolean violaRegraDiplomas(List<Documento> docs) {
            boolean temDiploma = false;
            for (Documento doc : docs) {
                if (doc instanceof Diploma) {
                    temDiploma = true;
                    break;
                }
            }
            if (!temDiploma) return false;

            for (Documento doc : docs) {
                if (!(doc instanceof Diploma || doc instanceof Certificado || doc instanceof Ata)) {
                    return true;
                }
            }
            return false;
        }

        private boolean violaRegraAtestados(List<Documento> docs) {
            String categoria = null;
            for (Documento doc : docs) {
                if (doc instanceof Atestado) {
                    Atestado atestado = (Atestado) doc;
                    if (categoria == null) {
                        categoria = atestado.getCategoria();
                    } else if (atestado.getCategoria() != null && !categoria.equals(atestado.getCategoria())) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean violaRegraOficiosCirculares(List<Documento> docs) {
            List<Documento> oficiosECirculares = new ArrayList<>();
            for(Documento doc : docs){
                if(doc instanceof Oficio || doc instanceof Circular){
                    oficiosECirculares.add(doc);
                }
            }

            if (oficiosECirculares.size() <= 1) return false;

            Set<String> destinatariosComuns = null;
            for (Documento doc : oficiosECirculares) {
                Set<String> destinatariosAtuais = new HashSet<>();
                if (doc instanceof Oficio) {
                    Oficio oficio = (Oficio) doc;
                    if (oficio.getDestinatario() != null) {
                        destinatariosAtuais.add(oficio.getDestinatario());
                    }
                } else if (doc instanceof Circular) {
                    Circular circular = (Circular) doc;
                    if (circular.getDestinatarios() != null) {
                        destinatariosAtuais.addAll(Arrays.asList(circular.getDestinatarios()));
                    }
                }

                if (destinatariosComuns == null) {
                    destinatariosComuns = new HashSet<>(destinatariosAtuais);
                } else {
                    destinatariosComuns.retainAll(destinatariosAtuais);
                }
            }
            return destinatariosComuns == null || destinatariosComuns.isEmpty();
        }
    }

    /**
     * Estratégia de empacotamento que usa o validador híbrido.
     */
    private class EstrategiaDeEmpacotamento {
        private final List<Documento> documentosDisponiveis;
        private final ValidadorDeProcesso validador = new ValidadorDeProcesso();

        public EstrategiaDeEmpacotamento(List<Documento> documentos) {
            this.documentosDisponiveis = documentos;
        }

        public void empacotar() {
            ordenarDocumentos();

            Iterator<Documento> docIterator = documentosDisponiveis.iterator();
            while (docIterator.hasNext()) {
                Documento doc = docIterator.next();
                for (Processo processo : mesa.getProcessos()) {
                    if (processo == null) continue;

                    if (validador.ehAdicaoValida(processo, doc)) {
                        processo.adicionarDocumento(doc);
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
            if ((doc instanceof Edital || doc instanceof Portaria) && doc.getPaginas() >= 100 && ((Norma)doc).getValido()) return 0;
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