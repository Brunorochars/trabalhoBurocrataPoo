package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
/**
 * Representa um documento do tipo Ata de uma reunião ou evento,
 * que herda as propriedades básicas de um {@link Documento}.
 *
 * <p>Além das propriedades de um documento (criador, código de curso, número de páginas),
 * uma Ata possui um número de registro, o texto descritivo do ocorrido e uma
 * lista de pessoas presentes.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see Documento
 * @see professor.entidades.CodigoCurso
 */
public class Ata extends Documento {

    private int numero;
    private String texto;
    private String[] presentes;

    /**
     * Construtor da classe Ata.
     *
     * @param criador O nome ou identificador do criador do documento.
     * @param codigoCurso O código do curso ao qual a Ata está associada.
     * @param paginas O número de páginas que o documento possui.
     * @param numero O número sequencial/identificador único desta Ata.
     * @param texto O conteúdo descritivo da Ata (registro da reunião/evento).
     * @param presentes Um array de {@code String} contendo os nomes das pessoas presentes.
     */
    public Ata(String criador, CodigoCurso codigoCurso, int paginas, int numero, String texto, String[] presentes) {
        super(criador, codigoCurso, paginas);
        this.numero = numero;
        this.texto = texto;
        this.presentes = presentes;
    }

    /**
     * Retorna o número de registro desta Ata.
     *
     * @return O número da Ata.
     */
    public int getNumero() {
        return this.numero;
    }

    /**
     * Retorna o conteúdo textual descritivo desta Ata.
     *
     * @return O texto da Ata.
     */
    public String getTexto() {
        return this.texto;
    }

    /**
     * Retorna o array de nomes das pessoas presentes na reunião/evento.
     *
     * <p><b>Nota:</b> Há um erro de recursão no método atual, que chama a si mesmo.
     * O corpo correto deveria ser {@code return this.presentes;}.</p>
     *
     * @return O array de {@code String} dos presentes.
     */
    public String[] getPresentes() {
        return this.presentes; // CORREÇÃO SUGERIDA: O código original está chamando this.getPresentes(), causando recursão infinita.
    }

    /**
     * Define um novo número de registro para esta Ata.
     *
     * @param numero O novo número da Ata.
     * @return O novo número da Ata após a atribuição.
     */
    public int setNumero(int numero) {
        return this.numero = numero;
    }

    /**
     * Define um novo conteúdo textual para esta Ata.
     *
     * @param texto O novo texto da Ata.
     * @return O novo texto da Ata após a atribuição.
     */
    public String setTexto(String texto) {
        return this.texto = texto;
    }

    /**
     * Define um novo array de pessoas presentes para esta Ata.
     *
     * @param presentes O novo array de {@code String} dos presentes.
     * @return O novo array de presentes após a atribuição.
     */
    public String[] setPresentes(String[] presentes) {
        return this.presentes = presentes;
    }

    /**
     * Compara este objeto {@code Ata} com o objeto especificado. O resultado é
     * {@code true} se e somente se o argumento não for nulo, for da mesma classe
     * {@code Ata}, for igual na hierarquia de {@link Documento} e possuir o mesmo
     * {@code numero}, {@code texto} e conteúdo de {@code presentes}.
     *
     * @param obj O objeto a ser comparado com esta Ata.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        if (!(super.equals(obj))) {
            return false;
        }
        Ata other = (Ata) obj;
        // Assume-se que 'DocumentoUtils.arraysDeStringSaoIguais' está corretamente implementado
        return this.numero == other.numero && this.texto.equals(other.texto) && DocumentoUtils.arraysDeStringSaoIguais(this.presentes, other.presentes);
    }

    /**
     * Retorna um valor de código hash para o objeto.
     *
     * <p>O código hash é baseado no código hash da superclasse {@link Documento},
     * no {@code numero} e no {@code texto} da Ata. O array {@code presentes} não
     * está incluído na função {@code Objects.hash()} para fins de desempenho ou
     * convenção, mas é considerado no {@code equals}.</p>
     *
     * @return Um código hash para esta Ata.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numero, texto);
    }

}