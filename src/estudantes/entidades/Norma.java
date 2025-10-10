package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Norma extends DocumentoAdministrativo {

private int numero;
private boolean valido;
private String texto;

    public Norma(String criador, CodigoCurso codigoCurso, int paginas,int numero, boolean valido, String texto){
        super(criador, codigoCurso, paginas);
        this.numero = numero;
        this.valido = valido;
        this.texto = texto;
    }

    public int getNumero(){
        return this.numero;
    }
    public boolean getValido(){
        return this.valido;
    }
    public String getTexto(){
        return this.texto;
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
        Norma other =  (Norma) obj;
        return this.numero == other.numero && this.valido == other.valido && this.texto.equals(other.texto);
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), numero, valido, texto);
    }
}
