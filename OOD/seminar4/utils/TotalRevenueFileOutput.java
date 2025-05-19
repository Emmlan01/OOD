package seminar4.utils;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Observer that logs the total revenue to a text file.
 * Each time a sale is completed, the observer updates the accumlated revenue and adds it to a log file. 
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private double total;
    private final String fileName = "totalRevenueLog.txt";

    /**
     * Updates the total revenue and writes it to a file. 
     * @param totalRevenye Thhe revenue from the sale.
     */
    @Override
    public void printTotalRevenue(double totalRevenue) {
        total += totalRevenue;
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("Total revenue: " + total + " SEK\n");
        } catch (IOException e) {
            System.out.println("Failed to write to file: " + e.getMessage());
        }
    }
}