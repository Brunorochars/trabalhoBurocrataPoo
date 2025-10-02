package estudantes.entidades;

public class Circular extends Deliberacao {

    private String[] destinatarios;

    public Circular(String texto, String[] destinatarios){
        super(texto);
        this.destinatarios = destinatarios;
    }

    public String[] getDestinatarios(){
        return this.destinatarios;
    }  

    public String[] setDestinatario(String[] destinatarios){
        return this.destinatarios = destinatarios;
    } 

    public boolean comparaCircular(Circular circular){
        
        if(this == circular){
            return true;
        }
        if(circular == null){
            return false;
        }
        if(this.getClass() != circular.getClass()){
            return false;
        }
        if(this.getTexto().equals(((Circular) circular).getTexto()) && this.destinatarios.equals(((Circular) circular).destinatarios)){
            return true;
        } else {
            return false;
        }
    }
    
}
