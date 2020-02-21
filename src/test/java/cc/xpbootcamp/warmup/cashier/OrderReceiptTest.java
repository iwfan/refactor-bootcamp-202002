package cc.xpbootcamp.warmup.cashier;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

class OrderReceiptTest {

    @Test
    void should_print_slogan_and_date_on_order() {
        Order order = new Order("Mr X", "Chicago, 60601", new ArrayList<LineItem>(), new Date());
        OrderReceipt receipt = new OrderReceipt(order);
        String output = receipt.printReceipt();

        assertThat(output, containsString("===== 老王超市，值得信赖 ======"));

        String regexp = "\\d{4}年\\d{1,2}月\\d{1,2}日，星期.";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(output);
        System.out.println(output);
        assertThat(matcher.find(), is(true));
    }

    @Test
    public void should_print_line_item_and_sales_tax_information() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
        }};

        OrderReceipt receipt = new OrderReceipt(new Order(lineItems));

        String output = receipt.printReceipt();

        assertThat(output, containsString("milk, 10.00 x 2, 20.00\n"));
        assertThat(output, containsString("biscuits, 5.00 x 5, 25.00\n"));
        assertThat(output, containsString("chocolate, 20.00 x 1, 20.00\n"));
        assertThat(output, containsString("税额: 6.5"));
        assertThat(output, containsString("总价: 71.5"));
    }

    @Test
    public void should_print_discount_information_on_wednesday() throws ParseException {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.00, 2));
        }};

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date NOW = sdf.parse("2020-02-19 00:00:00");
        OrderReceipt receipt = new OrderReceipt(new Order(lineItems, NOW));

        String output = receipt.printReceipt();

        assertThat(output, containsString("milk, 10.00 x 2, 20.00\n"));
        assertThat(output, containsString("折扣: 0.4"));
    }

}
