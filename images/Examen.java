package examen;

/**
 *
 * @author wilmer
 */
public class Examen {

    public static String concatenar(String pNom, String pTc) {
        StringBuilder sb = new StringBuilder();
        sb.append("nom::");
        String[] tokens = pNom.split(" ");
        for (String token : tokens) {
            sb.append(token);
        }
        sb.append(";tc::");
        sb.append(pTc);
        return sb.toString();
    }

    public static void generarMatriz(String pNom, String pTc) {
        int tamanio = Examen.concatenar(pNom, pTc).length();
        int cuadrado = ((int) Math.sqrt(tamanio)) + 1;
        String concatenado = Examen.concatenar(pNom, pTc);
        char[] chars = concatenado.toCharArray();
        char[][] matriz = new char[cuadrado][cuadrado];
        int k = 0;
        for (int i = 0; i < cuadrado; i++) {
            for (int j = 0; j < cuadrado; j++) {
                if (k != chars.length) {
                    matriz[i][j] = chars[k];
                    k++;
                } else {
                    matriz[i][j] = '@';
                }
            }
        }
        mostrarMatriz(matriz);
    }

    public static String cifrar(String pNom, String pTc) {
        int tamanio = Examen.concatenar(pNom, pTc).length();
        int cuadrado = ((int) Math.sqrt(tamanio)) + 1;
        String concatenado = Examen.concatenar(pNom, pTc);
        char[] chars = concatenado.toCharArray();
        char[][] matriz = new char[cuadrado][cuadrado];
        int k = 0;
        for (int i = 0; i < cuadrado; i++) {
            for (int j = 0; j < cuadrado; j++) {
                if (k != chars.length) {
                    matriz[i][j] = chars[k];
                    k++;
                } else {
                    matriz[i][j] = '@';
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (char[] matriz1 : matriz) {
                sb.append(matriz1[i]);
            }
        }
        return sb.toString();
    }

    public static void mostrarMatriz(char[][] pMatrix) {
        for (char[] pMatrix1 : pMatrix) {
            for (int j = 0; j < pMatrix.length; j++) {
                System.out.print("\t" + pMatrix1[j]);
            }
            System.out.println();
        }
    }

    public static void descifrar(String message) {
        char[] chars = message.toCharArray();
        int raiz = (int) Math.sqrt(chars.length);
        char[][] matriz = new char[raiz][raiz];
        int k = 0;
        for (int i = 0; i < raiz; i++) {
            for (int j = 0; j < raiz; j++) {
                matriz[i][j] = chars[k];
                k++;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (char[] matriz1 : matriz) {
                builder.append(matriz1[i]);
            }
        }
        String deNuevo = builder.toString();
        String[] tokens = deNuevo.split("::");
        String name = tokens[1].substring(0, tokens[1].length() - 3);
        String card = "";
        for (int i = 0; i < tokens[2].length(); i++) {
            if (tokens[2].charAt(i) != '@') {
                card = card + tokens[2].charAt(i);
            }
        }
        System.out.println("Nombre: " + name);
        System.out.println("Tarjeta de Credito: "+ card);
    }

    public static void main(String[] args) {
        // DATOS DE ENTRADA
        String nom = "Roberto Perez Sanjinez";
        String tc = "145-123-789";

        // SALIDAS
        System.out.println("INFORMACION CONCATENADA");
        String concatenado = Examen.concatenar(nom, tc);
        System.out.println("\n" + concatenado);

        System.out.println("\nMATRIZ CUADRADA\n");
        generarMatriz(nom, tc);

        System.out.println("\nINFORMACION CIFRADA\n");
        String message = cifrar(nom, tc);
        System.out.println(message);

        System.out.println("\nDESCIFRANDO MENSAJE\n");
        descifrar("nbri:2@oeen:3@mrze1-@:tSz47@:oa;58@RPnt-9@oejc1@@");
    }

}
