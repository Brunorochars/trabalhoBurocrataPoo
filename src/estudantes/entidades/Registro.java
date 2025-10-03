package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Registro  extends DocumentoAcademico{
    private String estudante;
    private long matricula;
    public Registro(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao,String estudante,long matricula) {
        super(criador, codigoCurso, paginas, autenticacao);
        this.estudante = estudante;
        this.matricula = matricula;
    }
    public String getEstudante() {
        return estudante;
    }
    public void setEstudante(String estudante) {
        this.estudante = estudante;
    }
    public long getMatricula() {
        return matricula;
    }
    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        if (!(super.equals(obj))) return false;
        Registro registroTestado = (Registro) obj;

        return this.estudante.equals(registroTestado.estudante) && this.matricula == registroTestado.matricula;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),estudante,matricula);
    }
}
