package estudantes.entidades;

public class Oficio extends Deliberacao{

    private String destinatario;

    public Oficio(String texto, String destinatario){
        super(texto);
        this.destinatario = destinatario;
    }

    public String getDestinatario(){
        return this.destinatario;
    }

    public String setDestinatario(String destinatario){
        return this.destinatario = destinatario;
    }

    public boolean comparaOficio(Oficio oficio){
        if(this == oficio){
            return true;
        }
        if(oficio == null){
            return false;
        }
        if(this.getClass() != oficio.getClass()){
            return false;
        }
        if(this.getTexto().equals(((Oficio) oficio).getTexto()) && this.destinatario.equals(((Oficio) oficio).destinatario)){
            return true;
        } else {
            return false;
        }
    }
    
}
