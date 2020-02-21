package cc.xpbootcamp.warmup.cashier;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Order {
    String customerName;
    String address;
    List<LineItem> lineItemList;
    Date date;

    private static final double TAX_RATE = .10;
    private static final double DISCOUNT_RATE = .02;

    public Order(List<LineItem> lineItemList) {
        this(null, null, lineItemList);
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

    public LocalDate getOrderDate() {
        Date date = getDate();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public boolean isDiscount() {
        return isWednesday();
    }

    public boolean isWednesday() {
        LocalDate localDate = getOrderDate();
        return localDate.getDayOfWeek().getValue() == 3;
    }
}
