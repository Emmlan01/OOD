package seminar5.utils;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A concrete implementation of the abstract class.
 * Writes the total revenue to a text file.
 */
public class TotalRevenuePrint extends TotalRevenueFileOutput {
    private final String fileName = "totalRevenueLog.txt";

    /**
     * Shows the total revenue that should be displayed to a log file.
     */
    @Override
    protected void doShowTotalRevenue(double total) throws IOException {
        FileWriter writer = new FileWriter(fileName, true); 
        writer.write("Total revenue: " + total + " SEK\n");
        writer.close();
    }

    /**
     * Handle the error.
     */
    @Override
    protected void handleErrors(Exception e) {
       System.out.println("Failed to write to file: " + e.getMessage());
    }
    
}
