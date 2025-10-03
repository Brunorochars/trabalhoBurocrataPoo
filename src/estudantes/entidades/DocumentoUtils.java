package estudantes.entidades;

public final class DocumentoUtils {
    private DocumentoUtils() {}

    public static boolean arraysDeStringSaoIguais(String[] arr1, String[] arr2) {

        if (arr1.length != arr2.length) return false;

        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) return false;
        }
        return true;
    }
}
