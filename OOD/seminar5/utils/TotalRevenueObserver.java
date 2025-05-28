package seminar5.utils;


/**
 * A listener interface for receiving notifications about total revenue. 
 * 
 */
public interface TotalRevenueObserver {
    /**
     * Calculate the total revenue
     * @param totalRevenue The total revenue
     */
    void calculateTotalRevenue(double totalRevenue);
}