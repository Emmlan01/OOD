package se.kth.iv1350.sem3.integration;
/**
 * Represents information about an item.
 */
public record ItemDTO(
    double price, 
    int VAT, 
    int itemId, 
    String itemDescription) { }