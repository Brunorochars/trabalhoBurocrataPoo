package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
/**
 * Representa um documento administrativo, que herda as propriedades básicas de um {@link Documento}.
 *
 * <p>Um DocumentoAdministrativo possui as mesmas propriedades de um documento genérico (criador, código de curso e número de páginas).</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see Documento
 * @see professor.entidades.CodigoCurso
 */
public class DocumentoAdministrativo extends Documento{
    /**
     * Construtor da classe DocumentoAdministrativo.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual o DocumentoAdministrativo está associada.
     * @param paginas O número de páginas que o documento possui.
     */
    public DocumentoAdministrativo(String criador, CodigoCurso codigoCurso, int paginas){
        super(criador, codigoCurso, paginas);    

    }
    /** 
     * Verifica se este DocumentoAdministrativo é igual a outro objeto.
     * @param obj O objeto a ser comparado com este DocumentoAdministrativo.
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
        DocumentoAdministrativo other = (Deliberacao) obj;
        return super.equals(other);
    }
    /** 
     * Retorna um valor de código hash para o objeto.
     * 
     * <p>O código hash é baseado no código hash do superclasse.</p>
     * 
     * @return Um valor de código hash para este DocumentoAdministrativo.
     */
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode());
    }

}