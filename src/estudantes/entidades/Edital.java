package estudantes.entidades;

public class Edital extends Norma {

    private String[] responsaveis;

    public Edital(int numero, boolean valido, String texto, String[] responsaveis){
        super(numero, valido, texto);
        this.responsaveis = responsaveis;
    }

    public String[] getResponsaveis(){
        return this.responsaveis;
    }
    
    public String[] setResponsaveis(String[] responsaveis){
        return this.responsaveis = responsaveis;
    }

    public boolean equals(Object obj){

        if(this == obj){
            return true;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }

        Edital other = (Edital) obj;

        if(this.numero() != other.numero()){
            return false;
        }
        if(this.isValido() != other.isValido()){
            return false;
        }
        if(this.getTexto().equals(((Edital) other).getTexto()) && this.responsaveis.equals(((Edital) other).responsaveis)){
            return true;
        } else {
            return false;
        }
    }
    
}
