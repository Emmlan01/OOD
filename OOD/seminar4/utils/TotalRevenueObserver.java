package seminar4.utils;

/**
 * A listener interface for receiving notifications about total revenue. 
 * 
 */
public interface TotalRevenueObserver {
    /**
     * Prints the total revenue
     * @param totalRevenue The total revenue
     */
    void printTotalRevenue(double totalRevenue);
}