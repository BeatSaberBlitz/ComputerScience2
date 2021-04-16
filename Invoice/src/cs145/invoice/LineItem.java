/**
 * 
 */
package cs145.invoice;

/**
 *@author Tiernan Meyer
 *@version 03/04/20
 */
public class LineItem {
	private int quantity;
	private Product product;
	
	/**
	 * Constructor for the LineItem
	 * @param product the product that was purchased
	 * @param qty the amount of the product purchased
	 */
	public LineItem(Product product, int qty) {
		this.product = product;
		this.quantity = qty;
	}
	
	/**
	 * Determines the total price of the product multiplied by the quantity ordered
	 * @return the total price of the products
	 */
	public double getTotalPrice() {
		double total = product.getPrice() * quantity;
		return total;
	}
	
	/**
	 * Converts the LineItem into a string to be outputted
	 */
	public String toString() {
		return product.getDescription() + "\t$" + product.getPrice() + "\t" + quantity + "\t\t$" + getTotalPrice() + "\n";
	}
}
