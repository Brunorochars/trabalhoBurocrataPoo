package estudantes.entidades;
import professor.entidades.CodigoCurso;
import java.util.Objects;

public class DocumentoAdministrativo extends Documento{
    
    public DocumentoAdministrativo(String criador, CodigoCurso codigoCurso, int paginas){
        super(criador, codigoCurso, paginas);    

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
        DocumentoAdministrativo other = (Deliberacao) obj;
        return super.equals(other);
    }
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode());
    }

}