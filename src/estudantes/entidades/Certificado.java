package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Certificado extends Registro {
    private String descricao;

    public Certificado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula, String descricao) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        if (!(super.equals(obj))) return false;
        Certificado certificadoTestado = (Certificado) obj;
        return this.descricao.equals(certificadoTestado.descricao);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), descricao);
    }
}
