package seminar3.model;

import java.util.List;

import seminar3.integration.ItemDTO;

/*
 * Represents summary information about the most recently added item and the current sale totals.
 * constructors and getter methods are implicit. Records are immutable.
 */
public record SaleDTO(
    ItemDTO lastAddedItem,
    List<SaleItems> saleItems,
    double totalPrice,
    double totalVAT,
    double customerDiscountRate,
    double totalCostDiscountRate,
    double itemDiscountAmount,
    double totalDiscountAmount
) {}
