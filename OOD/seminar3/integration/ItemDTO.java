package seminar3.integration;
/**
 * Represents information about an item.
 */
public record ItemDTO(
        double price,
        int VAT,
        int itemId,
        String itemDescription) {
}