package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;

/**
 * Representa um documento genérico, que serve como classe base para outros tipos de documentos.
 *
 * <p>Um Documento possui propriedades básicas como criador, código de curso e número de páginas.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see professor.entidades.CodigoCurso
 */
public abstract class Documento {
    private String criador;
    private CodigoCurso codigoCurso;
    private int paginas;
    /**
     * Construtor da classe Documento.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual o documento está associado.
     * @param paginas O número de páginas que o documento possui.
     */
    public Documento(String criador, CodigoCurso codigoCurso, int paginas){
        this.criador = criador;
        this.codigoCurso = codigoCurso;
        this.paginas = paginas;
    }
    /**
     * Retorna o código do curso ao qual o documento está associado.
     * @return
     */
    public CodigoCurso getCodigoCurso() {
        return codigoCurso;
    }
    /**
     * Define o código do curso ao qual o documento está associado.
     * @param codigoCurso
     */
    public void setCodigoCurso(CodigoCurso codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
    /**
     * Retorna o nome ou identificador do criador do documento.
     * @return
     */
    public String getCriador() {
        return criador;
    }
    /**
     * Define o nome ou identificador do criador do documento.
     * @param criador
     */
    public void setCriador(String criador) {
        this.criador = criador;
    }
    /**
     * Retorna o número de páginas que o documento possui.
     * @return
     */
    public int getPaginas() {
        return paginas;
    }
    /**
     * Define o número de páginas que o documento possui.
     * @param paginas
     */
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
    /**
     * Verifica se este Documento é igual a outro objeto.
     *
     * @param obj O objeto a ser comparado com este Documento.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contário.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj.getClass() == this.getClass())) return false;
        Documento documentoTestado = (Documento) obj;
        return this.criador.equals(documentoTestado.getCriador()) && this.paginas == documentoTestado.getPaginas() &&  this.codigoCurso.equals(documentoTestado.getCodigoCurso());
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no criador e no número de páginas do documento.</p>
     *
     * @return Um valor de código hash para este Documento.
     */
    @Override
    public int hashCode() {
        return Objects.hash(criador, paginas);
    }
}
