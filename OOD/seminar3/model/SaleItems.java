package seminar3.model;

import seminar3.integration.ItemDTO;

/*
 * Represents the item and the quantity of it, to caluclate
 * subtotal and VAT for that specific item.
 */
public class SaleItems {

    private ItemDTO item;
    private int quantity;

    /**
     * Create a new line item with specificed item and quantity.
     * 
     * @param item     The item.
     * @param quantity The quantity of the item.
     */
    public SaleItems(ItemDTO item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Increases the quantity of the item.
     * 
     * @param quantity The quantity of the item.
     */
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    /**
     * @return the item.
     */
    public ItemDTO getItem() {
        return item;
    }

    /**
     * @return the subtotal for the item.
     */
    public double getSubTotal() {
        return item.price() * quantity;
    }

    /**
     * @return the VAT amount for the item.
     */
    public double getVATAmount() {
        return getSubTotal() * item.VAT() / 100.0;
    }

    /**
     * @return the quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }
}

