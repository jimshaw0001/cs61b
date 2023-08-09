package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> list1 = new AListNoResizing<>();
        BuggyAList<Integer> list2 = new BuggyAList<>();

        list1.addLast(9);
        list2.addLast(9);
        list1.addLast(8);
        list2.addLast(8);
        list1.addLast(7);
        list2.addLast(7);

        assertEquals(list1.removeLast(), list2.removeLast());
        assertEquals(list1.removeLast(), list2.removeLast());
        assertEquals(list1.removeLast(), list2.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> M = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                M.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeM = M.size();
//                System.out.println("sizeL: " + sizeL);
//                System.out.println("sizeM: " + sizeM);
                assertEquals(sizeL, sizeM);
            } else if (operationNumber == 2) {
                // getLast if size > 0
                if (L.size() > 0 || M.size() > 0) {
                    int lastL = L.getLast();
                    int lastM = M.getLast();
//                    System.out.println("getLast() L: " + lastL);
//                    System.out.println("getLast() M: " + lastM);
                    assertEquals(lastL, lastM);
                } else {
//                    System.out.println("getLast(): but size = 0");
                }
            } else if (operationNumber == 3) {
                // removeLast if size > 0
                if (L.size() > 0 || M.size() > 0) {
                    int lastL = L.removeLast();
                    int lastM = M.removeLast();
//                    System.out.println("removeLast() L: " + lastL);
//                    System.out.println("removeLast() M: " + lastM);
                    assertEquals(lastL, lastM);
                } else {
//                    System.out.println("removeLast(): but size = 0");
                }
            }
        }
    }
}
