package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class MaxArrayDequeTest {
    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        Comparator<String> cmp = new Comparator<>() {

            @Override
            public int compare(String strA, String strB) {
                return strB.compareTo(strA);
            }
        };

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        MaxArrayDeque<String> ad1 = new MaxArrayDeque<String>(cmp);

        assertTrue("A newly initialized LLDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ad1.size());
        assertFalse("ad1 should now contain 1 item", ad1.isEmpty());

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
        Comparator<Integer> cmp = new Comparator<>() {

            @Override
            public int compare(Integer strA, Integer strB) {
                return strB.compareTo(strA);
            }
        };

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<Integer>(cmp);
        // should be empty
        assertTrue("ad1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("ad1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("ad1 should be empty after removal", ad1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        Comparator<Integer> cmp = new Comparator<>() {

            @Override
            public int compare(Integer strA, Integer strB) {
                return strB.compareTo(strA);
            }
        };

        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<>(cmp);
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }


    @Test
    /* check if null is return when removing from an empty MaxArrayDeque. */
    public void emptyNullReturnTest() {
        Comparator<Integer> cmp = new Comparator<>() {

            @Override
            public int compare(Integer strA, Integer strB) {
                return strB.compareTo(strA);
            }
        };
        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<Integer>(cmp);

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, ad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, ad1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        Comparator<Integer> cmp = new Comparator<>() {

            @Override
            public int compare(Integer strA, Integer strB) {
                return strB.compareTo(strA);
            }
        };
        // System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<Integer>(cmp);
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
}
