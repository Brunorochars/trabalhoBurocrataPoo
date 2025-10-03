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

    public boolean equals(Object obj){
        
        if(this == obj){
            return true;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        if(this.getTexto().equals(((Object) obj).getTexto()) && this.destinatarios.equals(((Object) obj).destinatarios)){
            return true;
        } else {
            return false;
        }
    }
    
}
