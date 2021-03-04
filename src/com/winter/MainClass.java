package com.winter;

import java.util.LinkedHashMap;
import java.util.Vector;

public class MainClass {

    public static void countWordsFromArray() {
        Vector<String> v = new Vector<>();
        v.addElement("hello");
        v.addElement("world");
        v.addElement("this");
        v.addElement("is");
        v.addElement("this");
        v.addElement("hello");
        System.out.println(v);

        LinkedHashMap<String, Integer> s = new LinkedHashMap<>();
        for (int i = 0; i < v.size(); i++) {
            int count = 1;
            for (int j = i + 1; j < v.size(); j++) {
                if (v.get(i) == v.get(j)) {
                    count++;
                    v.remove(j);
                }
            }
            s.put(v.get(i), count);
        }
        System.out.println(s);
    }

    /*
     *
     * */

    public static void triangularArray() {
        final int NMAX = 5;
        int[][] odds = new int[NMAX + 1][];

        for (int n = 0; n <= NMAX; n++)
            odds[n] = new int[n + 1];

        // fill triangular array
        for (int n = 0; n < odds.length; n++) {
            for (int k = 0; k < odds[n].length; k++) {
                System.out.printf("%4d", odds[n][k]);
                // compute binomial coefficient n*(n-1)*(n-2)
                int loterryOdds = 1;
                for (int i = 1; i <= k; i++)
                    loterryOdds = loterryOdds * (n - i + 1) / i;
                odds[n][k] = loterryOdds;
            }
            System.out.println();
        }

        // print triangular array
        for (int[] row : odds) {
            for (int odd : row)
                System.out.printf("%4d", odd);
            System.out.println();
        }
    }

    /*
     *
     * */

    public static void main(String[] args) {
//        triangularArray();
        countWordsFromArray();


        // input array
        int a[] = new int[]{2, 4, 8, 5, 12, 15, 6, 10, 7, 30, 25, 43, 46, 45, 21};
        int count = 0;

        for (int h = 0; h < a.length; h++) {
            if (a[h] % 5 == 0) {
                count++;
            }
        }

        System.out.println("Total multiplos de 5: " + count);

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int tmp;
                if (a[i] % 5 == 0) {
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }

        // output array, all multiples of 5 are moved to last
        for (int k = 0; k < a.length; k++) {
            System.out.print(a[k] + " ");
        }

    }

}
