package se.kth.iv1350.sem3.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import se.kth.iv1350.sem3.controller.Controller;
import se.kth.iv1350.sem3.integration.DiscountDB;
import se.kth.iv1350.sem3.integration.ExternalAccountingSystem;
import se.kth.iv1350.sem3.integration.ExternalInventorySystem;
import se.kth.iv1350.sem3.model.SaleDTO;

public class ControllerTests {
    private Controller controller;
    private ExternalInventorySystem externalInventorySystem = new ExternalInventorySystem();
    private ExternalAccountingSystem externalAccountingSystem = new ExternalAccountingSystem();
    private DiscountDB discountDB = new DiscountDB();

    @BeforeEach
    void setUp() {
        controller = new Controller(externalInventorySystem, externalAccountingSystem, discountDB);

    }

    @AfterEach
    void tearDown() {
        controller = null;
    }

    @Test
    public void testAddItem(){
        controller.startNewSale();
        SaleDTO result = controller.addItem(1, 2);
        assertNotNull(result);
        assertEquals(1, result.lastAddedItem().itemId());
        assertEquals(200.0, result.totalPrice());
        assertEquals(40, result.totalVAT()); 
    }

    @Test
    public void testInvalidAddItemReturnsNull(){
        SaleDTO result = controller.addItem(100, 2);
        assertNull(result);
    }

    @Test 
    public void testApplyDiscount(){
        controller.startNewSale();
        controller.addItem(2, 2);
        controller.addItem(1, 2);
        double totalPriceWithoutDiscount = controller.getTotalPrice();

        controller.startNewSale();
        controller.addItem(2, 2);
        controller.addItem(1, 2);
        controller.applyDiscount(1);
        double totalPriceWithDiscount = controller.getTotalPrice();
   
        assertTrue(totalPriceWithDiscount < totalPriceWithoutDiscount, "Discount should reduce price");
    }

    @Test 
    public void testPay(){
        controller.startNewSale();
        controller.addItem(1, 1);
        controller.endSale();
        controller.pay(500);

        assertDoesNotThrow(() -> controller.pay(500), "payment should process");
    }
}

