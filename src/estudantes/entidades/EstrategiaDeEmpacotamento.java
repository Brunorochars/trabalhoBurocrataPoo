package estudantes.entidades;

import professor.entidades.Mesa;
import professor.entidades.Processo;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Implementa a estratégia de empacotamento de documentos em processos.
 * <p>
 * A estratégia "Best-Fit com Menores Primeiro" é utilizada:
 * <ol>
 *   <li>Os documentos são ordenados por prioridade e, em seguida, por tamanho (menor para maior).</li>
 *   <li>Cada documento é então colocado no processo que já está mais cheio, mas que ainda pode aceitá-lo sem violar nenhuma regra.</li>
 * </ol>
 * Esta abordagem visa criar processos o mais densos possível, maximizando a eficiência.
 * </p>
 *
 * @author Bruno da Silva Rocha, Frederico Marques da Silva Barcelos
 * @version 1.0
 */
public class EstrategiaDeEmpacotamento {
    private final List<Documento> documentosDisponiveis;
    private final ValidadorDeProcesso validador = new ValidadorDeProcesso();
    private final Map<Processo, ProcessoMetadata> metadados;
    private final Mesa mesa;

    /**
     * Construtor da estratégia de empacotamento.
     *
     * @param documentos A lista de documentos a serem processados.
     * @param mesa A mesa de trabalho do burocrata, contendo os processos.
     */
    public EstrategiaDeEmpacotamento(List<Documento> documentos, Mesa mesa) {
        this.documentosDisponiveis = documentos;
        this.mesa = mesa;
        this.metadados = new HashMap<>();
        for (Processo p : mesa.getProcessos()) {
            if (p != null) {
                metadados.put(p, new ProcessoMetadata(p));
            }
        }
    }

    /**
     * Executa o algoritmo de empacotamento.
     * <p>
     * Itera sobre os documentos ordenados e tenta alocá-los no melhor processo
     * disponível na mesa. Documentos alocados são removidos da lista de disponíveis.
     * </p>
     */
    public void empacotar() {
        ordenarDocumentos();

        Iterator<Documento> docIterator = documentosDisponiveis.iterator();
        while (docIterator.hasNext()) {
            Documento doc = docIterator.next();
            Processo melhorProcesso = null;
            int maxPaginas = -1;

            // Encontra o melhor processo (o mais cheio que ainda aceita o documento)
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

            // Se um processo válido foi encontrado, adiciona o documento a ele
            if (melhorProcesso != null) {
                ProcessoMetadata meta = metadados.get(melhorProcesso);
                melhorProcesso.adicionarDocumento(doc);
                meta.atualizarCom(doc); // Atualiza o cache de metadados
                docIterator.remove();
            }
        }
    }

    /**
     * Ordena os documentos disponíveis de acordo com a estratégia.
     * A prioridade é por tipo de documento e, em seguida, pelo número de páginas em ordem crescente.
     */
    private void ordenarDocumentos() {
        documentosDisponiveis.sort(Comparator
                .comparing(this::getPrioridadeTipoDocumento)
                .thenComparing(Documento::getPaginas));
    }

    /**
     * Atribui uma prioridade numérica a cada tipo de documento para fins de ordenação.
     * @param doc O documento a ser avaliado.
     * @return Um valor inteiro representando a prioridade (menor é mais prioritário).
     */
    private int getPrioridadeTipoDocumento(Documento doc) {
        if ((doc instanceof Edital || doc instanceof Portaria) && doc.getPaginas() >= 100 && ((Norma) doc).getValido())
            return 0;
        if (doc instanceof Diploma) return 1;
        if (doc instanceof Atestado) return 2;
        if (doc instanceof Oficio || doc instanceof Circular) return 3;
        return 4;
    }

    /**
     * Obtém a lista de documentos que não foram alocados em nenhum processo.
     * @return Uma lista de documentos não utilizados.
     */
    public List<Documento> getDocumentosNaoUtilizados() {
        return documentosDisponiveis;
    }
}
