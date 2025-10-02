package estudantes.entidades;

public class DocumentoAdministrativo extends Documento{
    
    public DocumentoAdministrativo(String criador, CodigoCurso codigoCurso, int paginas){
        super(criador, codigoCurso, paginas);    

    }
    
    public boolean comparaDocumento(DocumentoAdministrativo doc){
        if(this == doc){
            return true;
        }
        if(doc == null){
            return false;
        }
        if(this.getClass() != doc.getClass()){
            return false;
        }
        if(this.criador.equals(((DocumentoAdministrativo) doc).criador) && this.codigoCurso.equals((DocumentoAdministrativo) doc).codigoCurso && this.paginas == ((DocumentoAdministrativo) doc).paginas){
            return true;
        } else {
            return false;
        }
    }

}