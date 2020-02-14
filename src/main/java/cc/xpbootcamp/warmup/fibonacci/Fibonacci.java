package cc.xpbootcamp.warmup.fibonacci;

public final class Fibonacci {

    static final int MAXIMIZE = 50;

    static long byPos(final int position) {
        if (position < 1 || position > MAXIMIZE) {
            throw new IllegalArgumentException("Allow position in 1~50 only!");
        }

        long[] sequence = new long[MAXIMIZE];
        sequence[0] = 1L;
        sequence[1] = 1L;

        for (int i = 2; i < position; i++) {
            sequence[i] = sequence[i - 1] + sequence[i - 2];
        }

        return sequence[position - 1];
    }
}
