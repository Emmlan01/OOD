package seminar4.view;

import seminar4.utils.TotalRevenueObserver;

public class TotalRevenueView implements TotalRevenueObserver {
	private double totalRevenue = 0;
	
	/**
	 * Prints the total revenue to the terminal.
	 * @param revenue the new running total from the ended sale.
	 */
	@Override
	public void printTotalRevenue(double revenue) {
		totalRevenue += revenue;
		System.out.println("********** Total Revenue: " + totalRevenue + "SEK *********** \n");
	} 
}