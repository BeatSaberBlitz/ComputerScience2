/**
 * 
 */
package cs145.invoice;

/**
 * Creates a product that has a description and price
 * @author MarefkeTyler
 * @version 2020.03.04
 */
public class Product {
	private String description;
	private double price;
	
	
	/**
	 * Creates a product with the parameters listed
	 * @param description the description of the product
	 * @param price the price of the product
	 */
	public Product(String description, double price) {
		super();
		this.description = description;
		this.price = price;
	}


	/**
	 * Returns the description of the product
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * Returns the price of the product
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
}
