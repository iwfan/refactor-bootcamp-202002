package cc.xpbootcamp.warmup.cashier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Order {
    String customerName;
    String address;
    List<LineItem> lineItemList;
    Date date;

    private static final double TAX_RATE = .10;
    private static final double DISCOUNT_RATE = .02;
    private static final String DAY_NUMBER_OF_WEEK = "u";
    private static final String WEDNESDAY = "3";

    public Order(List<LineItem> lineItemList) {
        this(null, null, lineItemList);
    }

    public Order(List<LineItem> lineItemList, Date date) {
        this(null, null, lineItemList, date);
    }

    public Order(String customerName, String address, List<LineItem> lineItemList) {
        this(customerName, address, lineItemList, new Date());
    }

    public Order(String customerName, String address, List<LineItem> lineItemList, Date date) {
        this.customerName = customerName;
        this.address = address;
        this.lineItemList = lineItemList;
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return address;
    }

    public List<LineItem> getLineItems() {
        return lineItemList;
    }

    public Date getDate() {
        return date;
    }

    public double getTotalPrice() {
        return getLineItems()
                .stream()
                .map(LineItem::getTotalPrice)
                .reduce(.0, Double::sum);
    }

    public double getStateTax() {
        return getTotalPrice() * TAX_RATE;
    }

    public double getTotalAmount() {
        double amount = getTotalPrice() + getStateTax();
        return isDiscount() ? amount - getDiscount() : amount;
    }

    public double getDiscount() {
        return getTotalPrice() * DISCOUNT_RATE;
    }

    public boolean isDiscount() {
        return isWednesday();
    }

    public boolean isWednesday() {
        return new SimpleDateFormat(DAY_NUMBER_OF_WEEK).format(getDate()).equals(WEDNESDAY);
    }
}
