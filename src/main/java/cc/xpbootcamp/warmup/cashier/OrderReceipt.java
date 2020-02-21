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

    public OrderReceipt(Order order) {
        this.order = order;
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
            result.append(lineItem.getTotalPrice());
            result.append('\n');
        }
        return result.toString();
    }

    private String getStateTaxPrintInfo() {
        return String.format("税额: %f\n", order.getStateTax());
    }

    private String getTotalAmountPrintInfo() {
        return String.format("总价: %f\n", order.getTotalAmount());
    }

    private String getReceiptInfo() {
        StringBuilder output = new StringBuilder();
        output.append(getReceiptSlogan());
        output.append(getOrderTimeInfo());
        output.append(getLineItemsInfo());
        output.append(getSplitLine());
        output.append(getStateTaxPrintInfo());
        if (order.isDiscount()) {
            output.append("折扣: " + order.getDiscount() + "\n");
        }
        output.append(getTotalAmountPrintInfo());
        return output.toString();
    }

    private String getSplitLine() {
        return "-----------------------------------\n";
    }

    private String getOrderTimeInfo() {
        String[] weekNames = {"日", "一", "二", "三", "四", "五", "六"};
        LocalDate localDate = order.getOrderDate();
        int year = localDate.getYear();
        int month = localDate.getMonth().getValue();
        int day = localDate.getDayOfMonth();
        int week = localDate.getDayOfWeek().getValue();
        return String.format("%d年%d月%d日，星期%s\n\n", year, month, day, weekNames[week]);
    }

    public String printReceipt() {
        return getReceiptInfo();
    }

    private String getReceiptTemplate() {
        return "===== 老王超市，值得信赖 ======\n\n";
    }
}
