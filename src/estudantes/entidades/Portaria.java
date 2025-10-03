package estudantes.entidades;

public class Portaria extends Norma {

    private int anoInicio;  

    public Portaria(int numero, boolean valido, String texto, int anoInicio){
        super(numero, valido, texto);
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
