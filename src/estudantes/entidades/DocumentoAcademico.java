package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
/**
 * Representa um documento acadêmico, que herda as propriedades básicas de um {@link Documento}.
 *
 * <p>Além das propriedades de um documento (criador, código de curso e número de páginas),
 * um DocumentoAcademico possui um número de autenticação.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see Documento
 * @see professor.entidades.CodigoCurso
 */
public class DocumentoAcademico extends Documento{
    private long autenticacao;
    /**
     * Construtor da classe DocumentoAcademico.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual o DocumentoAcademico está associada.
     * @param paginas O número de páginas que o documento possui.
     * @param autenticacao O número de autenticação do DocumentoAcademico.
     */
    public DocumentoAcademico(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao) {
        super(criador,codigoCurso,paginas);
        this.autenticacao = autenticacao;
    }
    /**
     * Retorna o número de autenticação do DocumentoAcademico.
     *
     * @return O número de autenticação do DocumentoAcademico.
     */
    public long getAutenticacao() {
        return autenticacao;
    }
    /**
     * Define o número de autenticação do DocumentoAcademico.
     *
     * @param autenticacao O novo número de autenticação do DocumentoAcademico.
     */
    public void setAutenticacao(long autenticacao) {
        this.autenticacao = autenticacao;
    }
    /**
     * Verifica se este DocumentoAcademico é igual a outro objeto.
     *
     * @param obj O objeto a ser comparado com este DocumentoAcademico.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contário.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj.getClass() != this.getClass()) return false;
        if(!(super.equals(obj))) return false;
        DocumentoAcademico documentoAcademicoTestado = (DocumentoAcademico) obj;
        return this.autenticacao == documentoAcademicoTestado.autenticacao ;
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash do superclasse e no número de autenticação do documento acadêmico.</p>
     *
     * @return Um valor de código hash para este DocumentoAcademico.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),autenticacao);
    }

}
