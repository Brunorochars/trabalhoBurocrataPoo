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

    public boolean comparaNorma(Norma norma){

        if(this == norma){
            return true;
        }
        if(norma == null){
            return false;
        }
        if(this.getClass() != norma.getClass()){
            return false;
        }
        if(this.numero == ((Norma) norma).numero && this.valido == ((Norma) norma).valido && this.texto.equals(((Norma) norma).texto)){
            return true;
        } else {
            return false;
        }
    }
    
}
