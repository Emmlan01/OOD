package seminar5.integration;


/**
 * Thrown when trying to add item which is not in inventory.
 */
public class ItemNotFoundException extends Exception {
    private int itemId;
    /**
     * Creates a new instance with a message specifying
     * for which id is not in inventory. 
     * @param itemId The item id that could not be found in inventory. 
     */
    public ItemNotFoundException(int itemId) {
        super("Item of id " +itemId + " cannot be found in the inventory");
        this.itemId = itemId;
    }
}