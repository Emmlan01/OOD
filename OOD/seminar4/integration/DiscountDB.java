package seminar4.integration;
/*
 * Represent a discount database for the different discounts.
 */

import java.util.List;

import seminar4.model.SaleItems;

public class DiscountDB {
    /*
     * Creates a new instance.
     */
    public DiscountDB() {
    }
 
    /**
     * Checks if a discount is available for a given customer.
     * 
     * @param customerId customerId The ID of the customer.
     * @return true if the customer is eligible, otherwise false.
     */
    public Boolean checkDiscount(int customerId) {
       if (customerId == 1)
          return true;
       else
          return false;
    }
 
    /**
     * Personal discount for a customer.
     * 
     * @param customerId customerId The ID of the customer.
     * @return The discount rate.
     */
    public double getDiscountForCustomer(int customerId) {
       if (checkDiscount(customerId)) {
          return 0.20;
       } else {
          return 0.0;
       }
    }
 
    /**
     * The item specific discount for a customer based on the items in the sale.
     * 
     * @param items      items The list of items in the sale.
     * @param customerId customerId The ID of the customer.
     * @return The total discount amount for the item.
     */
    public double getItemDiscount(List<SaleItems> items, int customerId) {
       double itemDiscount = 0.0;
       if (!checkDiscount(customerId))
          return 0.0;
 
       for (SaleItems item : items) {
          if (item.getItem().itemId() == 2) {
             itemDiscount += 5.0;
          }
       }
       return itemDiscount;
    }
 
    /**
     * The discount based of the total cost of the sale.
     * 
     * @param total      The toal cost before discount
     * @param customerId The ID of the customer.
     * @return The discount rate. (0.05 = 5%)
     */
 
    public double getTotalCostDiscount(double total, int customerId) {
       if (checkDiscount(customerId) && total > 100) {
          return 0.05;
       }
       return 0.0;
    }
 
    /**
     * Combines all discounts into a total amount to subtract from the sale.
     * 
     * @param customerId The ID of the customer.
     * @param totalPrice The total price of the sale before discount.
     * @param items      The items in the sale.
     * @return The total discount amount to be subtracted from the sale.
     */
    public double calculateTotalDiscount(int customerId, double totalPrice, List<SaleItems> items) {
       double customerDiscountRate = getDiscountForCustomer(customerId);
       double totalCostDiscountRate = getTotalCostDiscount(totalPrice, customerId);
       double itemDiscount = getItemDiscount(items, customerId);
 
       double percentDiscount = totalPrice * (customerDiscountRate + totalCostDiscountRate);
       return percentDiscount + itemDiscount;
    }
 
 }
 
