package seminar3.integration;
/*
 * Represents a printer
 */

 import java.time.LocalDateTime;
 import java.time.format.DateTimeFormatter;

import seminar3.model.SaleDTO;
import seminar3.model.SaleItems;

 public class Printer {
     private SaleDTO saleDTO;
     private double amountPaid;
     private LocalDateTime saleTime;
 
     /**
      * Create a new instance.
      * 
      * @param saleDTO     The saleDTO to print a receipt for.
      * @param amountPaid The amount paid by the customer.
      * @param saleTime   The timestamp of the sale.
      */
 
     public Printer(SaleDTO saleDTO, double amountPaid, LocalDateTime saleTime) {
         this.saleDTO = saleDTO;
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
         for (SaleItems line : saleDTO.saleItems()) {
             System.out.println(line.getItem().itemDescription() + "      " + line.getQuantity() + " x " +
                     String.format("%.2f", line.getItem().price()) + " " +
                     String.format("%.2f", line.getSubTotal()) + " SEK");
         }
         System.out.println();
 
         if (saleDTO.customerDiscountRate() > 0 || saleDTO.totalCostDiscountRate() > 0
                 || saleDTO.itemDiscountAmount() > 0) {
             System.out.println("Discount(s):");
             if (saleDTO.customerDiscountRate() > 0) {
                 System.out.println(" - Customer discount: " + (int) (saleDTO.customerDiscountRate() * 100) + "%");
             }
             if (saleDTO.totalCostDiscountRate() > 0) {
                 System.out.println(" - Total cost discount: " + (int) (saleDTO.totalCostDiscountRate() * 100) + "%");
             }
             if (saleDTO.itemDiscountAmount() > 0) {
                 System.out.println(" - Item discount: " + String.format("%.2f", saleDTO.itemDiscountAmount()) + " SEK");
             }
             System.out.println(
                     "Total discount applied: " + String.format("%.2f", saleDTO.totalDiscountAmount()) + " SEK");
             System.out.println();
         }
 
         System.out.println("Total: " + String.format("%.2f", saleDTO.totalPrice()) + " SEK");
         System.out.println("VAT: " + String.format("%.2f", saleDTO.totalVAT()) + " SEK");
         System.out.println();
         System.out.println("Cash: " + String.format("%.2f", amountPaid) + " SEK");
         System.out.println("Change: " + String.format("%.2f", (amountPaid - saleDTO.totalPrice())) + " SEK");
         System.out.println("------------------- End receipt --------------------");
     }
 }
 