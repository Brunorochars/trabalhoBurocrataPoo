package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
/**
 * Representa um documento do tipo Circular, que herda as propriedades básicas de uma {@link Deliberacao}.
 *
 * <p>Além das propriedades de uma deliberação (criador, código de curso, número de páginas e texto),
 * uma Circular possui uma lista de destinatários.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see Deliberacao
 * @see professor.entidades.CodigoCurso
 */
public class Circular extends Deliberacao {

    private String[] destinatarios;
    /**
     * Construtor da classe Circular.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual a Circular está associada.
     * @param paginas O número de páginas que o documento possui.
     * @param texto O conteúdo textual descritivo da Circular.
     * @param destinatarios Um array de {@code String} contendo os nomes dos destinatários da Circular.
     */
    public Circular(String criador, CodigoCurso codigoCurso, int paginas, String texto, String[] destinatarios){
        super(criador, codigoCurso, paginas, texto);
        this.destinatarios = destinatarios;
    }
    /**
     * Retorna o array de nomes dos destinatários da Circular.
     *
     * @return Um array de {@code String} contendo os nomes dos destinatários.
     */
    public String[] getDestinatarios(){
        return this.destinatarios;
    }  
    /**
     * Define o array de nomes dos destinatários da Circular.
     *
     * @param destinatarios Um array de {@code String} contendo os novos nomes dos destinatários.
     * @return O array atualizado de nomes dos destinatários.
     */
    public String[] setDestinatario(String[] destinatarios){
        return this.destinatarios = destinatarios;
    } 
    /** 
     * Verifica se este Circular é igual a outro objeto.
     * @param obj O objeto a ser comparado com este Circular.
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
        Circular other = (Circular) obj;
        return DocumentoUtils.arraysDeStringSaoIguais(this.destinatarios, other.destinatarios);
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link Deliberacao},
     * bem como na lista de destinatários da Circular.</p>
     *
     * @return Um valor de código hash para este Circular.
     */
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), destinatarios);
    }
    
}
