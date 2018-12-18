package com.winter;

public class Main {
    private static boolean istPalindrom(String pWord) {
        char[] word = pWord.toCharArray();
        int i1 = 0;
        int i2 = word.length - 1;
        while (i2 > i1) {
            if (word[i1] != word[i2]) {
                return false;
            }
            i1++;
            i2--;
        }
        return true;
    }

    private static void countLetters(String pWord) {
        String letters = "aeiou";
        int counters[] = {0, 0, 0, 0, 0};
        for (int i = 0; i < pWord.length(); i++) {
            for (int j = 0; j < letters.length(); j++) {
                if (pWord.charAt(i) == letters.charAt(j)) {
                    counters[j]++;
                }
            }
        }
        for (int i = 0; i < letters.length(); i++) {
            System.out.println(letters.charAt(i) + " : " + counters[i]);
        }
    }

    private static void showMatrix(int columnas, int filas) {
        boolean derecha = true, izquierda = false, abajo = false;
        int[][] matrizc = new int[filas][columnas];
        int x = 0, y = -1;

        for (int k = 1; k <= columnas * filas; k++) {
            if (izquierda) {
                y--;
                if (y == -1) {
                    y = 0;
                    x--;
                    izquierda = false;
                } else if (matrizc[x][y] != 0) {
                    y++;
                    x--;
                    izquierda = false;
                }
            } else if (derecha) {
                y++;
                if (y == columnas) {
                    y = columnas - 1;
                    x++;
                    derecha = false;
                    abajo = true;
                } else if (matrizc[x][y] != 0) {
                    y--;
                    x++;
                    derecha = false;
                    abajo = true;
                }
            } else if (abajo) {
                x++;
                if (x == filas) {
                    x = filas - 1;
                    y--;
                    abajo = false;
                    izquierda = true;
                } else if (matrizc[x][y] != 0) {
                    y--;
                    x--;
                    abajo = false;
                    izquierda = true;
                }
            } else {
                x--;
                if (x == -1 || matrizc[x][y] != 0) {
                    x++;
                    y++;
                    derecha = true;
                }
            }

            matrizc[x][y] = k;
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matrizc[i][j] + "\t");
            }
            System.out.println();
        }

        String info = filas == columnas ? "Matriz Cuadrada de " : "Matriz Rectangular de ";
        System.out.println("[" + info + columnas + "*" + filas + " = " + columnas * filas + " elementos]");
    }

    public static void main(String[] args) {
        System.out.println(Main.istPalindrom("andna"));
        Main.countLetters("android already");
        Main.showMatrix(3, 5);
    }
}
