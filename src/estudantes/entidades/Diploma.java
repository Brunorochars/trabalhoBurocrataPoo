package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Diploma extends Certificado{
    private String habilitacao;
    public Diploma(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula, String descricao, String habilitacao) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula,descricao);
        this.habilitacao=habilitacao;
    }
    public String getHabilitacao() {
        return habilitacao;
    }
    public void setHabilitacao(String habilitacao) {
        this.habilitacao = habilitacao;
    }
    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        if (!(super.equals(obj))) return false;
        Diploma diplomaTestado = (Diploma) obj;
        return this.habilitacao.equals(diplomaTestado.habilitacao);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),habilitacao);
    }
}
