package cc.xpbootcamp.warmup.fibonacci;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FibonacciTest {
    /**
     * 斐波那契数列： 1， 1， 2， 3， 5， 8 ... 12586269025L(50)
     *  Todo:
     *      + test position 1
     *      + test position 2
     *      + test position 3
     *      + test position 25
     *      + test position 50
     *      + test position < 1, > 50
     */

    @Test
    void should_return_1_when_calculate_given_position_is_1() {
        Assertions.assertEquals(1, Fibonacci.byPos(1));
    }

    @Test
    void should_return_1_when_calculate_given_position_is_2() {
        Assertions.assertEquals(1, Fibonacci.byPos(2));
    }

    @Test
    void should_return_2_when_calculate_given_position_is_3() {
        Assertions.assertEquals(2, Fibonacci.byPos(3));
    }

    @Test
    void should_return_75025_when_calculate_given_position_is_25() {
        Assertions.assertEquals(75025, Fibonacci.byPos(25));
    }

    @Test
    void should_return_12586269025_when_calculate_given_position_is_50() {
        Assertions.assertEquals(12586269025L, Fibonacci.byPos(50));
    }

    @Test
    void should_throw_error_when_calculate_given_position_is_0() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Fibonacci.byPos(0);
        });
    }

    @Test
    void should_throw_error_when_calculate_given_position_is_51() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Fibonacci.byPos(51);
        });
    }

}
