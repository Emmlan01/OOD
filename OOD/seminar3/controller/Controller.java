package seminar3.controller;
/*
 * This is the application's only controller. All calls to the model pass through this class.
 */
import seminar3.integration.DiscountDB;
import seminar3.integration.ExternalAccountingSystem;
import seminar3.integration.ExternalInventorySystem;
import seminar3.integration.ItemDTO;
import seminar3.integration.Printer;
import seminar3.model.Sale;
import seminar3.model.SaleDTO;

public class Controller {
    private ExternalAccountingSystem externalAccountingSystem;
    private ExternalInventorySystem externalInventorySystem;
    private Sale sale;
    private DiscountDB discountDB;
  
    /**
     * Creates a new instance of Controller.
     * 
     * @param externalInventorySystem  The reference to external inventory system.
     * @param externalAccountingSystem The reference to external accounting system.
     * @param discountDB               The reference to discount database.
     */
    public Controller(ExternalInventorySystem externalInventorySystem, ExternalAccountingSystem externalAccountingSystem,
        DiscountDB discountDB) {
      this.externalInventorySystem = externalInventorySystem;
      this.externalAccountingSystem = externalAccountingSystem;
      this.discountDB = discountDB;
    }
  
    /*
     * Starts a new sale. This method must be called before doing anything else
     * during a sale.
     */
    public void startNewSale() {
      this.sale = new Sale();
    }
  
    /**
     * Adds an item to the sale if the item ID is valid.
     * 
     * @param itemId   The ID of the item to add.
     * @param quantity The quantity of the item to add.
     * @return A SaleDTO representing the updated sale.
     */
    public SaleDTO addItem(int itemId, int quantity) {
      if (externalInventorySystem.validItemId(itemId)) {
        ItemDTO item = externalInventorySystem.getItem(itemId);
        return sale.addItem(item, quantity);
      }
      return null;
    }
  
    /**
     * Applies a discount to the current sale based on customerId.
     * 
     * @param customerId The ID of the customer.
     */
    public void applyDiscount(int customerId) {
      sale.applyDiscount(customerId, discountDB);
    }
  
    /*
     * Ends the current sale.
     */
    public void endSale() {
      sale.endSale();
    }
  
    /**
     * Handles payment for the sale and prints the receipt
     * 
     * @param amountPaid The amount paid by the customer
     */
    public void pay(double amountPaid) {
      Printer changeAmount = sale.pay(amountPaid);
      externalAccountingSystem.updateSaleInformation(sale);
      externalInventorySystem.updateSaleInformation(sale);
      changeAmount.printReceipt();
    }
  
    /**
     * Gets the total price of the current sale after possible discounts.
     * 
     * @return The total price to be paid.
     */
    public double getTotalPrice() {
      return sale.getTotalPrice();
    }
  }
  