package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
/**
 * Representa um documento do tipo Deliberação, que herda as propriedades básicas de um {@link DocumentoAdministrativo}.
 *
 * <p>Além das propriedades de um documento administrativo (criador, código de curso e número de páginas),
 * uma Deliberação possui um texto descritivo.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see DocumentoAdministrativo
 * @see professor.entidades.CodigoCurso
 */
public class Deliberacao extends DocumentoAdministrativo {

    private String texto;
    /**
     * Construtor da classe Deliberacao.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual a Deliberação está associada.
     * @param paginas O número de páginas que o documento possui.
     * @param texto O conteúdo textual descritivo da Deliberação.
     */
    public Deliberacao(String criador, CodigoCurso codigoCurso, int paginas, String texto){
        super(criador, codigoCurso, paginas);
        this.texto = texto;
    }
    /**
    * Retorna o conteúdo textual descritivo desta Deliberação.
    *
    * @return O texto da Deliberação.
    */
    public String getTexto(){
        return this.texto;
    }
    /**
     * Define o conteúdo textual descritivo desta Deliberação.
     * @param texto
     * @return
     */
    public String setTexto(String texto){
        return this.texto = texto;
    }
    /** 
     * Verifica se esta Deliberação é igual a outro objeto.
     * @param obj O objeto a ser comparado com esta Deliberação.
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
        Deliberacao other = (Deliberacao) obj;
        return this.texto.equals(other.texto);
        
    } 
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link DocumentoAdministrativo},
     * bem como no texto descritivo da Deliberação.</p>
     *
     * @return Um valor de código hash para esta Deliberação.
     */
    @Override  
    public int hashCode(){
        return Objects.hash(super.hashCode(), texto);
    } 
}
