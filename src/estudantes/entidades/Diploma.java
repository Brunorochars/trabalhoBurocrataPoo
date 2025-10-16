package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
/**
 * Representa um documento do tipo Diploma, que herda as propriedades básicas de um {@link Certificado}.
 *
 * <p>Além das propriedades de um certificado (criador, código de curso, número de páginas, autenticação,
 * estudante, matrícula e descrição), um Diploma possui uma habilitação.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see Certificado
 * @see professor.entidades.CodigoCurso
 */
public class Diploma extends Certificado{
    private String habilitacao;
    /**
     * Construtor da classe Diploma.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual o Diploma está associada.
     * @param paginas O número de páginas que o documento possui.
     * @param autenticacao O número de autenticação do Diploma.
     * @param estudante O nome ou identificador do estudante a quem o Diploma é concedido.
     * @param matricula O número de matrícula do estudante.
     * @param descricao A descrição do Diploma.
     * @param habilitacao A habilitação conferida pelo Diploma.
     */
    public Diploma(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula, String descricao, String habilitacao) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula,descricao);
        this.habilitacao=habilitacao;
    }
    /**
     * Retorna a habilitação conferida pelo Diploma.
     *
     * @return A habilitação do Diploma.
     */
    public String getHabilitacao() {
        return habilitacao;
    }
    /**
     * Define a habilitação conferida pelo Diploma.
     *
     * @param habilitacao A nova habilitação do Diploma.
     */
    public void setHabilitacao(String habilitacao) {
        this.habilitacao = habilitacao;
    }
    /**
     * Verifica se este Diploma é igual a outro objeto.
     *
     * @param obj O objeto a ser comparado com este Diploma.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contário.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        if (!(super.equals(obj))) return false;
        Diploma diplomaTestado = (Diploma) obj;
        return this.habilitacao.equals(diplomaTestado.habilitacao);
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link Certificado},
     * bem como na habilitação do Diploma.</p>
     *
     * @return Um valor de código hash para este Diploma.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),habilitacao);
    }
}
