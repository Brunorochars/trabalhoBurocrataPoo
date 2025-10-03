package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Historico extends Registro{
    private  double coeficiente;
    private String[] componentes;
    public Historico(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula,Double coeficiente, String[] componentes) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.coeficiente = coeficiente;
    }

    public Double getCoeficiente() {
        return coeficiente;
    }
    public void setCoeficiente(Double coeficiente) {
        this.coeficiente = coeficiente;
    }
    public String[] getComponentes() {
        return componentes;
    }
    public void setComponentes(String[] componentes) {
        this.componentes = componentes;
    }
    @Override
    public boolean equals(Object obj) {

            if (obj == null) return false;
            if (obj == this) return true;
            if (obj.getClass() != this.getClass()) return false;
            if (!(super.equals(obj))) return false;
            Historico historicoTestado = (Historico) obj;
            return this.coeficiente == historicoTestado.coeficiente && DocumentoUtils.arraysDeStringSaoIguais(this.componentes, historicoTestado.componentes) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coeficiente);
    }
}
