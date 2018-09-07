package com.example.demo.util.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringReader;
import java.util.Map;

public class UsuarioEntityPDFView extends AbstractPDFView {

//    @Override
//    protected void buildPdfDocument(Map<String, Object> model,
//                                    Document document,
//                                    PdfWriter writer,
//                                    HttpServletRequest request,
//                                    HttpServletResponse response) throws Exception {
//
//        List<UsuarioEntity> usuarioEntityList = (List<UsuarioEntity>) model.get("usuarios");
//
//        document.add(new Paragraph("Reporte lista de usuarios, generado en: " + LocalDate.now()));
//
//        PdfPTable table = new PdfPTable(4);
//        table.setWidthPercentage(100);
//        table.setSpacingBefore(10);
//
//        // define font for table header row
//        Font font = FontFactory.getFont(FontFactory.HELVETICA);
//        font.setColor(BaseColor.WHITE);
//
//        // define table header cell
//        PdfPCell cell = new PdfPCell();
//        cell.setBackgroundColor(BaseColor.DARK_GRAY);
//        cell.setPadding(5);
//
//        // write table header
//        cell.setPhrase(new Phrase("ID", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("ACTIVO", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("NOMBRE USUARIO", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("CORREO ELECTRÓNICO", font));
//        table.addCell(cell);
//
//        // add all elements from usuarioEntityList
//        for (UsuarioEntity usuario : usuarioEntityList) {
//            table.addCell(usuario.getId().toString());
//            table.addCell(Boolean.toString(usuario.isActive()));
//            table.addCell(usuario.getUsername());
//            table.addCell(usuario.getEmail());
//
//            table.completeRow();
//        }
//
//
//
//        PdfPCell cell6 = new PdfPCell(new Phrase("Celda 6"));
//        cell6.setColspan(3);
//        table.addCell(cell6);
//        table.completeRow();
//
////        PdfPCell cell7 = new PdfPCell(new Phrase("Celda 7"));
////        cell7.setColspan(3);
////        table.addCell(cell7);
////        PdfPTable ad = new PdfPTable()
////
////        table.completeRow();
////
////        table.ad
//        document.add(table);
//    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document,
                                    PdfWriter writer,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        String k = "<html><body bgcolor='#E6E6FA'> <table border='1'>\n" +
                "  <tr>\n" +
                "    <th>LOGO - AJ</th>\n" +
                "    <th>FORMULARIO DE DENUNCIAS</th> \n" +
                "    <th>LOGO - ESCUDO</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td bgcolor='red'>DATOS DEL DENUNCIADO</td>\n" +
                "    <td></td>\n" +
                "    <td></td>\n" +
                "  </tr>\n" +
                "</table> </body></html>";

//        String fileName = "templates/forgotPassword.html";
//        HTMLWorker htmlWorker = new HTMLWorker(document);
//
//        ClassLoader classLoader = new UsuarioEntityPDFView().getClass().getClassLoader();
//        File file = new File(classLoader.getResource(fileName).getFile());
//        String content = new String(Files.readAllBytes(file.toPath()));
//        htmlWorker.parse(new StringReader(content));
//        document.close();



        document.open();
        HTMLWorker htmlWorker = new HTMLWorker(document);
        htmlWorker.parse(new StringReader(k));
        document.close();

    }


}
