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

    public boolean comparaPortaria(Portaria portaria){
        if(this == portaria){
            return true;
        }
        if(portaria == null){
            return false;
        }
        if(this.getClass() != portaria.getClass()){
            return false;
        }
        if(this.getTexto().equals(((Portaria) portaria).getTexto()) && this.anoInicio == ((Portaria) portaria).anoInicio){
            return true;
        } else {
            return false;
        }
    }
}
