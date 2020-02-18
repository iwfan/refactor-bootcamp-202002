package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
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
                .map(LineItem::totalPrice)
                .reduce(.0, Double::sum);
    }

    private double getStateTax() {
        return getTotalPrice() * TAX_RATE;
    }

    private double getTotalAmount() {
        return getTotalPrice() + getStateTax();
    }

    private String getReceiptSlogan() {
        return "===== 老王超市，值得信赖 ======\n\n";
    }

    private String getCustomerInfo() {
        return order.getCustomerName() + order.getCustomerAddress();
    }

    private String getLineItemsInfo() {
        StringBuilder result = new StringBuilder();
        for (LineItem lineItem : order.getLineItems()) {
            result.append(lineItem.getDescription());
            result.append('\t');
            result.append(lineItem.getPrice());
            result.append('\t');
            result.append(lineItem.getQuantity());
            result.append('\t');
            result.append(lineItem.totalPrice());
            result.append('\n');
        }
        return result.toString();
    }

    private String getStateTaxPrintInfo() {
        return String.format("Sales Tax\t%f", getStateTax());
    }

    private String getTotalAmountPrintInfo() {
        return String.format("Total Amount\t%f", getTotalAmount());
    }

    private String getReceiptInfo() {
        StringBuilder output = new StringBuilder();
        output.append(getReceiptSlogan());
        output.append(getOrderTimeInfo());
        output.append(getCustomerInfo());
        output.append(getLineItemsInfo());
        output.append(getStateTaxPrintInfo());
        output.append(getTotalAmountPrintInfo());
        return output.toString();
    }

    private String getOrderTimeInfo() {
        String[] weekNames = {"日", "一", "二", "三", "四", "五", "六"};
        Date date = order.getTime();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonth().getValue();
        int day = localDate.getDayOfMonth();
        int week = localDate.getDayOfWeek().getValue();
        return String.format("%d年%d月%d日，星期%s\n\n", year, month, day, weekNames[week]);
    }

    public String printReceipt() {
        return getReceiptInfo();
    }
}
