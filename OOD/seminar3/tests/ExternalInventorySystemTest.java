package seminar3.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import seminar3.integration.ExternalInventorySystem;
import seminar3.integration.ItemDTO;


public class ExternalInventorySystemTest {
    private ExternalInventorySystem externalInventorySystem;

    @BeforeEach
    public void setUp() {
        externalInventorySystem = new ExternalInventorySystem();
    }

    @AfterEach
    public void tearDown() {
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
    public void testGetItemReturnsCorrectItem() {
        ItemDTO item = externalInventorySystem.getItem(1);
        assertNotNull(item);
        assertEquals(100, item.price());
        assertEquals(20, item.VAT());
        assertEquals(1, item.itemId());
        assertEquals("Apple", item.itemDescription());
        ;
    }
}

