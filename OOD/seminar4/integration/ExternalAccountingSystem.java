package seminar4.integration;

import seminar4.model.Sale;

/*
 * Represents external accounting system.
 */
public class ExternalAccountingSystem {

    /*
     * Creates a new instance.
     */
    public ExternalAccountingSystem() {
    }
  
    /**
     * Prints a confirmation massage.
     * 
     * @param sale The sale to be completed.
     */
    public void updateSaleInformation(Sale sale) {
      System.out.println("Sale information to external accounting system");
    }
  }
  