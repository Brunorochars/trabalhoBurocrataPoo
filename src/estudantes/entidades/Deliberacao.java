package estudantes.entidades;

import professor.entidades.CodigoCurso;

public class Deliberacao extends DocumentoAdministrativo {

    private String texto;

    public Deliberacao(String criador, CodigoCurso codigoCurso, int paginas, String texto){
        super(criador, null, paginas);
        this.texto = texto;
    }

    public String getTexto(){
        return this.texto;
    }

    public String setTexto(String texto){
        return this.texto = texto;
    }
    
    public boolean comparaDeliberacao(Deliberacao deliberacao){
        
        if(this == deliberacao){
            return true;
        }
        if(deliberacao == null){
            return false;
        }
        if(this.getClass() != deliberacao.getClass()){
            return false;
        }
        if(this.texto.equals(((Deliberacao) deliberacao).texto)){
            return true;
        } else {
            return false;
        }
     }    
}
