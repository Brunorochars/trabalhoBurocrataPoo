package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
/**
 * Representa um registro acadêmico, que herda as propriedades básicas de um {@link DocumentoAcademico}.
 *
 * <p>Além das propriedades de um documento acadêmico, um Registro possui o nome do estudante e o número de matrícula.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.1
 * @see DocumentoAcademico
 * @see professor.entidades.CodigoCurso
 */
public class Registro extends DocumentoAcademico {
    private String estudante;
    private long matricula;

    /**
     * Construtor da classe Registro.
     *
     * @param criador O nome do criador do documento.
     * @param codigoCurso O código do curso associado.
     * @param paginas O número de páginas do documento.
     * @param autenticacao O código de autenticação do documento acadêmico.
     * @param estudante O nome do estudante a quem o registro se refere.
     * @param matricula O número de matrícula do estudante.
     */
    public Registro(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula) {
        super(criador, codigoCurso, paginas, autenticacao);
        this.estudante = estudante;
        this.matricula = matricula;
    }

    /**
     * Obtém o nome do estudante associado a este registro.
     * @return Uma {@code String} com o nome do estudante.
     */
    public String getEstudante() {
        return estudante;
    }

    /**
     * Altera o nome do estudante deste registro.
     * @param estudante A nova {@code String} para o nome do estudante.
     */
    public void setEstudante(String estudante) {
        this.estudante = estudante;
    }

    /**
     * Obtém o número de matrícula do estudante.
     * @return O número de matrícula.
     */
    public long getMatricula() {
        return matricula;
    }

    /**
     * Altera o número de matrícula do estudante.
     * @param matricula O novo número de matrícula.
     */
    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    /**
     * Verifica se este Registro é igual a outro objeto.
     * @param obj O objeto a ser comparado com este Registro.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contrário.
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