package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
/**
 * Representa um ofício, que herda as propriedades básicas de uma {@link Deliberacao}.
 *
 * <p>Além das propriedades de uma deliberação (criador, código de curso, número de páginas e texto descritivo),
 * um Ofício possui um destinatário específico.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see Deliberacao
 * @see professor.entidades.CodigoCurso
 */
public class Oficio extends Deliberacao{

    private String destinatario;
    /**
     * Construtor da classe Oficio.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual o Ofício está associada.
     * @param paginas O número de páginas que o documento possui.
     * @param texto O conteúdo textual descritivo do Ofício.
     * @param destinatario O nome do destinatário do Ofício.
     */
    public Oficio(String criador, CodigoCurso codigoCurso, int paginas, String texto, String destinatario){
        super(criador, codigoCurso, paginas, texto);
        this.destinatario = destinatario;
    }
    /**
     * Retorna o nome do destinatário do Ofício.
     * @return
     */ 
    public String getDestinatario(){
        return this.destinatario;
    }
    /**
     * Define o nome do destinatário do Ofício.
     * @param destinatario
     * @return
     */
    public String setDestinatario(String destinatario){
        return this.destinatario = destinatario;
    }
    /** 
     * Verifica se este Ofício é igual a outro objeto.
     * @param obj O objeto a ser comparado com este Ofício.
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
        Oficio other = (Oficio) obj;
        return this.destinatario.equals(other.destinatario);
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link Deliberacao},
     * bem como no destinatário do Ofício.</p>
     *
     * @return Um valor de código hash para este Ofício.
     */
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), destinatario);
    }
}
