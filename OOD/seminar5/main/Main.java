package seminar5.main;

import seminar5.controller.Controller;
import seminar5.integration.DiscountDB;
import seminar5.integration.ExternalAccountingSystem;
import seminar5.integration.ExternalInventorySystem;
import seminar5.view.View;

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
        DiscountDB discountDB = DiscountDB.getInstance();
        Controller contr = new Controller(externalInventorySystem, externalAccountingSystem, discountDB);
        View view = new View(contr);
        view.startNewSale();
        view.addItem(1, 2);
      //  view.addItem(2, 2);
        view.applyDiscount(1);
        view.endSale();
        view.pay(4000);
    }
}
