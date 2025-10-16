package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
/**
 * Representa uma portaria, que herda as propriedades básicas de uma {@link Norma}.
 *
 * <p>Além das propriedades de uma norma (criador, código de curso, número de páginas, número da norma,
 * status de validade e texto descritivo), uma Portaria possui um ano de início.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see Norma
 * @see professor.entidades.CodigoCurso
 */
public class Portaria extends Norma {

    private int anoInicio;  
    /**
     * Construtor da classe Portaria.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual a Portaria está associada.
     * @param paginas O número de páginas que o documento possui.
     * @param numero O número da Portaria.
     * @param valido Indica se a Portaria está atualmente válida ou não.
     * @param texto O conteúdo textual descritivo da Portaria.
     * @param anoInicio O ano de início da Portaria.
     */
    public Portaria(String criador, CodigoCurso codigoCurso, int paginas, int numero, boolean valido, String texto, int anoInicio){
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.anoInicio = anoInicio;
    }
    /**
     * Retorna o ano de início da Portaria.
     * @return
     */
    public int getAnoInicio(){
        return this.anoInicio;
    }
    /**
     * Define o ano de início da Portaria.
     * @param anoInicio
     * @return
     */
    public int setAnoInicio(int anoInicio){
        return this.anoInicio = anoInicio;
    }
    /** 
     * Verifica se esta Portaria é igual a outro objeto.
     * @param obj O objeto a ser comparado com esta Portaria.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contário.    
     */
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
        Portaria other = (Portaria) obj;
        return this.anoInicio == other.anoInicio;
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link Norma},
     * bem como no ano de início da Portaria.</p>
     *
     * @return Um valor de código hash para esta Portaria.
     */
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), anoInicio);
    }
}
