 package com.mymart.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mymart.model.OrderItem;
import com.mymart.model.Orders;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private InvoiceService invoiceService;

    public void sendOrderConfirmationEmail(String userEmail, Orders orders) throws MessagingException, InvalidImageFormatException {
        try {
            // Generate PDF invoice and save it to a file
            String pdfPath = invoiceService.generatePdfInvoice(orders);

            // Create a MimeMessage
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(userEmail);
            helper.setSubject("MyMart Order Confirmation - Order #" + orders.getOrderNumber());

            // Start building the HTML body
            StringBuilder emailBody = new StringBuilder();
            emailBody.append("<html><body>");
            emailBody.append("<h1>Thank you for your order!</h1>");
            emailBody.append("<p>Your order has been successfully placed. Order details:</p>");
            emailBody.append("<p><strong>Order Number:</strong> ").append(orders.getOrderNumber()).append("</p>");
            emailBody.append("<p><strong>Total Amount:</strong> $").append(orders.getTotalAmount()).append("</p>");
            
            // Add tracking link
            String trackingLink = "http://localhost:8080/trackOrder/" + orders.getOrderNumber();
            emailBody.append("<p><a href='").append(trackingLink).append("'>For more details and to track your order, click here.</a></p>");

            emailBody.append("<h2>Product Details:</h2>");
            emailBody.append("<ul>");

            for (OrderItem item : orders.getOrderItems()) {
                emailBody.append("<li>");
                emailBody.append("<img src='cid:").append(item.getProduct().getImageFileName()).append("' alt='Product Image' style='max-height: 100px; max-width: 100px;' /><br/>");
                emailBody.append("<strong>Product Name:</strong> ").append(item.getProduct().getName()).append("<br/>");
                emailBody.append("<strong>Quantity:</strong> ").append(item.getQuantity()).append("<br/>");
                emailBody.append("<strong>Price:</strong> $").append(item.getProduct().getPrice()).append("<br/>");
                emailBody.append("<strong>Total Price:</strong> $").append(item.getTotalPrice()).append("<br/>");
                emailBody.append("</li>");
            }

            emailBody.append("</ul>");
            emailBody.append("<p>Thank you for shopping with us!</p>");
            emailBody.append("<p>MyMart Team</p>");
            emailBody.append("</body></html>");

            helper.setText(emailBody.toString(), true); // true indicates HTML

            // Embed product images as inline resources
            for (OrderItem item : orders.getOrderItems()) {
                File imageFile = new File("public/images/" + item.getProduct().getImageFileName());
                if (imageFile.exists()) {
                    Path imagePath = imageFile.toPath();
                    String contentType = Files.probeContentType(imagePath);
                    if (!isValidImageFormat(contentType)) {
                        throw new InvalidImageFormatException("Invalid image format: " + contentType);
                    }
                    InputStreamSource imageSource = new ByteArrayResource(Files.readAllBytes(imagePath));
                    helper.addInline(item.getProduct().getImageFileName(), imageSource, contentType);
                }
            }

            // Attach the PDF invoice
            File pdfFile = new File(pdfPath);
            helper.addAttachment("Invoice.pdf", pdfFile);

            // Send the email
            emailSender.send(message);

        } catch (MessagingException e) {
            throw e; // Rethrow MessagingException to be handled in the controller
        } catch (Exception e) {
            throw new MessagingException("Failed to send order confirmation email", e); // Wrap other exceptions in MessagingException
        }
    }

    private boolean isValidImageFormat(String contentType) {
        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/gif"));
    }

    public class InvalidImageFormatException extends IOException {
        private static final long serialVersionUID = 1L;

        public InvalidImageFormatException(String message) {
            super(message);
        }
    }
}
