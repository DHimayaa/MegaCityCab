package generator;

import dao.BillingDAO;
import model.Bill;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class BillGenerator {

    private BillingDAO billingDAO;
    private String pdfDirectory;  // Directory for storing PDFs

    public BillGenerator(String pdfDirectory) {
        this.billingDAO = new BillingDAO();
        this.pdfDirectory = pdfDirectory;
    }

    // Method to generate and insert a bill for a booking
    public Bill generateBill(int bookingId, double totalAmount) {
        Timestamp billTime = new Timestamp(System.currentTimeMillis());

        // Create the bill in the database
        boolean isBillCreated = billingDAO.createBill(bookingId, totalAmount);

        if (isBillCreated) {
            // Get the generated bill from the database
            Bill bill = billingDAO.getBillByBookingId(bookingId);
            if (bill != null) {
                // Generate the PDF for the bill
                generateBillPDF(bill);
            }
            return bill;
        } else {
            return null;
        }
    }

    private void generateBillPDF(Bill bill) {
        Document document = new Document();

        try {
            // Verify if the directory exists, if not, create it
            File dir = new File(pdfDirectory);
            if (!dir.exists()) {
                System.out.println("Directory does not exist. Creating directory: " + pdfDirectory);
                dir.mkdirs();  // Creates the directory if it doesn't exist
            }

            // Define the path for saving the PDF file
            String pdfFileName = pdfDirectory + File.separator + "bill_" + bill.getBillId() + ".pdf";
            System.out.println("PDF file name: " + pdfFileName);

            // Create the PDF and write the document to the file
            PdfWriter.getInstance(document, new FileOutputStream(pdfFileName));

            document.open();
            document.add(new Paragraph("MegaCity Cab - Bill"));
            document.add(new Paragraph("Booking ID: " + bill.getBookingId()));
            document.add(new Paragraph("Total Amount: Rs " + bill.getTotalAmount()));
            document.add(new Paragraph("Bill Time: " + bill.getBillTime()));

            document.close();

            System.out.println("Bill PDF generated successfully: " + pdfFileName);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            System.err.println("Error generating the PDF: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example: pass the path to the generated_pdfs folder
        String pdfDir = System.getProperty("user.dir") + File.separator + "generated_pdfs";  // Use current working directory
        BillGenerator generator = new BillGenerator(pdfDir);

        // Example usage:
        generator.generateBill(6, 500.00);  // Generate a bill for booking ID 6 with a total amount of 500.00
    }
}
