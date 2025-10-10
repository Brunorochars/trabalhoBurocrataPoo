package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Circular extends Deliberacao {

    private String[] destinatarios;

    public Circular(String criador, CodigoCurso codigoCurso, int paginas, String texto, String[] destinatarios){
        super(criador, codigoCurso, paginas, texto);
        this.destinatarios = destinatarios;
    }

    public String[] getDestinatarios(){
        return this.destinatarios;
    }  

    public String[] setDestinatario(String[] destinatarios){
        return this.destinatarios = destinatarios;
    } 
    @Override
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
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), destinatarios);
    }
    
}
