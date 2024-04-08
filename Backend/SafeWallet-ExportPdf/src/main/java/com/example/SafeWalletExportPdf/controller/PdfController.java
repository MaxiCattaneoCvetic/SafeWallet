package com.example.SafeWalletExportPdf.controller;

import com.example.SafeWalletExportPdf.model.TransactionInformation;
import com.example.SafeWalletExportPdf.service.PdfCreatorService;
import jakarta.servlet.http.HttpServletResponse;
import org.springdoc.core.parsers.ReturnTypeParser;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/download")
public class PdfController {

    private final PdfCreatorService pdfGeneratorService;


    public PdfController(PdfCreatorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }



    @PostMapping("/pdf/generate")
    public ResponseEntity<?> generatePDF(HttpServletResponse response, @RequestBody TransactionInformation transactionInformation) throws IOException {
        try{
            response.setContentType("application/pdf");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
            response.setHeader(headerKey, headerValue);

            this.pdfGeneratorService.export(response,transactionInformation);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
