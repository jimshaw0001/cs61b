package gh2;

import deque.ArrayDeque;
import deque.Deque;

public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        this.buffer = new ArrayDeque<>();
        for (int i = 0; i < Math.round(SR / frequency); i++) {
            buffer.addLast(Double.valueOf(0.0));
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int tmpSize = buffer.size();
        for (int i = 0; i < tmpSize; i++) {
            buffer.removeLast();
        }
        for (int i = 0; i < tmpSize; i++) {
            buffer.addLast(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        Double front = buffer.get(0);
        buffer.removeFirst();
        Double newDouble = DECAY * (front + buffer.get(0)) / 2.0;
        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
