package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

/**
 * Classe que representa um documento genérico.
 * <br><br>
 * <strong>Seu trabalho começa aqui...</strong>
 * 
 * @author Bruno da Silva Rocha, Frederico de Oliveira.
 */
public abstract class Documento {
    private String criador;
    private CodigoCurso codigoCurso;
    private int paginas;

    public Documento(String criador, CodigoCurso codigoCurso, int paginas){
        this.criador = criador;
        this.codigoCurso = codigoCurso;
        this.paginas = paginas;
    }
    public CodigoCurso getCodigoCurso() {
        return codigoCurso;
    }
    public void setCodigoCurso(CodigoCurso codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
    public String getCriador() {
        return criador;
    }
    public void setCriador(String criador) {
        this.criador = criador;
    }
    public int getPaginas() {
        return paginas;
    }
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj.getClass() == this.getClass())) return false;
        Documento documentoTestado = (Documento) obj;
        return this.criador.equals(documentoTestado.getCriador()) && this.paginas == documentoTestado.getPaginas() &&  this.codigoCurso.equals(documentoTestado.getCodigoCurso());
    }

    @Override
    public int hashCode() {
        return Objects.hash(criador, paginas);
    }
}
