package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class DocumentoAcademico extends Documento{
    private long autenticacao;
    public DocumentoAcademico(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao) {
        super(criador,codigoCurso,paginas);
        this.autenticacao = autenticacao;
    }
    public long getAutenticacao() {
        return autenticacao;
    }
    public void setAutenticacao(long autenticacao) {
        this.autenticacao = autenticacao;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj.getClass() != this.getClass()) return false;
        if(!(super.equals(obj))) return false;
        DocumentoAcademico documentoAcademicoTestado = (DocumentoAcademico) obj;
        return this.autenticacao == documentoAcademicoTestado.autenticacao ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),autenticacao);
    }

}
