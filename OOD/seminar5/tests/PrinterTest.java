package seminar5.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import org.junit.Before;

import seminar5.integration.ItemDTO;
import seminar5.integration.Printer;
import seminar5.model.Sale;

public class PrinterTest {
    
    private ByteArrayOutputStream  outContent;
    private PrintStream originalSysOut;

     @Before
    public void setUp(){
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void cleanUpStreams(){
        System.setOut(originalSysOut);
         outContent = null;
    }

    @Test
    public void testPrintReceiptPrintsExpectedOutput() {
        ItemDTO item = new ItemDTO(100.0, 20, 1, "Apple");
        Sale sale = new Sale();
        sale.addItem(item, 2);

        double amountPaid = 300.0;
        LocalDateTime saleTime = LocalDateTime.of(2025, 5, 28, 17, 30);
        Printer printer = new Printer(sale, amountPaid, saleTime);

        printer.printReceipt();
        String output = outContent.toString();

        assertTrue(output.contains("------------------- Begin receipt -------------------"), "Expected output was not found in System.out");
        assertTrue(output.contains("Time of Sale: 2025-05-28 17:30"), "Expected output was not found in System.out");
        assertTrue(output.contains("Apple"), "Expected output was not found in System.out");
        assertTrue(output.contains("2 x 100.00"), "Expected output was not found in System.out");
        assertTrue(output.contains("200.00 SEK"), "Expected output was not found in System.out"); 
        assertTrue(output.contains("Total: 200.00 SEK"), "Expected output was not found in System.out"); 
        assertTrue(output.contains("VAT: 40.00 SEK"), "Expected output was not found in System.out");
        assertTrue(output.contains("Cash: 300.00 SEK"), "Expected output was not found in System.out");
        assertTrue(output.contains("Change: 100.00 SEK"), "Expected output was not found in System.out");
        assertTrue(output.contains("------------------- End receipt --------------------"), "Expected output was not found in System.out");
    }
}
