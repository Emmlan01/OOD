package seminar4.integration;
/*
 * Represents a printer
 */

 import java.time.LocalDateTime;
 import java.time.format.DateTimeFormatter;

import seminar4.model.Sale;
import seminar4.model.SaleItems;

public class Printer {
    private Sale sale;
    private double amountPaid;
    private LocalDateTime saleTime;

    /**
     * Create a new instance.
     * 
     * @param sale       The sale to print a receipt for.
     * @param amountPaid The amount paid by the customer.
     * @param saleTime   The timestamp of the sale.
     */

    public Printer(Sale sale, double amountPaid, LocalDateTime saleTime) {
        this.sale = sale;
        this.amountPaid = amountPaid;
        this.saleTime = saleTime;
    }

    /*
     * Prints the receipt to the console.
     */
    public void printReceipt() {
        System.out.println("------------------- Begin receipt -------------------");
        System.out.println("Time of Sale: " + saleTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println();
        for (SaleItems line : sale.getSaleItems()) {
            System.out.println(line.getItem().itemDescription() + "      " + line.getQuantity() + " x " +
                    String.format("%.2f", line.getItem().price()) + " " +
                    String.format("%.2f", line.getSubTotal()) + " SEK");
        }
        System.out.println();
        System.out.println("Total discount: " + String.format("%.2f", sale.getTotalDiscountAmount()) + " SEK");
        System.out.println("Total: " + String.format("%.2f", sale.getTotalPrice()) + " SEK");
        System.out.println("VAT: " + String.format("%.2f", sale.getTotalVAT()) + " SEK");
        System.out.println();
        System.out.println("Cash: " + String.format("%.2f", amountPaid) + " SEK");
        System.out.println("Change: " + String.format("%.2f", (amountPaid - sale.getTotalPrice())) + " SEK");
        System.out.println("------------------- End receipt --------------------");
    }
}