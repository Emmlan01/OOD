package seminar5.model;

import seminar5.integration.ItemDTO;

/*
 * Represents summary information about the most recently added item and the current sale totals.
 * constructors and getter methods are implicit. Records are immutable.
 */
public record SaleDTO(
        ItemDTO lastAddedItem,
        double totalPrice,
        double totalVAT) {
}

