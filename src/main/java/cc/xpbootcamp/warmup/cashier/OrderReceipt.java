package cc.xpbootcamp.warmup.cashier;

import java.text.SimpleDateFormat;
import java.util.Locale;

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

    public String printReceipt() {
        return getReceiptInfo();
    }

    private String getReceiptInfo() {
        StringBuilder output = new StringBuilder();
        output.append(getReceiptSlogan());
        output.append(getOrderTimeInfo());
        output.append(getLineItemsInfo());
        output.append(getSplitLine());
        output.append(getStateTaxPrintInfo());
        output.append(getDiscountInfo());
        output.append(getTotalAmountPrintInfo());
        return output.toString();
    }

    private String getReceiptSlogan() {
        return "===== 老王超市，值得信赖 ======\n\n";
    }

    private String getOrderTimeInfo() {
        return new SimpleDateFormat("yyyy年M月dd日，E\n\n", Locale.CHINA)
                .format(order.getDate());
    }

    private String getSplitLine() {
        return "-----------------------------------\n";
    }

    private String getLineItemsInfo() {
        StringBuilder result = new StringBuilder();
        String pattern = "%s, %.2f x %d, %.2f\n";
        for (LineItem lineItem : order.getLineItems()) {
            result.append(lineItem.getFormattedLineItemInfo(pattern));
        }
        return result.toString();
    }

    private String getDiscountInfo() {
        return order.isDiscount() ? String.format("折扣: %.2f\n", order.getDiscount()) : "";
    }

    private String getStateTaxPrintInfo() {
        return String.format("税额: %.2f\n", order.getStateTax());
    }

    private String getTotalAmountPrintInfo() {
        return String.format("总价: %.2f\n", order.getTotalAmount());
    }

}
