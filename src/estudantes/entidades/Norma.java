package estudantes.entidades;

public class Norma extends DocumentoAdministrativo {

private int numero;
private boolean valido;
private String texto;

    public Norma(int numero, boolean valido, String texto){
        this.numero = numero;
        this.valido = valido;
        this.texto = texto;
    }

    public int getNumero(){
        return this.numero;
    }

    public boolean getValido(){
        return this.valido;
    }

    public String getTexto(){
        return this.texto;
    }

    public boolean equals(Object obj){

        if(this == obj){
            return true;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }

        Norma other =  (Norma) obj;
        
        if(this.numero != other.numero){
            return false;
        }
        if(this.isValido() != other.isValido()){
            return false;
        }

        if(this.texto.equals(((Norma) other).texto)){
            return true;
        } else {
            return false;
        }
    }
    
}
