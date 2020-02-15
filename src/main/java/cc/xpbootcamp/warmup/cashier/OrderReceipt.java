package cc.xpbootcamp.warmup.cashier;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {
    private Order order;
    private final double TAX_RATE = .10;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    private double getTotalPrice() {
        return order.getLineItems()
                .stream()
                .map(item -> item.totalPrice())
                .reduce(.0, Double::sum);
    }

    private double getStateTax() {
        return getTotalPrice() * TAX_RATE;
    }

    private double getTotalAmount() {
        return getTotalPrice() + getStateTax();
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        printReceiptHeaders(output);
        printCustomerInfo(output);
        printLineItemsInfo(output);
        printStateTax(output, getStateTax());
        printTotalPrice(output, getTotalAmount());
        return output.toString();
    }

    private void printLineItemsInfo(StringBuilder output) {
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalPrice());
            output.append('\n');
        }
    }

    private void printTotalPrice(StringBuilder output, double total) {
        // print total amount
        output.append("Total Amount").append('\t').append(total);
    }

    private void printStateTax(StringBuilder output, double totalSalesTax) {
        // prints the state tax
        output.append("Sales Tax").append('\t').append(totalSalesTax);
    }

    private void printCustomerInfo(StringBuilder output) {
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
    }

    private void printReceiptHeaders(StringBuilder output) {
        output.append("======Printing Orders======\n");
    }
}
