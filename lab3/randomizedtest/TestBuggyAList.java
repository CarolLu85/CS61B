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
        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();

        for(int i = 3; i <= 6; i ++){
            a.addLast(i);
            b.addLast(i);
        }

        for(int j = 0; j <= 2; j++){
            assertEquals(a.removeLast(), b.removeLast());
        }
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> R = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                R.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeR = R.size();
                System.out.println("sizeL: " + sizeL + "sizeR: " + sizeR);
                assertEquals(sizeL, sizeR);
            } else if (operationNumber == 2 && L.size() > 0 && R.size() > 0) {
                int lastL = L.getLast();
                int lastR = R.getLast();
                assertEquals(lastL, lastR);
            } else if (operationNumber == 3 && L.size() > 0&& R.size() > 0) {
                int removeL = L.removeLast();
                int removeR = R.removeLast();
                assertEquals(removeL, removeR);

            }
        }
    }
}
