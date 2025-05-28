package seminar5.utils;

import java.util.List;

import seminar5.integration.DiscountDB;
import seminar5.model.SaleItems;

public interface DiscountStrategy {
      /**
     * Calculates discount amount or rate depending on the strategy.
     * 
     * @param totalPrice The total price before discount.
     * @param items      The list of sale items.
     * @param discountDB The discount database (used to fetch applicable rates).
     * @return The discount amount or rate (interpretation depends on strategy).
     */
    double getDiscount(double totalPrice, List<SaleItems> items, DiscountDB discountDB);
}