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
    
    public boolean equals(Object obj){
        
        if(this == obj){
            return true;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }

        Deliberacao other = (Deliberacao) obj;

        if(this.paginas != other.paginas){
            return false;
        }
        if(this.texto.equals(((Deliberacao) other).texto) && this.getCriador().equals(((Deliberacao) other).getCriador()) && this.codigoCurso.equals(((Deliberacao) other).codigoCurso)){
            return true;
        } else {
            return false;
        }
     }    
}
