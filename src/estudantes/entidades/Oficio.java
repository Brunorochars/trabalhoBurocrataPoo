package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Oficio extends Deliberacao{

    private String destinatario;

    public Oficio(String criador, CodigoCurso codigoCurso, int paginas, String texto, String destinatario){
        super(criador, codigoCurso, paginas, texto);
        this.destinatario = destinatario;
    }
    
    public String getDestinatario(){
        return this.destinatario;
    }
    public String setDestinatario(String destinatario){
        return this.destinatario = destinatario;
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
        Oficio other = (Oficio) obj;
        return this.destinatario.equals(other.destinatario);
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), destinatario);
    }
}
