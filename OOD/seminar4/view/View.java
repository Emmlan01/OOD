package seminar4.view;

/*
 * This is a placeholder for the real view, and represents the view of the program.
 */
import seminar4.model.SaleDTO;
import seminar4.controller.Controller;
import seminar4.integration.ItemDTO;

 public class View {
    private Controller contr;

    /**
     * Creates a new instance, that uses the specified controller for all calls to
     * other layers.
     * 
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /*
     * Starts a new sale by calling the controller.
     */
    public void startNewSale() {
        contr.startNewSale();
    }

    /**
     * Adds an item with the itemId and quantity to the current sale,
     * and prints sale information to the console.
     * 
     * @param itemId   The ID of the item to add.
     * @param quantity The number of quantity of the item.
     */
    public void addItem(Integer itemId, int quantity) {
        SaleDTO saleInfo = contr.addItem(itemId, quantity);
        if (saleInfo != null) {
            ItemDTO item = saleInfo.lastAddedItem();
            System.out.println("Add " + quantity + " item with item id " + itemId + " :");
            System.out.println("Item ID : " + item.itemId());
            System.out.println("Item name : " + item.itemDescription());
            System.out.println("Item cost : " + item.price() + " SEK");
            System.out.println("VAT : " + item.VAT() + "%");
            System.out.println("Item description : " + item.itemDescription());
            System.out.println();
            System.out.println("Total cost (incl VAT): " + String.format("%.2f", saleInfo.totalPrice()) + " SEK");
            System.out.println("Total VAT: " + String.format("%.2f", saleInfo.totalVAT()) + " SEK");
            System.out.println();
            System.out.println("End sale:");
            System.out.println("Total cost (incl VAT): " + String.format("%.2f", saleInfo.totalPrice()) + " SEK");
        }
        if (saleInfo == null) {
            System.out.println("Identifier is invalid");
        }

    }

    /**
     * Calling the controller to possible apply discount for a specific customer.
     * 
     * @param customerId The Id of the customer for who the discount can be applied
     *                   to.
     */
    public void applyDiscount(int customerId) {
        contr.applyDiscount(customerId);
    }

    /*
     * Ends the current sale by calling the controller.
     */
    public void endSale() {
        contr.endSale();
    }

    /**
     * The payment for the sale and calling controller to print the receipt.
     * 
     * @param amountPaid The amount paid by the customer.
     */
    public void pay(double amountPaid) {
        contr.pay(amountPaid);
    }

}

