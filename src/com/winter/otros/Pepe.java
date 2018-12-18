package com.winter.otros;

import java.text.*;
import java.time.LocalDate;
import java.util.Date;

public class Pepe {
    public static void main(String[] args) {
        loteria();

        System.out.println("Pepe");
        String unique = System.currentTimeMillis() + "";
        System.out.println(unique.substring(unique.length() - 6));
        System.out.println("PERPERP");
        //String text = "This ñandu sofía - text ! has \\ /allot # of % special % characters";
        //String text = "550990-20181006_231947[2].jpg";
        //String text = "420821-Screenshot_20181018-175919_Mundinuggets Sofía.jpg"; 
        String text = "192001-consulta via web - Aponte Mejía.docx";

        text = text.replaceAll("[^a-zA-Z0-9.]", "_");


        System.out.println(text);


        //locale();
    }

    public static void loteria() {
        final int NMAX = 10;
        // allocate triangular array
        int[][] odds = new int[NMAX + 1][];

        for (int n = 0; n <= NMAX; n++)
            odds[n] = new int[n + 1];

        // fill triangular array
        for (int n = 0; n < odds.length; n++) {
            for (int k = 0; k < odds[n].length; k++) {
                // compute binomial coefficient n*(n-1)*(n-2)
                int loterryOdds = 1;
                for (int i = 1; i <= k; i++)
                    loterryOdds = loterryOdds * (n - i + 1) / i;
                odds[n][k] = loterryOdds;
            }
        }

        // print triangular array
        for (int[] row : odds) {
            for (int odd : row)
                System.out.printf("%4d", odd);
            System.out.println();
        }
    }

    public static String formatCustom(String pString, String pComodin, String pNewComodin) {
        String[] parts = pString.split(pComodin);
        return parts[2] + pNewComodin + parts[1] + pNewComodin + parts[0];
    }


    public static void locale() {
        String dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        LocalDate localDate = LocalDate.now();
        System.out.println("dateFormat" + dateFormat);
        System.out.println("localDate" + localDate.toString());
        Date d1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("BUA_ " + formatCustom("03/08/2018", "/", "-"));

        try {
            Date date = format.parse("2018-08-13");
            System.out.println("Date" + d1.toString() + " Conv: " + date.toString());
            System.out.println(" gbg " + new Date().toString().substring(0, 10));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        //System.out.println("isNumeric " + isNumeric("1001a"));
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}


// TIPS SPRING BOOT
/*

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class })

*/