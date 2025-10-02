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

    public boolean comparaEdital(Edital edital){
        if(this == edital){
            return true;
        }
        if(edital == null){
            return false;
        }
        if(this.getClass() != edital.getClass()){
            return false;
        }
        if(this.getTexto().equals(((Edital) edital).getTexto()) && this.responsaveis.equals(((Edital) edital).responsaveis)){
            return true;
        } else {
            return false;
        }
    }
    
}
