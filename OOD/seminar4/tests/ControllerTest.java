package seminar4.tests;

import seminar4.integration.DatabaseFailureException;
import seminar4.integration.DiscountDB;
import seminar4.integration.ExternalAccountingSystem;
import seminar4.integration.ExternalInventorySystem;
import seminar4.integration.ItemNotFoundException;
import seminar4.model.SaleDTO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import seminar4.controller.Controller;

public class ControllerTest {
    private Controller controller;
    private ExternalInventorySystem externalInventorySystem = new ExternalInventorySystem();
    private ExternalAccountingSystem externalAccountingSystem = new ExternalAccountingSystem();
    private DiscountDB discountDB = DiscountDB.getInstance();

    @BeforeEach
    void setUp() {
        controller = new Controller(externalInventorySystem, externalAccountingSystem, discountDB);
    }

    @AfterEach
    void tearDown() {
        controller = null;
    }

    @Test
    public void testAddItem() throws ItemNotFoundException, DatabaseFailureException {
        controller.startNewSale();
        SaleDTO result = controller.addItem(1, 2);
        assertNotNull(result);
        assertEquals(1, result.lastAddedItem().itemId());
        assertEquals(200.0, result.totalPrice());
        assertEquals(40, result.totalVAT());
    }

    @Test
    public void testApplyDiscount() throws ItemNotFoundException, DatabaseFailureException {
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
    public void testPay() throws ItemNotFoundException, DatabaseFailureException {
        controller.startNewSale();
        controller.addItem(1, 1);
        controller.endSale();
        controller.pay(500);

        assertDoesNotThrow(() -> controller.pay(500), "payment should process");
    }

    @Test 
    public void testGetItemThrowsItemNotFoundException(){
        assertThrows(ItemNotFoundException.class, () -> {
            externalInventorySystem.getItem(10);
        });
    }

    @Test 
    public void testGetItemThrowsDatabaseFailureException(){
        assertThrows(DatabaseFailureException.class, () -> {
            externalInventorySystem.getItem(100);
        });
    }
}