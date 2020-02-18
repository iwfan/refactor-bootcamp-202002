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
        double amount = getTotalPrice() + getStateTax();
        return isDiscount() ? amount - getDiscount() : amount;
    }

    private double getDiscount() {
        return getTotalPrice() * (1.0 - .98);
    }

    private String getReceiptSlogan() {
        return "===== 老王超市，值得信赖 ======\n\n";
    }

    private String getLineItemsInfo() {
        StringBuilder result = new StringBuilder();
        for (LineItem lineItem : order.getLineItems()) {
            result.append(lineItem.getDescription());
            result.append(", ");
            result.append(lineItem.getPrice());
            result.append(" x ");
            result.append(lineItem.getQuantity());
            result.append(", ");
            result.append(lineItem.totalPrice());
            result.append('\n');
        }
        return result.toString();
    }

    private String getStateTaxPrintInfo() {
        return String.format("税额: %f\n", getStateTax());
    }

    private String getTotalAmountPrintInfo() {
        return String.format("总价: %f\n", getTotalAmount());
    }

    private String getReceiptInfo() {
        StringBuilder output = new StringBuilder();
        output.append(getReceiptSlogan());
        output.append(getOrderTimeInfo());
        output.append(getLineItemsInfo());
        output.append(getSplitLine());
        output.append(getStateTaxPrintInfo());
        if (isDiscount()) {
            output.append("折扣: " + getDiscount() + "\n");
        }
        output.append(getTotalAmountPrintInfo());
        return output.toString();
    }

    private String getSplitLine() {
        return "-----------------------------------\n";
    }

    private String getOrderTimeInfo() {
        String[] weekNames = {"日", "一", "二", "三", "四", "五", "六"};
        LocalDate localDate = getOrderDate();
        int year = localDate.getYear();
        int month = localDate.getMonth().getValue();
        int day = localDate.getDayOfMonth();
        int week = localDate.getDayOfWeek().getValue();
        return String.format("%d年%d月%d日，星期%s\n\n", year, month, day, weekNames[week]);
    }

    private LocalDate getOrderDate() {
        Date date = order.getDate();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private boolean isDiscount() {
        return isWednesday();
    }

    private boolean isWednesday() {
        LocalDate localDate = getOrderDate();
        return localDate.getDayOfWeek().getValue() == 3;
    }

    public String printReceipt() {
        return getReceiptInfo();
    }
}
