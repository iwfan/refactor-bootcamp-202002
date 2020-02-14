package cc.xpbootcamp.warmup.fibonacci;

public final class Fibonacci {

    static long byPos(final int position) {
        if (position < 1 || position > 50) {
            throw new IllegalArgumentException("Allow position in 1~50 only!");
        }

        if (position == 1 || position == 2) {
            return 1L;
        } else {
            return byPos(position - 1) + byPos(position - 2);
        }
    }
}
