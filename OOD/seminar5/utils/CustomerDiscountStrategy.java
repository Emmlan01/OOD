package seminar5.utils;

import java.util.List;

import seminar5.integration.DiscountDB;
import seminar5.model.SaleItems;

/**
 * A discount strategy that calculates the discount based on the customer.
 * The discount is retrieved from the DiscountDB based on customerId.
 */
public class CustomerDiscountStrategy implements DiscountStrategy {
    private final int customerId;

     /**
     * Creates a new instance
     * @param customerId the Id of the customer eligible for discount.
     */
    public CustomerDiscountStrategy(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Calculates the discount.
     * @param totalPrice The total price of the sale before disocunt.
     * @param items The list of items in the sale.
     * @param discountDB the database used to fetch discount.
     * @return The discount amount applied based on the customer.
     */
    @Override
    public double getDiscount(double totalPrice, List<SaleItems> items, DiscountDB discountDB) {
        return discountDB.getDiscountForCustomer(customerId);
    }
}
