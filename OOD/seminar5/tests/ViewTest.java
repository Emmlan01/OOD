package seminar5.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;

import seminar5.integration.DiscountDB;
import seminar5.integration.ExternalAccountingSystem;
import seminar5.integration.ExternalInventorySystem;
import seminar5.controller.Controller;
import seminar5.view.View;

public class ViewTest {
   
    private ByteArrayOutputStream  outContent;
    private PrintStream originalSysOut;
    private View view;
    private Controller controller;
    private ExternalInventorySystem externalInventorySystem = new ExternalInventorySystem();
    private ExternalAccountingSystem externalAccountingSystem = new ExternalAccountingSystem();
    private DiscountDB discountDB = DiscountDB.getInstance();


    @Before
    public void setUp(){
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        controller = new Controller(externalInventorySystem, externalAccountingSystem, discountDB); 
        view = new View(controller);
    }

    @AfterEach
    public void cleanUpStreams(){
        System.setOut(originalSysOut);
         outContent = null;
    }

    @Test
    public void testAddItemPrintsCorrect(){
        view.startNewSale();
         view.addItem(1, 2);

         Integer itemId = 1;
        int quantity = 2;
        String description = "Apple";
        double price = 100.0;
        double totalPrice = 200.00;
        double totalvat = 40.00;
        int vat = 20;

        String result = outContent.toString();
        assertTrue(result.contains("Add " + quantity + " item with item id " + itemId + " :"), "Expected output was not found in System.out");
        assertTrue(result.contains("Item ID : " + itemId), "Expected output was not found in System.out");
        assertTrue(result.contains("Item name : " + description), "Expected output was not found in System.out");
        assertTrue(result.contains("Item cost : " + price + " SEK"), "Expected output was not found in System.out");
        assertTrue(result.contains("VAT : " + vat + "%"), "Expected output was not found in System.out");
        assertTrue(result.contains("Item description : " + description), "Expected output was not found in System.out");
        assertTrue(result.contains("Total cost (incl VAT): " + String.format("%.2f", totalPrice) + " SEK"), "Expected output was not found in System.out");
        assertTrue(result.contains("Total VAT: " + String.format("%.2f", totalvat) + " SEK"), "Expected output was not found in System.out");
        assertTrue(result.contains("End sale:"), "Expected output was not found in System.out");
        assertTrue(result.contains("Total cost (incl VAT): " + String.format("%.2f", totalPrice) + " SEK \n"), "Expected output was not found in System.out");
       
    }
}
