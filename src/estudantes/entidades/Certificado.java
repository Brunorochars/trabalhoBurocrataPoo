package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
/**
 * Representa um documento do tipo Certificado, que herda as propriedades básicas de um {@link Registro}.
 *
 * <p>Além das propriedades de um registro (criador, código de curso, número de páginas,
 * autenticacao, estudante e matrícula), um Certificado possui uma descrição.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see Registro
 * @see professor.entidades.CodigoCurso
 */
public class Certificado extends Registro {
    private String descricao;
    /**
     * Construtor da classe Certificado.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual o Certificado está associado.
     * @param paginas O número de páginas que o documento possui.
     * @param autenticacao O número de autenticação único do Certificado.
     * @param estudante O nome ou identificador do estudante ao qual o Certificado se refere.
     * @param matricula A matrícula do estudante ao qual o Certificado se refere.
     * @param descricao A descrição detalhada do Certificado (motivo, período, etc.).
     */
    public Certificado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula, String descricao) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.descricao = descricao;
    }
    /**
     * Retorna a descrição deste Certificado.
     *
     * @return A descrição do Certificado.
     */
    public String getDescricao() {
        return descricao;
    }
    /**
     * Define a descrição deste Certificado.
     *
     * @param descricao A nova descrição do Certificado.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    /**
     * Verifica se este Certificado é igual a outro objeto.
     *
     * @param obj O objeto a ser comparado com este Certificado.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        if (!(super.equals(obj))) return false;
        Certificado certificadoTestado = (Certificado) obj;
        return this.descricao.equals(certificadoTestado.descricao);
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link Registro},
     * bem como na descrição do Certificado.</p>
     *
     * @return Um valor de código hash para este Certificado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), descricao);
    }
}
