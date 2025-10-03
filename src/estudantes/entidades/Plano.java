package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Arrays;
import java.util.Objects;

public class Plano extends DocumentoAcademico{
    private String responsavel;
    private String[] planejamento;

    public Plano(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao,String responsavel, String[] planejamento) {
        super(criador, codigoCurso, paginas, autenticacao);
        this.responsavel = responsavel;
        this.planejamento = planejamento;
    }
    public String getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    public String[] getPlanejamento() {
        return planejamento;
    }
    public void setPlanejamento(String[] planejamento) {
        this.planejamento = planejamento;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        if (!(super.equals(obj))) return false;
        Plano planoTestado = (Plano) obj;
        return this.responsavel.equals(planoTestado.responsavel) && DocumentoUtils.arraysDeStringSaoIguais(planoTestado.planejamento, this.planejamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), responsavel, Arrays.hashCode(planejamento));
    }

    }


