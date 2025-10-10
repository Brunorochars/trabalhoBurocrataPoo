package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Deliberacao extends DocumentoAdministrativo {

    private String texto;

    public Deliberacao(String criador, CodigoCurso codigoCurso, int paginas, String texto){
        super(criador, codigoCurso, paginas);
        this.texto = texto;
    }

    public String getTexto(){
        return this.texto;
    }

    public String setTexto(String texto){
        return this.texto = texto;
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
        Deliberacao other = (Deliberacao) obj;
        return this.texto.equals(other.texto);
        
     } 
    @Override  
    public int hashCode(){
        return Objects.hash(super.hashCode(), texto);
    } 
}
