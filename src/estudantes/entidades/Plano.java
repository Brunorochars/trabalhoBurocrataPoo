package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Arrays;
import java.util.Objects;
/**
 * Representa um plano acadêmico, que herda as propriedades básicas de um {@link DocumentoAcademico}.
 * <p>Além das propriedades de um documento acadêmico, um Plano possui um responsável e um planejamento detalhado.</p>
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.1
 * @see DocumentoAcademico 
 * @see professor.entidades.CodigoCurso
 */
public class Plano extends DocumentoAcademico{
    private String responsavel;
    private String[] planejamento;
    /**
     * Construtor da classe Plano.
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual o Plano está associado.
     * @param paginas O número de páginas que o documento possui.
     * @param autenticacao O número de autenticação do Plano.
     * @param responsavel O nome do responsável pelo Plano.
     * @param planejamento A lista de atividades ou tópicos planejados no Plano.
     */
    public Plano(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao,String responsavel, String[] planejamento) {
        super(criador, codigoCurso, paginas, autenticacao);
        this.responsavel = responsavel;
        this.planejamento = planejamento;
    }
    /**
     * Obtém o nome do responsável pelo Plano.
     * @return A {@code String} com o nome do responsável.
     */
    public String getResponsavel() {
        return responsavel;
    }
    /**
     * Altera o nome do responsável pelo Plano.
     * @param responsavel O novo nome do responsável.
     */
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    /**
     * Obtém a lista de atividades ou tópicos planejados no Plano.
     * @return Um array de {@code String} com o planejamento.
     */
    public String[] getPlanejamento() {
        return planejamento;
    }
    /**
     * Altera a lista de atividades ou tópicos planejados no Plano.
     * @param planejamento O novo array de {@code String} do planejamento.
     */
    public void setPlanejamento(String[] planejamento) {
        this.planejamento = planejamento;
    }
    /**
     * Verifica se este Plano é igual a outro objeto.
     * @param obj O objeto a ser comparado com este Plano.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        if (!(super.equals(obj))) return false;
        Plano planoTestado = (Plano) obj;
        return this.responsavel.equals(planoTestado.responsavel) && DocumentoUtils.arraysDeStringSaoIguais(planoTestado.planejamento, this.planejamento);
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link DocumentoAcademico},
     * bem como no responsável e planejamento do Plano.</p>
     *
     * @return Um valor de código hash para este Plano.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), responsavel, Arrays.hashCode(planejamento));
    }
}