package seminar5.view;

import java.io.IOException;

import seminar5.utils.TotalRevenueFileOutput;

/**
 * A concrete class that display total revenue to the console. 
 */
public class TotalRevenueView extends TotalRevenueFileOutput {

    /**
     * Display the total revenue to the console.
     * @param total The total revenue to display.
     * @throws IOException Declared to maintain templet method.
     */
    @Override
    protected void doShowTotalRevenue(double total) throws IOException {
		System.out.println("********** Total Revenue: " + total + "SEK *********** \n");
	} 
    
    /**
     * Handle the error.
     */
    @Override 
    protected void handleErrors(Exception e) {
       System.out.println("Failed to write to console: " + e.getMessage());
    }
    
}

