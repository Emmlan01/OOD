package se.kth.iv1350.sem3.main;

import se.kth.iv1350.sem3.controller.Controller;
import se.kth.iv1350.sem3.integration.DiscountDB;
import se.kth.iv1350.sem3.integration.ExternalAccountingSystem;
import se.kth.iv1350.sem3.integration.ExternalInventorySystem;
import se.kth.iv1350.sem3.view.View;

/*
 * Starts the entire application, contains the main method used to start the application.
 */
public class Main {

    /**
     * The main method used to start the entire application
     * 
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        ExternalAccountingSystem externalAccountingSystem = new ExternalAccountingSystem();
        ExternalInventorySystem externalInventorySystem = new ExternalInventorySystem();
        DiscountDB discountDB = new DiscountDB();
        Controller contr = new Controller(externalInventorySystem, externalAccountingSystem, discountDB);
        View view = new View(contr);
        view.startNewSale();
        view.addItem(1, 1);
        view.addItem(2, 2);
        view.applyDiscount(1);
        view.endSale();
        view.pay(1000);
    }
}
