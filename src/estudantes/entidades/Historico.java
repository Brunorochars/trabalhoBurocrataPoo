package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;
/**
 * Representa um histórico acadêmico, que herda as propriedades básicas de um {@link Registro}.
 *
 * <p>Além das propriedades de um registro, um Histórico possui um coeficiente de rendimento e uma lista de componentes cursados.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.1
 * @see Registro
 * @see professor.entidades.CodigoCurso
 */
public class Historico extends Registro{
    private  double coeficiente;
    private String[] componentes;
    /**
     * Construtor da classe Historico.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual o Histórico está associado.
     * @param paginas O número de páginas que o documento possui.
     * @param autenticacao O número de autenticação do Histórico.
     * @param estudante O nome ou identificador do estudante a quem o Histórico é concedido.
     * @param matricula O número de matrícula do estudante.
     * @param coeficiente O coeficiente de rendimento do estudante.
     * @param componentes A lista de componentes curriculares cursados pelo estudante.
     */
    public Historico(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula, double coeficiente, String[] componentes) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.coeficiente = coeficiente;
        this.componentes = componentes;
    }
    /**
     * Obtém o coeficiente de rendimento do estudante.
     * @return O coeficiente de rendimento.
     */
    public double getCoeficiente() {
        return coeficiente;
    }
    /**
     * Altera o coeficiente de rendimento do estudante.
     * @param coeficiente O novo coeficiente de rendimento.
     */
    public void setCoeficiente(double coeficiente) {
        this.coeficiente = coeficiente;
    }
    /**
     * Obtém a lista de componentes curriculares cursados pelo estudante.
     * @return Um array de {@code String} com os nomes dos componentes.
     */
    public String[] getComponentes() {
        return componentes;
    }
    /**
     * Altera a lista de componentes curriculares cursados pelo estudante.
     * @param componentes O novo array de {@code String} de componentes.
     */
    public void setComponentes(String[] componentes) {
        this.componentes = componentes;
    }
    /** 
     * Verifica se este Histórico é igual a outro objeto.
     * @param obj O objeto a ser comparado com este Histórico.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contrário.    
     */
    @Override
    public boolean equals(Object obj) {

            if (obj == null) return false;
            if (obj == this) return true;
            if (obj.getClass() != this.getClass()) return false;
            if (!(super.equals(obj))) return false;
            Historico historicoTestado = (Historico) obj;
            return this.coeficiente == historicoTestado.coeficiente && DocumentoUtils.arraysDeStringSaoIguais(this.componentes, historicoTestado.componentes) ;
    }
    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link Registro},
     * bem como no coeficiente do Histórico.</p>
     *
     * @return Um valor de código hash para este Histórico.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coeficiente);
    }
}