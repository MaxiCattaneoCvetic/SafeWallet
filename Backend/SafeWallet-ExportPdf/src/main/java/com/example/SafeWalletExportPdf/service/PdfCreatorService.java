package com.example.SafeWalletExportPdf.service;


import com.example.SafeWalletExportPdf.model.TransactionInformation;
import com.lowagie.text.pdf.draw.LineSeparator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.IOException;


@Service
public class PdfCreatorService {
    public void export(HttpServletResponse response, TransactionInformation transactionInformation) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        // Agregar la imagen
        Image logo = Image.getInstance("https://wonderventures3.s3.amazonaws.com/safewallet-transaction.png");
        logo.scaleToFit(60, 60); // Escalar la imagen según tus necesidades
        logo.setAlignment(Element.ALIGN_LEFT); // Alinea la imagen al centro


        document.add(logo); // Agrega la imagen al documento PDF

        // Styles
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Font fontSubtitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontSubtitle.setSize(16);

        Font fontText = FontFactory.getFont(FontFactory.HELVETICA);
        fontText.setSize(12);

        // Header
        Paragraph header = new Paragraph();
        header.setAlignment(Paragraph.ALIGN_RIGHT);
        header.setFont(fontTitle);
        header.add("SafeWallet");
        document.add(header);

        // Transaction Details
        document.add(new Chunk(new LineSeparator()));
        Paragraph transactionTitle = new Paragraph("Comprobante de transacción", fontSubtitle);
        transactionTitle.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(transactionTitle);
        document.add(new Chunk(new LineSeparator()));

        // Origen
        Paragraph origenTitle = new Paragraph("Datos de origen", fontSubtitle);
        document.add(origenTitle);
        Paragraph origenData = new Paragraph();
        origenData.setFont(fontText);
        origenData.add("Nombre: " + transactionInformation.getNameFrom() + "\n");
        origenData.add("Cbu-Alias-Cvu:: " + transactionInformation.getAccountFrom() +  "\n");
        document.add(origenData);

        // Destino
        Paragraph destinoTitle = new Paragraph("Datos de destino", fontSubtitle);
        document.add(destinoTitle);
        Paragraph destinoData = new Paragraph();
        destinoData.setFont(fontText);
        destinoData.add("Nombre: " + transactionInformation.getNameTo() + "\n");
        destinoData.add("Cbu-Alias-Cvu:: " + transactionInformation.getAccountTo() +  "\n");
        document.add(destinoData);

        // Transacción
        Paragraph transaccionTitle = new Paragraph("Datos de la transacción", fontSubtitle);
        document.add(transaccionTitle);
        Paragraph transaccionData = new Paragraph();
        transaccionData.setFont(fontText);
        transaccionData.add("Transferencia de: $" + transactionInformation.getAmount()+ "\n");
        transaccionData.add("Fecha: " + transactionInformation.getDate() +  "\n");
        transaccionData.add("Concepto: " + transactionInformation.getDetail() + "\n\n");
        document.add(transaccionData);

        // Footer
        document.add(new Chunk(new LineSeparator()));
        Paragraph footer = new Paragraph("Gracias por ser parte de SafeWallet", fontText);
        footer.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(footer);

        document.close();

    }
}
