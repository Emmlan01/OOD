package seminar5.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import seminar5.integration.DiscountDB;
import seminar5.integration.ItemDTO;
import seminar5.integration.Printer;
import seminar5.utils.TotalRevenueObserver;
import seminar5.utils.CustomerDiscountStrategy;
import seminar5.utils.DiscountStrategy;
import seminar5.utils.ItemDiscountStrategy;
import seminar5.utils.TotalCostDiscountStrategy;

/*
 * Represents the on going sale, including all purchased items
 * total cost, VAT, discounts, and payment handling.
 */
public class Sale {
    private List<SaleItems> saleItems;
    private ItemDTO lastAddedItem;
    private LocalDateTime saleTime;
    private double totalPrice;
    private double totalVAT;
    public double discountRate;
    private double customerDiscountRate;
    private double totalCostDiscountRate;
    private double itemDiscountAmount;
    private List<TotalRevenueObserver> saleObservers;

    /*
     * Creates a new instance, with current timestamp and emty item list.
     */
    public Sale() {
        this.saleTime = LocalDateTime.now();
        this.totalPrice = 0.0;
        this.totalVAT = 0.0;
        this.saleItems = new ArrayList<>();
        this.customerDiscountRate = 0.0;
        this.discountRate = 0.0;
        this.totalCostDiscountRate = 0.0;
        this.itemDiscountAmount = 0.0;
        this.saleObservers = new ArrayList<>();
    }

    /**
     * Adds an item with the specified quantity to the sale.
     * If the item is already present, increses the quantity.
     * 
     * @param item     item The item to add.
     * @param quantity The quantity of the item.
     * @return SaleDTO with last added item and updated totals.
     */
    public SaleDTO addItem(ItemDTO item, int quantity) {
        SaleItems existingLine = findLineItem(item.itemId());
        if (existingLine != null) {
            existingLine.increaseQuantity(quantity);
        } else {
            saleItems.add(new SaleItems(item, quantity));
        }

        lastAddedItem = item;
        updateTotals();
        return new SaleDTO(lastAddedItem, totalPrice, totalVAT);
    }

    /**
     * Searches for an existing item with the given item ID.
     * 
     * @param itemId The ID of the item to search for.
     * @return The SaleItems line if found, or else null.
     */
    private SaleItems findLineItem(int itemId) {
        for (SaleItems line : saleItems) {
            if (line.getItem().itemId() == itemId) {
                return line;
            }
        }
        return null;
    }

    /*
     * Update total price and VAT for all items in the sale.
     */
    private void updateTotals() {
        totalPrice = 0;
        totalVAT = 0;
        for (SaleItems line : saleItems) {
            totalPrice += line.getSubTotal();
            totalVAT += line.getVATAmount();
        }
    }

    /*
     * Ends the current sale by updating the timestamp.
     */
    public void endSale() {
        this.saleTime = LocalDateTime.now();
    }

    /**
     * Returns the total price for the sale after applying all discounts.
     * 
     * @return Returns the total price of the sale.
     */
    public double getTotalPrice() {
        double discountedPrice = totalPrice * (1 - discountRate);
        return discountedPrice;
    }

    /**
     * Returns the total VAT for the sale.
     * 
     * @return the total VAT for the sale.
     */
    public double getTotalVAT() {
        return totalVAT;
    }

    /**
     * Applies all possible discounts from DiscountDB based on customerId.
     * 
     * @param customerId The ID of the customer.
     * @param discountDB the discount database.
     */
    public void applyDiscount(int customerId, DiscountDB discountDB) {
        DiscountStrategy customerDiscount = new CustomerDiscountStrategy(customerId);
        DiscountStrategy totalCostDiscount = new TotalCostDiscountStrategy(customerId);
        DiscountStrategy itemDiscount = new ItemDiscountStrategy(customerId);

        double totalPercent = customerDiscount.getDiscount(totalPrice, saleItems, discountDB)
                + totalCostDiscount.getDiscount(totalPrice, saleItems, discountDB);
        double percentDiscount = totalPrice * totalPercent;
        double totalDiscount = percentDiscount + itemDiscount.getDiscount(totalPrice, saleItems, discountDB);

        discountRate = totalDiscount / totalPrice;
    }

    /**
     * Returns the discount applied based on customerId
     * 
     * @return the discount applied based on customerId.
     */
    public double getCustomerDiscountRate() {
        return customerDiscountRate;
    }

    /**
     * Returns the discount applied based on total cost.
     * 
     * @return the discount applied based on total cost.
     */
    public double getTotalCostDiscountRate() {
        return totalCostDiscountRate;
    }

    /**
     * Returns the discount applied based on the items purchased.
     * 
     * @return the discount applied based on the items purchased.
     */
    public double getItemDiscountAmount() {
        return itemDiscountAmount;
    }

    /**
     * Returns the total discount amount after all discounts applied.
     * 
     * @return the total discount amount after all discounts applied.
     */
    public double getTotalDiscountAmount() {
        return totalPrice * discountRate;
    }

    /**
     * Adds the list of obsververs to the sale.
     * 
     * @param observer The list of TotalRevenueObserver that should be added to the
     *                 sale.
     */
    public void addObservers(List<TotalRevenueObserver> observers) {
        saleObservers.addAll(observers);
    }

    /**
     * Notifies all observers about the updated total revenue.
     */
    private void notifyObservers() {
        for (TotalRevenueObserver obs : saleObservers) {
            obs.calculateTotalRevenue(totalPrice);
        }
    }

    /**
     * Finalize payment to generate receipt and notifying all observers.
     * 
     * @param amountPaid the amount paid by the customer.
     * @return a Printer instance.
     */
    public Printer pay(double amountPaid) {
        notifyObservers();
        return new Printer(this, amountPaid, saleTime);
    }

    /**
     * Returns the list of all line items in the sale.
     * 
     * @return the list of all line items in the sale.
     */
    public List<SaleItems> getSaleItems() {
        return saleItems;
    }

}