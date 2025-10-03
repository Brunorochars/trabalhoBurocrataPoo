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

    public boolean equals(Object obj){
        
        if(this == obj){
            return true;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }

        Portaria other = (Portaria) obj;

        if(this.getNumero() != other.getNumero()){
            return false;
        }
        if(this.isValido() != other.isValido()){
            return false;
        }
        if(this.getAnoInicio() != other.getAnoInicio()){
            return false;
        }
        if(this.getTexto().equals(((Portaria) other).getTexto())){
            return true
        } else {
            return false;
        }
    }
}
