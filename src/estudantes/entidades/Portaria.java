package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Portaria extends Norma {

    private int anoInicio;  

    public Portaria(String criador, CodigoCurso codigoCurso, int paginas, int numero, boolean valido, String texto, int anoInicio){
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.anoInicio = anoInicio;
    }

    public int getAnoInicio(){
        return this.anoInicio;
    }
    public int setAnoInicio(int anoInicio){
        return this.anoInicio = anoInicio;
    }

    @Override
    public boolean equals(Object obj){
        
        if(this == obj){
            return true;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        if(!(super.equals(obj))){
            return false;
        }
        Portaria other = (Portaria) obj;
        return this.anoInicio == other.anoInicio;
    }
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), anoInicio);
    }
}
