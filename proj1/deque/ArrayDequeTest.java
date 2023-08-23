package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {

        @Test
        /** Adds a few things to the list, checking isEmpty() and size() are correct,
         * finally printing the results.
         *
         * && is the "and" operation. */
        public void addIsEmptySizeTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

            ArrayDeque<String> ad1 = new ArrayDeque<String>();

            assertTrue("A newly initialized LLDeque should be empty", ad1.isEmpty());
            ad1.addFirst("front");

            // The && operator is the same as "and" in Python.
            // It's a binary operator that returns true if both arguments true, and false otherwise.
            assertEquals(1, ad1.size());
            assertFalse("lld1 should now contain 1 item", ad1.isEmpty());

            ad1.addLast("middle");
            assertEquals(2, ad1.size());

            ad1.addLast("back");
            assertEquals(3, ad1.size());

            System.out.println("Printing out deque: ");
            ad1.printDeque();
        }

        @Test
        /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
        public void addRemoveTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

            ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
            // should be empty
            assertTrue("lld1 should be empty upon initialization", ad1.isEmpty());

            ad1.addFirst(10);
            // should not be empty
            assertFalse("lld1 should contain 1 item", ad1.isEmpty());

            ad1.removeFirst();
            // should be empty
            assertTrue("lld1 should be empty after removal", ad1.isEmpty());

        }

        @Test
        /* Tests removing from an empty deque */
        public void removeEmptyTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

            ArrayDeque<Integer> lld1 = new ArrayDeque<>();
            lld1.addFirst(3);

            lld1.removeLast();
            lld1.removeFirst();
            lld1.removeLast();
            lld1.removeFirst();

            int size = lld1.size();
            String errorMsg = "  Bad size returned when removing from empty deque.\n";
            errorMsg += "  student size() returned " + size + "\n";
            errorMsg += "  actual size() returned 0\n";

            assertEquals(errorMsg, 0, size);

        }

        @Test
        /* Check if you can create ArrayDeque with different parameterized types*/
        public void multipleParamTest() {

            ArrayDeque<String>  ad1 = new ArrayDeque<String>();
            ArrayDeque<Double>  ad2 = new ArrayDeque<Double>();
            ArrayDeque<Boolean> ad3 = new ArrayDeque<Boolean>();

            ad1.addFirst("string");
            ad2.addFirst(3.14159);
            ad3.addFirst(true);

            String s = ad1.removeFirst();
            double d = ad2.removeFirst();
            boolean b = ad3.removeFirst();

        }

        @Test
        /* check if null is return when removing from an empty ArrayDeque. */
        public void emptyNullReturnTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

            ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

            boolean passed1 = false;
            boolean passed2 = false;
            assertEquals("Should return null when removeFirst is called on an empty Deque,", null, ad1.removeFirst());
            assertEquals("Should return null when removeLast is called on an empty Deque,", null, ad1.removeLast());

        }

        @Test
        /* Add large number of elements to deque; check if order is correct. */
        public void bigLLDequeTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

            ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
            for (int i = 0; i < 1000000; i++) {
                ad1.addLast(i);
            }

            for (double i = 0; i < 500000; i++) {
                assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
            }

            for (double i = 999999; i > 500000; i--) {
                assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
            }

        }


    @Test
    public void iterableTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addFirst(3);
        Iterator<Integer> itr = ad1.iterator();
        if (itr.hasNext()) assertEquals(itr.next(), Integer.valueOf(3));
        if (itr.hasNext()) assertEquals(itr.next(), Integer.valueOf(1));
        if (itr.hasNext()) assertEquals(itr.next(), Integer.valueOf(2));
        assertEquals(itr.hasNext(), false);

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            ad2.addLast(i);
        }
        int j = 0;
        for (Integer i : ad2) {
            assertEquals(i, Integer.valueOf(j));
            j++;
        }
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addFirst(3);
        ArrayDeque<Integer> ad2 = null;
        ArrayDeque<Integer> ad3 = ad1;
        ArrayDeque<Integer> ad4 = new ArrayDeque<>();
        ad4.addLast(1);
        ad4.addLast(2);
        ArrayDeque<Integer> ad5 = new ArrayDeque<>();
        ad5.addLast(1);
        ad5.addLast(2);
        ad5.addFirst(3);
        ArrayDeque<Integer> ad6 = new ArrayDeque<>();
        ad6.addLast(1);
        ad6.addLast(2);
        ad6.addFirst(4);
        assertEquals(false, ad1.equals(ad2));
        assertEquals(true, ad1.equals(ad3));
        assertEquals(false, ad1.equals(ad4));
        assertEquals(true, ad1.equals(ad5));
        assertEquals(false, ad1.equals(ad6));
    }

    @Test
    public void testRandom() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(0);
        ad1.removeLast();
        ad1.isEmpty();
        ad1.addFirst(3);
        ad1.removeLast();
        ad1.addFirst(5);
    }
}
