package cc.xpbootcamp.warmup.cashier;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

class OrderReceiptTest {
    /**
     * 1. testing print slogan and date
     * 2. print line item with name / price / count / amount
     * 3. print tax and total amount
     * 4. print discount on wednesday
     */

    @Test
    void should_print_slogan_and_date_on_order() {
        Order order = new Order("Mr X", "Chicago, 60601", new ArrayList<LineItem>());
        OrderReceipt receipt = new OrderReceipt(order);
        String output = receipt.printReceipt();

        assertThat(output, containsString("===== 老王超市，值得信赖 ======"));

        String regexp = "\\d{4}年\\d{1,2}月\\d{1,2}日，星期.";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(output);

        assertThat(matcher.find(), is(true));
    }

    @Test
    public void shouldPrintLineItemAndSalesTaxInformation() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems));

        String output = receipt.printReceipt();

        assertThat(output, containsString("milk, 10.0 x 2, 20.0\n"));
        assertThat(output, containsString("biscuits, 5.0 x 5, 25.0\n"));
        assertThat(output, containsString("chocolate, 20.0 x 1, 20.0\n"));
        assertThat(output, containsString("税额: 6.5"));
        assertThat(output, containsString("总价: 71.5"));
    }

}
