package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {

    private static class IntComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            if (a == null || b == null || a < b) return -1;
            if (a > b) return 1;
            return 0;
        }
    }

    @Test
    public void maxTest() {
        IntComparator intComparator = new IntComparator();
        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<>(intComparator);
        mad1.addLast(1);
        mad1.addLast(2);
        mad1.addLast(-1);
        assertEquals(Integer.valueOf(2), mad1.max());
    }
}
