package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;
/**
 * Representa um documento do tipo Norma, que herda as propriedades básicas de um {@link DocumentoAdministrativo}.
 *
 * <p>Além das propriedades de um documento administrativo (criador, código de curso e número de páginas),
 * uma Norma possui um número identificador, um status de validade e um texto descritivo.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see DocumentoAdministrativo
 * @see professor.entidades.CodigoCurso
 */
public class Norma extends DocumentoAdministrativo {

private int numero;
private boolean valido;
private String texto;
    /**
     * Construtor da classe Norma.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual a Norma está associada.
     * @param paginas O número de páginas que o documento possui.
     * @param numero O número da Norma.
     * @param valido Indica se a Norma está atualmente válida ou não.
     * @param texto O conteúdo textual descritivo da Norma.
     */
    public Norma(String criador, CodigoCurso codigoCurso, int paginas,int numero, boolean valido, String texto){
        super(criador, codigoCurso, paginas);
        this.numero = numero;
        this.valido = valido;
        this.texto = texto;
    }
    /**
     * Retorna o número da Norma.
     * @return
     */
    public int getNumero(){
        return this.numero;
    }
    /**
     * Retorna o status de validade da Norma.
     * @return
     */
    public boolean getValido(){
        return this.valido;
    }
    /**
     * Retorna o conteúdo textual descritivo da Norma.
     * @return
     */
    public String getTexto(){
        return this.texto;
    }
    /**
     * Verifica se esta Norma é igual a outro objeto.
     * @param obj O objeto a ser comparado com esta Norma.
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
        Norma other =  (Norma) obj;
        return this.numero == other.numero && this.valido == other.valido && this.texto.equals(other.texto);
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link DocumentoAdministrativo},
     * bem como no número, validade e texto da Norma.</p>
     *
     * @return Um valor de código hash para esta Norma.
     */
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), numero, valido, texto);
    }
}
