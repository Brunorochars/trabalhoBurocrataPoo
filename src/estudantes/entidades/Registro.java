package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
/**
 * Representa um registro acadêmico, que herda as propriedades básicas de um {@link DocumentoAcademico}.
 *
 * <p>Além das propriedades de um documento acadêmico (criador, código de curso, número de páginas e número de autenticação),
 * um Registro possui o nome do estudante e o número de matrícula.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see DocumentoAcademico
 * @see professor.entidades.CodigoCurso
 */
public class Registro  extends DocumentoAcademico{
    private String estudante;
    private long matricula;
    public Registro(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao,String estudante,long matricula) {
        super(criador, codigoCurso, paginas, autenticacao);
        this.estudante = estudante;
        this.matricula = matricula;
    }
    /**
     * Retorna o nome ou identificador do estudante a quem o Registro é concedido.
     * @return
     */
    public String getEstudante() {
        return estudante;
    }
    /**
     * Define o nome ou identificador do estudante a quem o Registro é concedido.
     * @param estudante
     */
    public void setEstudante(String estudante) {
        this.estudante = estudante;
    }
    /**
     * Retorna o número de matrícula do estudante.
     * @return
     */
    public long getMatricula() {
        return matricula;
    }
    /**
     * Define o número de matrícula do estudante.
     * @param matricula
     */
    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }
    /**
     * Verifica se este Registro é igual a outro objeto.
     * @param obj O objeto a ser comparado com este Registro.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contário.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        if (!(super.equals(obj))) return false;
        Registro registroTestado = (Registro) obj;

        return this.estudante.equals(registroTestado.estudante) && this.matricula == registroTestado.matricula;
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link DocumentoAcademico},
     * bem como no estudante e matrícula do Registro.</p>
     *
     * @return Um valor de código hash para este Registro.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),estudante,matricula);
    }
}
