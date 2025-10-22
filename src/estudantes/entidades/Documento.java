package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;

/**
 * Representa um documento genérico, que serve como classe base para outros tipos de documentos.
 *
 * <p>Um Documento possui propriedades básicas como criador, código de curso e número de páginas.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.1
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
     * Obtém o código de curso associado a este documento.
     * @return O enum {@link CodigoCurso} do curso.
     */
    public CodigoCurso getCodigoCurso() {
        return codigoCurso;
    }
    /**
     * Altera o código de curso deste documento.
     * @param codigoCurso O novo {@link CodigoCurso} a ser atribuído.
     */
    public void setCodigoCurso(CodigoCurso codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
    /**
     * Obtém o nome do criador do documento.
     * @return Uma {@code String} com o nome do criador.
     */
    public String getCriador() {
        return criador;
    }
    /**
     * Altera o nome do criador do documento.
     * @param criador A nova {@code String} para o nome do criador.
     */
    public void setCriador(String criador) {
        this.criador = criador;
    }
    /**
     * Obtém a quantidade de páginas do documento.
     * @return O número total de páginas.
     */
    public int getPaginas() {
        return paginas;
    }
    /**
     * Altera a quantidade de páginas do documento.
     * @param paginas O novo número total de páginas.
     */
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
    /**
     * Verifica se este Documento é igual a outro objeto.
     *
     * @param obj O objeto a ser comparado com este Documento.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contrário.
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