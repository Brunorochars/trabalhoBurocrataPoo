package estudantes.entidades;
/**
 * Utilitários para manipulação e comparação de documentos.
 *
 * <p>Esta classe fornece métodos estáticos que podem ser usados para operações comuns relacionadas a documentos.</p>
 *
 * @author [Bruno da Silva Rocha/Frederico de Oliveira]
 * @version 1.0
 * @see estudantes.entidades.Documento
 * @see estudantes.entidades.DocumentoAcademico
 * @see estudantes.entidades.Ata
 * @see estudantes.entidades.Plano
 * @see estudantes.entidades.Registro
 * @see estudantes.entidades.Portaria
 * @see estudantes.entidades.Norma
 * @see estudantes.entidades.DocumentoAdministrativo
 * 
 */
public final class DocumentoUtils {
    private DocumentoUtils() {}
    /**
     * Compara dois arrays de strings para verificar se são iguais.
     *
     * <p>Dois arrays são considerados iguais se tiverem o mesmo comprimento e
     * todos os elementos correspondentes forem iguais.</p>
     *
     * @param arr1 O primeiro array de strings a ser comparado.
     * @param arr2 O segundo array de strings a ser comparado.
     * @return {@code true} se os arrays forem iguais; {@code false} caso contrário.
     */
    public static boolean arraysDeStringSaoIguais(String[] arr1, String[] arr2) {

        if (arr1.length != arr2.length) return false;

        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) return false;
        }
        return true;
    }
}
