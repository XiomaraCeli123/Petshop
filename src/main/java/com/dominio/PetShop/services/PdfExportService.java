package com.dominio.PetShop.services;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.geom.PageSize;
import com.dominio.PetShop.models.Product;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PdfExportService {

    // Modificar este método para que reciba una lista de productos
    public byte[] generateProductPdf(List<Product> products) throws IOException {
        // Crea un flujo de salida en memoria
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Crea el documento PDF
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        // Título del documento
        document.add(new Paragraph("Lista de Productos para animales"));

        // Agregar tabla de productos (7 columnas)
        Table table = new Table(7); // 7 columnas (ajustado a los campos de la tabla)
        table.addHeaderCell("ID");
        table.addHeaderCell("Marca");
        table.addHeaderCell("Categoría");
        table.addHeaderCell("Descripción");
        table.addHeaderCell("Precio");
        table.addHeaderCell("Fecha Creación");
        table.addHeaderCell("Imagen");

        // Formato para la fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Agregar los productos de la lista a la tabla
        for (Product product : products) {
            table.addCell(String.valueOf(product.getId()));  // ID del producto
            table.addCell(product.getBrand());  // Marca del producto
            table.addCell(product.getCategory());  // Categoría del producto
            table.addCell(product.getDescription() != null ? product.getDescription() : "N/A");  // Descripción (maneja nulls)
            table.addCell(String.format("$%.2f", product.getPrice()));  // Precio del producto, formato de moneda
            table.addCell(dateFormat.format(product.getCreatedAt()));  // Fecha de creación
            table.addCell(product.getImageFileName() != null ? product.getImageFileName() : "N/A");  // Nombre del archivo de imagen
        }

        document.add(table);

        // Cerrar el documento
        document.close();

        // Retornar el PDF como un array de bytes
        return byteArrayOutputStream.toByteArray();
    }
}
