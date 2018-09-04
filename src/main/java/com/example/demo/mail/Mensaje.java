package com.example.demo.mail;

import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
public class Mensaje {
    //    private String remitente;
//    private List<String> destinatarios;
//    private String asunto;
//    private String cuerpo;
    private String to_address;
    private String asunto;
    private String cuerpo;

    public void metodo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse("03-08/2018"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, 1);
        System.out.println("MMM: " + dateFormat.format(calendar.getTime()));
    }

    public String currentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date);
    }

//    public void pepe() {
//
//
////        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(utf);
//
//        String utf = "ñandu";
//        byte ptext[] = new byte[0];
//        try {
//            ptext = utf.getBytes("ISO-8859-1");
//            String value = new String(ptext, "UTF-8");
//            System.out.println("GOOGLE: " + value);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//
//// convert the input string to a character array
//        char[] chars = utf.toCharArray();
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < chars.length; i++) {
//            int unipoint = Character.codePointAt(chars, i);
//            if ((unipoint < 32) || (unipoint > 127)) {
//                StringBuilder hexString = new StringBuilder();
//                for (int k = 0; k < 4; k++) // 4 times to build a 4-digit hex
//                {
//                    hexString.insert(0, Integer.toHexString(unipoint % 16));
//                    unipoint = unipoint / 16;
//                }
//                sb.append("\\u" + hexString);
//            } else {
//                sb.append(chars[i]);
//            }
//        }
//
//        System.out.println("PEPE: " + sb.toString());
//    }

}
