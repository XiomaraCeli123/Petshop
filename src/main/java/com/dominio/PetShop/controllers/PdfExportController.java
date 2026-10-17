package com.dominio.PetShop.controllers;

import com.dominio.PetShop.models.Product;
import com.dominio.PetShop.services.PdfExportService;
import com.dominio.PetShop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
public class PdfExportController {

    @Autowired
    private PdfExportService pdfExportService;

    @Autowired
    private ProductService productService;

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf(Locale locale) throws IOException {
        List<Product> products = productService.getAllProducts();

        byte[] pdfContent = pdfExportService.generateProductPdf(products, locale);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=productos.pdf");
        headers.add("Content-Type", MediaType.APPLICATION_PDF_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}