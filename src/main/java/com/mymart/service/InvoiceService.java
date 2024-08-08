package com.mymart.service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.mymart.model.OrderItem;
import com.mymart.model.Orders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

	public String generatePdfInvoice(Orders orders) {
		String currentWorkingDir = System.getProperty("user.dir");
        String invoicesDir = Paths.get(currentWorkingDir, "invoices").toString();

        File dir = new File(invoicesDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String pdfPath = Paths.get(invoicesDir, "invoice-" + orders.getOrderNumber() + ".pdf").toString();

        try {
            PdfWriter writer = new PdfWriter(pdfPath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("MyMart").setFontSize(50).setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER));
            document.add(new Paragraph("Invoice").setFontSize(40).setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER));

            document.add(new Paragraph("Order Number: " + orders.getOrderNumber()).setFontSize(26));
            document.add(new Paragraph("Order Date: " + orders.getOrderDate()).setFontSize(26));

            float[] columnWidths = {2, 4, 1, 1, 2};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell(new Cell().add(new Paragraph("Product Image")));
            table.addHeaderCell(new Cell().add(new Paragraph("Product Name")));
            table.addHeaderCell(new Cell().add(new Paragraph("Quantity")));
            table.addHeaderCell(new Cell().add(new Paragraph("Price")));
            table.addHeaderCell(new Cell().add(new Paragraph("Total Price")));

            String imagesDir = Paths.get(currentWorkingDir, "public", "images").toString();

            List<OrderItem> orderItems = orders.getOrderItems();
            for (OrderItem item : orderItems) {
                String imagePath = Paths.get(imagesDir, item.getProduct().getImageFileName()).toString();
                try {
                    ImageData imageData = ImageDataFactory.create(imagePath);
                    Image productImage = new Image(imageData).scaleToFit(100, 100);
                    table.addCell(new Cell().add(productImage));
                } catch (IOException e) {
                    table.addCell(new Cell().add(new Paragraph("No Image")));
                }

                table.addCell(new Cell().add(new Paragraph(item.getProduct().getName())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getQuantity()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getProduct().getPrice()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getTotalPrice()))));
            }

            document.add(table);

            document.add(new Paragraph("Shipping Charges: $" + orders.getShippingCharges()).setFontSize(26));
            document.add(new Paragraph("Subtotal: $" + orders.getSubtotal()).setFontSize(26));
            document.add(new Paragraph("Total Amount: $" + orders.getTotalAmount()).setFontSize(26));

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pdfPath;
    }
}
