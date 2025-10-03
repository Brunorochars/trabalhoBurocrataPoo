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
        if(this.criador.equals(((Object) obj).criador) && this.codigoCurso.equals((Object) obj).codigoCurso && this.paginas == ((Object) obj).paginas){
            return true;
        } else {
            return false;
        }
    }

}