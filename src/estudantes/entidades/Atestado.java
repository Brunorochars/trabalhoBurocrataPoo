package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Atestado extends Registro{
    private String descricao;
    private String categoria;
    public Atestado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula,String descricao, String categoria) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.descricao = descricao;
        this.categoria = categoria;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        if (!(super.equals(obj))) return false;
        Atestado atestadoTestado = (Atestado) obj;
        return this.descricao.equals(atestadoTestado.descricao) && this.categoria.equals(atestadoTestado.categoria);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), descricao, categoria);
    }

}
