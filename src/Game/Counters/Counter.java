package Game.Counters;

/**
 * Counter holds count member and methods increase and decrease, updating count.
 * @author Itamar Berger 322554403
 * @version 1.0
 * @since 2023-04-24

 */
public class Counter {
    private int count;

    /**
     * A constructor creating a counter with a given number.
     * @param number (int).
     */
    public Counter(int number) {
        this.count = number;
    }

    /**
     * Adds number to current count.
     * @param number (int) added number.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtracts number from current count.
     * @param number (int) subtracted number.
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * Get current count of Counter.
     * @return count (int).
     */
    public int getValue() {
        return this.count;
    }

    /**
     * Updates current count.
     * @param number (int).
     */
    public void setValue(int number) {
        this.count = number;
    }
}

