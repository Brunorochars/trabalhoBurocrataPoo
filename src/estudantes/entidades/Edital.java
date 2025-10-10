package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Edital extends Norma {

    private String[] responsaveis;

    public Edital(String criador, CodigoCurso codigoCurso, int paginas, int numero, boolean valido, String texto, String[] responsaveis){
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.responsaveis = responsaveis;
    }
    public String[] getResponsaveis(){
        return this.responsaveis;
    }  
    public String[] setResponsaveis(String[] responsaveis){
        return this.responsaveis = responsaveis;
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
        Edital other = (Edital) obj;
        return DocumentoUtils.arraysDeStringSaoIguais(this.responsaveis, other.responsaveis);
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), responsaveis);
    }
}
