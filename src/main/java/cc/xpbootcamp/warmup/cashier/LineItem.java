package cc.xpbootcamp.warmup.cashier;

public class LineItem {
	private String description;
	private double price;
	private int quantity;

	public LineItem(String description, double price, int quantity) {
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

    public double getTotalPrice() {
        return getPrice() * getQuantity();
    }

	public String getFormattedLineItemInfo(String formatterPattern) {
		return String.format(formatterPattern, getDescription(), getPrice(), getQuantity(), getTotalPrice());
	}
}
