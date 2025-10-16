package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
/**
 * Representa um documento do tipo Atestado, que herda as propriedades básicas de um {@link Registro}.
 *
 * <p>Além das propriedades de um registro (criador, código de curso, número de páginas,
 * autenticacao, estudante e matrícula), um Atestado possui uma descrição e uma categoria.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see Registro
 * @see professor.entidades.CodigoCurso
 */
public class Atestado extends Registro{
    private String descricao;
    private String categoria;
    /**
     * Construtor da classe Atestado.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual o Atestado está associado.
     * @param paginas O número de páginas que o documento possui.
     * @param autenticacao O número de autenticação único do Atestado.
     * @param estudante O nome ou identificador do estudante ao qual o Atestado se refere.
     * @param matricula A matrícula do estudante ao qual o Atestado se refere.
     * @param descricao A descrição detalhada do Atestado (motivo, período, etc.).
     * @param categoria A categoria do Atestado (médico, acadêmico, etc.).
     */
    public Atestado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula,String descricao, String categoria) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.descricao = descricao;
        this.categoria = categoria;
    }
    /**
     * Retorna a descrição deste Atestado.
     *
     * @return A descrição do Atestado.
     */
    public String getDescricao() {
        return descricao;
    }
    /**
     * Define a descrição deste Atestado.
     *
     * @param descricao A nova descrição do Atestado.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    /**
     * Retorna a categoria deste Atestado.
     *
     * @return A categoria do Atestado.
     */
    public String getCategoria() {
        return categoria;
    }
    /**
     * Define a categoria deste Atestado.
     *
     * @param categoria A nova categoria do Atestado.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    /**
     * Verifica se este Atestado é igual a outro objeto.
     *
     * @param obj O objeto a ser comparado com este Atestado.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        if (!(super.equals(obj))) return false;
        Atestado atestadoTestado = (Atestado) obj;
        return this.descricao.equals(atestadoTestado.descricao) && this.categoria.equals(atestadoTestado.categoria);
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link Registro},
     * bem como na descrição e categoria do Atestado.</p>
     *
     * @return Um valor de código hash para este Atestado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), descricao, categoria);
    }

}
