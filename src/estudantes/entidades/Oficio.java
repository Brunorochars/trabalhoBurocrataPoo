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

    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }

        Oficio other = (Oficio) obj;

        if(this.getTexto().equals(((Oficio) other).getTexto()) && this.destinatario.equals(((Oficio) other).destinatario)){
            return true;
        } else {
            return false;
        }
    }
    
}
