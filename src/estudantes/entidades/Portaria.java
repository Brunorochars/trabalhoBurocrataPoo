package estudantes.entidades;

public class Portaria extends Norma {

    private int anoInicio;  

    public Portaria(int numero, boolean valido, String texto, int anoInicio){
        super(numero, valido, texto);
        this.anoInicio = anoInicio;
    }
}
