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
        if(!(super.equals(obj))){
            return false;
        }

        Circular other = (Circular) obj;

        return DocumentoUtils.arraysDeStringSaoIguais(this.destinatarios, other.destinatarios);
    }
    
}
