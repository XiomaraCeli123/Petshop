package com.dominio.PetShop.services;

import com.dominio.PetShop.models.Product;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Service
public class PdfExportService {

    @Autowired
    private MessageSource messageSource;

    private String t(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    public byte[] generateProductPdf(List<Product> products, Locale locale) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        document.add(new Paragraph(t("pdf.title", locale)));

        Table table = new Table(UnitValue.createPercentArray(new float[]{5, 13, 12, 12, 24, 9, 13, 12}));
        table.useAllAvailableWidth();

        table.addHeaderCell(t("pdf.id", locale));
        table.addHeaderCell(t("pdf.name", locale));
        table.addHeaderCell(t("pdf.brand", locale));
        table.addHeaderCell(t("pdf.category", locale));
        table.addHeaderCell(t("pdf.description", locale));
        table.addHeaderCell(t("pdf.price", locale));
        table.addHeaderCell(t("pdf.date", locale));
        table.addHeaderCell(t("pdf.image", locale));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String na = t("pdf.na", locale);

        for (Product product : products) {
            table.addCell(String.valueOf(product.getId()));
            table.addCell(product.getName() != null ? product.getName() : na);
            table.addCell(product.getBrand() != null ? product.getBrand() : na);
            table.addCell(product.getCategory() != null ? product.getCategory() : na);
            table.addCell(product.getDescription() != null ? product.getDescription() : na);
            table.addCell(String.format(locale, "$%.2f", product.getPrice()));
            table.addCell(dateFormat.format(product.getCreatedAt()));
            table.addCell(buildImageCell(product.getImageFileName(), na));
        }

        document.add(table);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private Cell buildImageCell(String fileName, String fallback) {
        Cell cell = new Cell();
        if (fileName != null) {
            try {
                Path path = Paths.get("public/images", fileName);
                if (Files.exists(path)) {
                    Image img = new Image(ImageDataFactory.create(path.toString()));
                    img.scaleToFit(60, 60);
                    cell.add(img);
                    return cell;
                }
            } catch (Exception ex) {
                System.out.println("No se pudo cargar la imagen: " + ex.getMessage());
            }
        }
        cell.add(new Paragraph(fallback));
        return cell;
    }
}