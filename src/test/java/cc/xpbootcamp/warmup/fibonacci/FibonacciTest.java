package cc.xpbootcamp.warmup.fibonacci;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FibonacciTest {
    /**
     * 斐波那契数列： 1， 1， 2， 3， 5， 8 ... 12586269025L(50)
     *  Todo:
     *      - test position 1
     *      - test position 2
     *      - test position 3
     *      - test position 25
     *      - test position 50
     *      - test position < 1, > 50
     */

    @Test
    void should_return_1_when_calculate_given_position_is_1() {
        Assertions.assertEquals(1l, Fibonacci.byPos(1));
    }

}