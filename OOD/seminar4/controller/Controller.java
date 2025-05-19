package seminar4.controller;
import java.util.ArrayList;
import java.util.List;

import seminar4.integration.DatabaseFailureException;
/*
 * This is the application's only controller. All calls to the model pass through this class.
 */
import seminar4.integration.DiscountDB;
import seminar4.integration.ExternalAccountingSystem;
import seminar4.integration.ExternalInventorySystem;
import seminar4.integration.ItemDTO;
import seminar4.integration.ItemNotFoundException;
import seminar4.integration.Printer;
import seminar4.utils.TotalRevenueObserver;
import seminar4.model.Sale;
import seminar4.model.SaleDTO;

public class Controller {
    private ExternalAccountingSystem externalAccountingSystem;
    private ExternalInventorySystem externalInventorySystem;
    private Sale sale;
    private DiscountDB discountDB;
    private List<TotalRevenueObserver> revenueObservers = new ArrayList<>();


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
      this.sale.addObservers(revenueObservers);
    }

    /**
     * Adds an item to the sale if the item ID is valid.
     * 
     * @param itemId   The ID of the item to add.
     * @param quantity The quantity of the item to add.
     * @return A SaleDTO representing the updated sale.
     * @throws ItemNotFoundException If itemId does not exists in inventory.
     * @throws DatabaseFailureException If specific itemId is not in inventory.
     */
    public SaleDTO addItem(int itemId, int quantity) throws ItemNotFoundException, DatabaseFailureException {
      ItemDTO item = externalInventorySystem.getItem(itemId);
      return sale.addItem(item, quantity);
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

    /**
     * Adds an observer that will be notified to the observer list for sales. 
     * @param newObserver The observer to notify to the sale observer list.
     */
    public void addRevenueObserver(TotalRevenueObserver revenueObserver){
      revenueObservers.add(revenueObserver);
    }
  }
