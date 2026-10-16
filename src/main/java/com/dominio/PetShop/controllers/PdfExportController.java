package com.dominio.PetShop.controllers;

import com.dominio.PetShop.models.Product;
import com.dominio.PetShop.services.PdfExportService;
import com.dominio.PetShop.services.ProductService;  // Asegúrate de tener el servicio que obtiene los productos
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class PdfExportController {

    @Autowired
    private PdfExportService pdfExportService;

    @Autowired
    private ProductService productService;  // Servicio para obtener productos desde la base de datos

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws IOException {
        // Obtener productos desde la base de datos
        List<Product> products = productService.getAllProducts();

        // Generar el PDF con los productos obtenidos
        byte[] pdfContent = pdfExportService.generateProductPdf(products);

        // Configurar las cabeceras para la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=productos.pdf");
        headers.add("Content-Type", MediaType.APPLICATION_PDF_VALUE);

        // Retornar el archivo PDF como respuesta
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
