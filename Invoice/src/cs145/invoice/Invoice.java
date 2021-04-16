/**
 * 
 */
package cs145.invoice;

import java.util.ArrayList;

/**
 * Creates a Invoice for the customer based on the items they want
 * 
 * @author MarefkeTyler
 * @version 2020.03.04
 */
public class Invoice {
	private Address billingAddress;
	private ArrayList<LineItem> items;

	/**
	 * @param billingAddress
	 */
	public Invoice(Address billingAddress) {
		this.billingAddress = billingAddress;
		items = new ArrayList<LineItem>();
	}

	/**
	 * Adds a LineItem to the ArrayList items
	 * 
	 * @param product  the product for LineItem
	 * @param quantity the quantity of the product
	 */
	public void addCharge(Product product, int quantity) {
		LineItem myItem = new LineItem(product, quantity);
		items.add(myItem);
	}

	/**
	 * Returns the total amount due for all LineItems
	 * 
	 * @return totalPrice the total cost due for all LineItems
	 */
	public double getTotalDue() {
		double totalPrice = 0;
		for (int index = 0; index < items.size(); index++) {
			totalPrice += items.get(index).getTotalPrice();
		}
		return totalPrice;
	}

	/**
	 * Returns a String form of invoice for the customer
	 * 
	 * @return the invoice for the customer
	 */
	@Override
	public String toString() {
		String myInvoice = "Invoice\n\n" + billingAddress + "\n\nDescription\tPrice\tQuantity\tTotalPrice\n"
				+ "------------------------------------------------------\n";
		for (int index = 0; index < items.size(); index++) {
			myInvoice += items.get(index);
		}
		myInvoice += "Total Price:\t\t\t\t$" + getTotalDue() + "\n\nThank You for shopping at Wal-Mart!\n";
		return myInvoice;
	}

}
