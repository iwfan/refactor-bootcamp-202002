package cc.xpbootcamp.warmup.cashier;

import java.util.Date;
import java.util.List;

public class Order {
    protected String customerName;
    protected String address;
    protected List<LineItem> lineItemList;
    protected Date date;

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
}
