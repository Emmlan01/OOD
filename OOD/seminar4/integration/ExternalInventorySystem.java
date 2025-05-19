package seminar4.integration;
/*
 * Represent external inventory system.
 */
import java.util.HashMap;
import java.util.Map;

import seminar4.model.Sale;

public class ExternalInventorySystem {
    private Map<Integer, ItemDTO> inventory = new HashMap<>();

    /**
     * Creates a new instance with pre defined items.
     */
    public ExternalInventorySystem() {
      inventory.put(1, new ItemDTO(100, 20, 1, "Apple"));
      inventory.put(2, new ItemDTO(200, 10, 2, "Banana"));
    }

    /**
     * Check if the itemId is valid and represents an actual item.
     * 
     * @param itemId The itemId represents an item to validate.
     * @return true if the item exists in the inventory, otherwise false.
     */

    public boolean validItemId(Integer itemId) {
      return inventory.containsKey(itemId);
    }

    /**
     * Gets the item information for the given itemId.
     * 
     * @param itemId The ID of the item.
     * @return An ItemDTO object with item details, or null if not found.
     * @throws ItemNotFoundException If item does not exists in inventory.
     * @throws DatabaseFailureException To simulate a database failure.
     */
    public ItemDTO getItem(Integer itemId) throws ItemNotFoundException, DatabaseFailureException {
      if (itemId == 100){
        throw new DatabaseFailureException("Database cannot be reached");
      }
        ItemDTO item = inventory.get(itemId);  
        if (item == null) {
          throw new ItemNotFoundException(itemId);
        }
        return item;
    }

    /**
     * Prints a confirmation massage.
     * 
     * @param sale The sale to be completed.
     */
    public void updateSaleInformation(Sale sale) {
      System.out.println("Sale information to external inventory system");
    }
  }