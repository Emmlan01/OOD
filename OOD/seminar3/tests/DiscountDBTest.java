package seminar3.tests;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import seminar3.integration.DiscountDB;
import seminar3.integration.ItemDTO;
import seminar3.model.SaleItems;


public class DiscountDBTest {
    DiscountDB discountDB = new DiscountDB();

    @Test
    public void testValidCustomerReturnsTrue(){
        assertTrue(discountDB.checkDiscount(1), "valid customer id for discount");
    }

    @Test 
     public void testInvalidCustomerReturnsFalse(){
        assertFalse(discountDB.checkDiscount(100), "invalid customer id for discount");
    }

    @Test
    public void testGetDiscountForCustomerForValidCustomer(){
        assertEquals(0.20, discountDB.getDiscountForCustomer(1), "the discount for a customer eligiable for discount");
    }

     @Test
    public void testGetDiscountForCustomerForInvalidCustomer(){
        assertEquals(0.0, discountDB.getDiscountForCustomer(2), "no discount for customer not eligible for discount");
    }

    @Test
    public void testGetItemDiscountWithId2(){
        List<SaleItems> items = new ArrayList<>();
        ItemDTO item = new ItemDTO(50, 10,2, "Test");
        items.add(new SaleItems(item, 1));

        double discount = discountDB.getItemDiscount(items, 1);
        assertEquals(5, discount, "The discount for a valid item id for discount");
    }

       @Test
    public void testGetItemDiscountWithNotId2ReturnsZero(){
        List<SaleItems> items = new ArrayList<>();
        ItemDTO item = new ItemDTO(50, 10,1, "Test");
        items.add(new SaleItems(item, 1));

        double discount = discountDB.getItemDiscount(items, 1);
        assertEquals(0, discount, "No item discount");
    }

    @Test 
    public void testGetTotalCostDiscountReturnsDiscount(){
        assertEquals(0.05, discountDB.getTotalCostDiscount(150.0, 1), "Total cost above 100 returns discounts");
    }

    @Test 
    public void testGetTotalCostDiscountReturnsZero(){
        assertEquals(0.0, discountDB.getTotalCostDiscount(50.0, 1), "no discount for total cost under 100");
    }

    @Test
    public void testCalculateTotalDiscountForValidCustomer(){
        List<SaleItems> items = new ArrayList<>();
        ItemDTO item = new ItemDTO(100, 20, 2, "test");
        items.add(new SaleItems(item, 1));

        double discount = discountDB.calculateTotalDiscount(1, 150.0, items);
        assertEquals(42.5, discount, "the total discount for a valid customer");
    }

     @Test
    public void testCalculateTotalDiscountForInvalidCustomer() {
        List<SaleItems> items = new ArrayList<>();
        ItemDTO item = new ItemDTO(100, 20, 2, "test");
        items.add(new SaleItems(item, 1));

        double discount = discountDB.calculateTotalDiscount(2, 150.0, items);
        assertEquals(0.0, discount, "no discount for invalid customer id");
    }

}
