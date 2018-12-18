package com.winter;

public class ExamenAdsib {
    private static String concat(String pName, String pTc) {
        String[] stringWithoutSpaces = pName.split(" ");
        StringBuilder result = new StringBuilder();
        result.append("nom::");
        for (String string : stringWithoutSpaces) {
            result.append(string);
        }
        result.append(";tc::");
        result.append(pTc);
        return result.toString();

    }

    public static void showCharMatrix(char[][] pMatrix) {
        for (char[] value : pMatrix) {
            for (int j = 0; j < pMatrix.length; j++) {
                System.out.print("\t" + value[j]);
            }
            System.out.println();
        }
    }

    private static char[][] generateMatrix(String pName, String pTc) {
        int stringLength = ExamenAdsib.concat(pName, pTc).length();
        int squarePerfect = ((int) Math.sqrt(stringLength)) + 1;
        String concatValue = ExamenAdsib.concat(pName, pTc);
        char[] arrayChars = concatValue.toCharArray();
        char[][] matrix = new char[squarePerfect][squarePerfect];
        int h = 0;
        for (int i = 0; i < squarePerfect; i++) {
            for (int j = 0; j < squarePerfect; j++) {
                // fill values into matrix
                if (h != arrayChars.length) {
                    matrix[i][j] = arrayChars[h];
                    h++;
                } else {
                    matrix[i][j] = '@';
                }
            }
        }
        return matrix;
    }

    private static String encrypt(char[][] pMatrix) {
        StringBuilder sbForRows = new StringBuilder();
        for (char[] rowData : pMatrix) {
            for (char cellData : rowData) {
                sbForRows.append(cellData);
            }
        }
        StringBuilder sbForColumns = new StringBuilder();
        for (int i = 0; i < pMatrix.length; i++) {
            for (char[] cellData : pMatrix) {
                sbForColumns.append(cellData[i]);
            }
        }
        return sbForColumns.toString();
    }

    private static void decrypt(String pEncryptedWord) {
        char[] arrayChars = pEncryptedWord.toCharArray();
        int dimen = (int) Math.sqrt(arrayChars.length);
        char[][] matrix = new char[dimen][dimen];
        int k = 0;
        // normal fill i rows; j columns;
        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++) {
                matrix[i][j] = arrayChars[k];
                k++;
            }
        }
        StringBuilder sbForColumns = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (char[] cellData : matrix) {
                sbForColumns.append(cellData[i]);
            }
        }
        String[] values = sbForColumns.toString().split("::");
        String name = values[1].substring(0, values[1].length() - 3);

        StringBuilder card = new StringBuilder();
        for (int i = 0; i < values[2].length(); i++) {
            if (values[2].charAt(i) != '@') {
                card.append(values[2].charAt(i));
            }
        }

        StringBuilder finalName = new StringBuilder();
        char[] charNames = name.toCharArray();
        finalName.append(charNames[0]);
        for (int i = 1; i < charNames.length; i++) {
            if (Character.isUpperCase(charNames[i])) {
                finalName.append(' ');
                finalName.append(charNames[i]);
            } else {
                finalName.append(charNames[i]);
            }
        }
        System.out.println("Nombre: " + finalName);
        System.out.println("Tarjeta de credito: " + card);
//        return sbForColumns.toString();
    }

    public static void main(String[] args) {
        String name = "Roberto Perez Sanjinez";
        String tc = "145-123-789";
        System.out.println("A) INFORMACION CONCATENADA");
        System.out.println(ExamenAdsib.concat(name, tc));
        System.out.println("B) MATRIZ CUADRADA");
        ExamenAdsib.showCharMatrix(generateMatrix(name, tc));
        System.out.println("C) INFORMACION CIFRADA");
        System.out.println(ExamenAdsib.encrypt(generateMatrix(name, tc)));
        System.out.println("D) DESCIFRANDO MENSAJE");
//        System.out.println(decrypt("nbri:2@oeen:3@mrze1-@:tSz47@:oa;58@RPnt-9@oejc1@@"));
        ExamenAdsib.decrypt("nbri:2@oeen:3@mrze1-@:tSz47@:oa;58@RPnt-9@oejc1@@");
    }
}
