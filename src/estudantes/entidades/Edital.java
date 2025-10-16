package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;
/**
 * Representa um documento do tipo Edital, que herda as propriedades básicas de uma {@link Norma}.
 *
 * <p>Além das propriedades de uma norma (criador, código de curso, número de páginas, número, validade e texto),
 * um Edital possui uma lista de responsáveis.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see Norma
 * @see professor.entidades.CodigoCurso
 */
public class Edital extends Norma {

    private String[] responsaveis;
    /**
     * Construtor da classe Edital.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual o Edital está associada.
     * @param paginas O número de páginas que o documento possui.
     * @param numero O número do Edital.
     * @param valido Indica se o Edital está atualmente válido ou não.
     * @param texto O conteúdo textual descritivo do Edital.
     * @param responsaveis Um array de {@code String} contendo os nomes dos responsáveis pelo Edital.
     */
    public Edital(String criador, CodigoCurso codigoCurso, int paginas, int numero, boolean valido, String texto, String[] responsaveis){
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.responsaveis = responsaveis;
    }
    /**
     * Retorna o array de nomes dos responsáveis pelo Edital.
     *
     * @return Um array de {@code String} contendo os nomes dos responsáveis.
     */
    public String[] getResponsaveis(){
        return this.responsaveis;
    }
    /**
     * Define o array de nomes dos responsáveis pelo Edital.
     *
     * @param responsaveis Um array de {@code String} contendo os novos nomes dos responsáveis.
     * @return O array atualizado de nomes dos responsáveis.
     */  
    public String[] setResponsaveis(String[] responsaveis){
        return this.responsaveis = responsaveis;
    }
    /** 
     * Verifica se este Edital é igual a outro objeto.
     * @param obj O objeto a ser comparado com este Edital.
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
        Edital other = (Edital) obj;
        return DocumentoUtils.arraysDeStringSaoIguais(this.responsaveis, other.responsaveis);
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link Norma},
     * bem como na lista de responsáveis do Edital.</p>
     *
     * @return Um valor de código hash para este Edital.
     */
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), responsaveis);
    }
}
