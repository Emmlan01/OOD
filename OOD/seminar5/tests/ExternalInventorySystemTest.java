package seminar5.tests;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import seminar5.integration.DatabaseFailureException;
import seminar5.integration.ExternalInventorySystem;
import seminar5.integration.ItemDTO;
import seminar5.integration.ItemNotFoundException;
import seminar5.model.Sale;


public class ExternalInventorySystemTest {
    private ExternalInventorySystem externalInventorySystem;
    private ByteArrayOutputStream  outContent;
    private PrintStream originalSysOut;

    @Before
    public void setUp(){
        externalInventorySystem = new ExternalInventorySystem();
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void cleanUpStreams(){
        System.setOut(originalSysOut);
         outContent = null;
        externalInventorySystem = null;
    }

    @Test
    public void testValidItemIdReturnsTrue() {
        assertTrue(externalInventorySystem.validItemId(1));
    }

    @Test
    public void testInvalidItemIdReturnsFalse() {
        assertFalse(externalInventorySystem.validItemId(10));
    }

    @Test
    public void testGetItemReturnsCorrectItem() throws ItemNotFoundException, DatabaseFailureException {
        ItemDTO item = externalInventorySystem.getItem(1);
        assertNotNull(item);
        assertEquals(100, item.price());
        assertEquals(20, item.VAT());
        assertEquals(1, item.itemId());
        assertEquals("Apple", item.itemDescription());
        ;
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

       @Test
    public void testUpdateSaleInformationPrintsConfirmation() {
        Sale sale = new Sale();
        externalInventorySystem.updateSaleInformation(sale);

        String output = outContent.toString();
        assertTrue(output.contains("Sale information to external inventory system"),
            "The confirmation message should be printed to System.out.");
    }
}