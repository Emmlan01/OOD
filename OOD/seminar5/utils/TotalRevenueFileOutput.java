package seminar5.utils;
import java.io.IOException;

/**
 * Abstract class that implements the Template pattern for observing total revenue.
 */
public abstract class TotalRevenueFileOutput implements TotalRevenueObserver {
    private double total;

    /**
     * It calculates and shows the updated total revenue and is called when a new sale has been completed.
     *  @param totalRevenue The amount of the latest sale to add to the total revenue.
     */
  @Override
  public void calculateTotalRevenue(double totalRevenue){
    addTotalRevenue(totalRevenue);  
    showTotalRevenue();
  }
  
/**
 * Adds the sale amount to the accumulated total revenue.
 * @param totalRevenue The revenue from the most recent sale.
 */
  private void addTotalRevenue(double totalRevenue){
    total += totalRevenue;
  }
/**
 * Calls the abstract method to display the total revenue and handle exception.
 */
  public void showTotalRevenue(){
    try{
        doShowTotalRevenue(total);
    } catch (Exception e){
        handleErrors(e);
    }
  }
  
  /**
   * Shows the total revenue.
   * @param total The current total to display.
   * @throws IOException If it fails.
   */
    protected abstract void doShowTotalRevenue(double total) throws IOException; 

    /**
     * Handle error.
     * @param e The exception to handle.
     */
    protected abstract void handleErrors(Exception e);
}