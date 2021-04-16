package cs145.invoice;

public class InvoiceProcessing {

	public static void main(String[] args) {
		Product toaster = new Product("Toaster Oven", 29.95);
		Product dryer = new Product("Hair dryer", 24.95);
		Product vacuum = new Product("Car vacuum", 19.99);
		Address address = new Address("Sam's Small Appliances", "100 Main Street", "Anytown", "Wisconsin", "54555");
		
		Invoice invoice = new Invoice(address);
		invoice.addCharge(toaster, 3);
		invoice.addCharge(dryer, 1);
		invoice.addCharge(vacuum, 2);
		
		System.out.println(invoice);

	}

}
