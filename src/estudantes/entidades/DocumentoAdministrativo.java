package estudantes.entidades;

public class DocumentoAdministrativo extends Documento{
    
    public DocumentoAdministrativo(String criador, CodigoCurso codigoCurso, int paginas){
        super(criador, codigoCurso, paginas);    

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
        
        return this.super.equals(other);
    }

}