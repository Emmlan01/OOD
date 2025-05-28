package seminar3.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.*;

import seminar3.integration.DiscountDB;
import seminar3.integration.ItemDTO;
import seminar3.model.Sale;
import seminar3.model.SaleItems;


public class SaleTest {
    private Sale sale;
    private ItemDTO item1 = new ItemDTO(120, 20, 1, "item1");
    private ItemDTO item2 = new ItemDTO(100, 10, 2, "Item2");
    private ItemDTO item3 = new ItemDTO(100, 10, 0, "Invalid");


    @BeforeEach
    void setUp(){
        sale = new Sale();
    }

    @AfterEach
    void tearDown(){
        sale = null;
    }

    @Test
    public void testAddItemNewItemToLine(){
        sale.addItem(item1, 2);
        List<SaleItems> items = sale.getSaleItems();
        assertEquals(1, items.size(), "one item added");
        assertEquals(item1.itemId(), items.get(0).getItem().itemId(), "contains correct item");
    }

    @Test
    public void testAddItemExistingItemIncreasesQuantity(){
        sale.addItem(item1, 1);
        sale.addItem(item1, 2);
        List<SaleItems> items = sale.getSaleItems();
        assertEquals(1, items.size(), "same item" );
        assertEquals(3, items.get(0).getQuantity(), "quantity of the item");
    }

    @Test 
    public void testUpdateTotals(){
        sale.addItem(item1, 1);
        sale.addItem(item2, 2);

        double expectedPrice = (120 * 1) + (100 * 2);
        double expectedVAT = 40;

        assertEquals(expectedPrice, sale.getTotalPrice(), "total price");
        assertEquals(expectedVAT, sale.getTotalVAT(), "total VAT");
    }

    @Test
    public void testApplyDiscount(){
        sale.addItem(item1, 1);
        DiscountDB discountDB = new DiscountDB();
        sale.applyDiscount(1, discountDB);
        assertEquals(0.2, sale.getCustomerDiscountRate(), "Discount for eligible customer");
        assertEquals(0.05, sale.getTotalCostDiscountRate(), "Discount for total cost");
        assertEquals(0, sale.getItemDiscountAmount(), "no discount based on the item");
    }
}
