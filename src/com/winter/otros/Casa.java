package com.winter.otros;

import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

// -----------------------

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

import java.util.Base64;

import java.util.UUID;

import java.util.ArrayList;
import java.util.Collections;

import java.util.concurrent.ThreadLocalRandom;

import java.util.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import java.nio.ByteBuffer;
import java.io.UnsupportedEncodingException;

public class Casa {
    String b;

    Casa(String p) {
        b = p;
    }

    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (this.getClass() == obj.getClass()) {
            Casa billingAddress = (Casa) obj;
            if ((billingAddress.b).equals(this.b) /*&& (billingAddress.lastName).equals(this.lastName)*/) {
                isEqual = true;
            }
        }
        return isEqual;
    }


    public static void main(String args[]) {

		/*
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		System.out.println("PEPE" + timestamp);

		Date mDate = new Date();
		SimpleDateFormat mDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		mDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		System.out.println("UTC Time is: " + mDateFormat.format(mDate));


		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		System.out.println("UTC Time is: " + dateFormat.format(date));


		System.out.println("\n");
		KeyPair keyPair = getKeyPair();

        byte[] data = "test".getBytes("UTF8");

        Signature sig = Signature.getInstance("SHA1WithRSA");
        sig.initSign(keyPair.getPrivate());
        sig.update(data);
        byte[] signatureBytes = sig.sign();
        System.out.println("Signature:" + Base64.getDecoder().encode(signatureBytes));

        sig.initVerify(keyPair.getPublic());
        sig.update(data);
        System.out.println("verificando");
        System.out.println(sig.verify(signatureBytes));
        */

		/*
        Casa a1 = new Casa("somos tu y yo");
        Casa a2 = new Casa("somos tu y yo menos yo");
        System.out.println("IGUALES: " + a1.equals(a2));
		*/

        final String uuid = UUID.randomUUID().toString();//.replace("-", "");
        System.out.println("uuid = " + uuid);

        String ts = String.valueOf(System.currentTimeMillis());
        System.out.println("uuid-ts = " + ts);
        System.out.println("uuid-ts = " + ts.substring(ts.length() - 7));

        System.out.println("====");


        char example = '1';
        System.out.println("new1 " + generar());
        System.out.println("new2 " + generar());
        System.out.println("EXTENSION " + "wilmer.jpg".substring("wilmer.jpg".length() - 3));
        System.out.println("CADEñA " + example);

        StringBuilder builder = new StringBuilder();
        builder.append("Señor: ");
        builder.append("Pepe\n");
        builder.append("Agradecemos su comunicación con la Institución, respecto a su consulta, nos encontramos gestionando la respuesta que satisfaga sus expectativas, la cual será remitida vía este medio, en fecha: ");
        builder.append("05/05/2005\n\n");
        builder.append("Atentamente\n\n");
        builder.append("Autoridad de Fiscalización del Juego");

        System.out.println("RESULT " + builder.toString());

        metodo(currentDate());

// UTF-8
        String utf = "ñandu";
        byte ptext[] = new byte[0];
        try {
            ptext = utf.getBytes("ISO-8859-1");
            String value = new String(ptext, "UTF-8");
            System.out.println("GOOGLE: " + value);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
// UTF-8

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive

/*
for (int i = 0; i<= 55; i++) {
	System.out.println(randomMe(0, 8));
	
}
*/

    }

    public static void pepe() {
        String utf = "ñandu";

// convert the input string to a character array
        char[] chars = utf.toCharArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            int unipoint = Character.codePointAt(chars, i);
            if ((unipoint < 32) || (unipoint > 127)) {
                StringBuilder hexString = new StringBuilder();
                for (int k = 0; k < 4; k++) // 4 times to build a 4-digit hex
                {
                    hexString.insert(0, Integer.toHexString(unipoint % 16));
                    unipoint = unipoint / 16;
                }
                sb.append("\\u" + hexString);
            } else {
                sb.append(chars[i]);
            }
        }

//        System.out.println("PEPE: " + sb.toString());

    }

    public static String currentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println("ACTUAL: " + dateFormat.format(date));
        return dateFormat.format(date);
    }

    public static void metodo(String pPar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(pPar));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, 5);
        System.out.println("MMM: " + dateFormat.format(calendar.getTime()));
    }

    public static String generar() {
        String ts = String.valueOf(System.currentTimeMillis());
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        //System.out.print(list.toString() + "\n");
        String cadena = "";
        cadena = list.get(randomMe(0, 7)).toString() + list.get(randomMe(0, 7)).toString() + list.get(randomMe(0, 7)).toString();
        return cadena + ts.substring(ts.length() - 7);
    }


    public static int randomMe(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }



/*

	private static KeyPair getKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        return kpg.genKeyPair();
    }
    */

    class Pepe {
        String a;

        Pepe(String pA) {
            this.a = pA;
        }
    }
}