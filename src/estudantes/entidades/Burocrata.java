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

        EstrategiaDeEmpacotamento estrategia = new EstrategiaDeEmpacotamento(documentosParaProcessar, this.mesa);
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




}