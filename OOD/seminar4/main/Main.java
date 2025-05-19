package seminar4.main;


import seminar4.controller.Controller;
import seminar4.integration.DiscountDB;
import seminar4.integration.ExternalAccountingSystem;
import seminar4.integration.ExternalInventorySystem;
import seminar4.view.View;

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
        view.addItem(100, 1);
        view.addItem(8, 2);
    //    view.applyDiscount(1);
        view.endSale();
        view.pay(900);
    }
}
