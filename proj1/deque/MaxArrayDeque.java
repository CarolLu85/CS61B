package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comp = c;
    }

    /* returns the maximum element in the deque as governed by the previously given Comparator. */
    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        T maxElement = this.get(0);
        for (T element : this) {
            if (comp.compare(element, maxElement) > 0) {
                maxElement = element;
            }
        }
        return maxElement;
    }

    /* returns the maximum element in the deque as governed by the parameter Comparator c. */
    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T maxElement = this.get(0);
        for (T element : this) {
            if (c.compare(element, maxElement) > 0) {
                maxElement = element;
            }
        }
        return maxElement;
    }

    public static void main(String[] args) {
        // Comparator<Integer> defaultComparator = new Comparator<Integer>() {
        //     @Override
        //     public int compare(Integer a, Integer b) {
        //         return a.compareTo(b);
        //     }
        // };

        // Comparator<Integer> defaultComparator = (a, b) -> a.compareTo(b);

        Comparator<Integer> defaultComparator = Integer::compareTo;
        MaxArrayDeque<Integer> maxDeque = new MaxArrayDeque<>(defaultComparator);

        maxDeque.addFirst(1);
        maxDeque.addFirst(2);
        maxDeque.addFirst(3);

        System.out.println("the max is: " + maxDeque.max());

        Comparator<String> stringComparator = String::compareTo;
        MaxArrayDeque<String> maxStringDeque = new MaxArrayDeque<>(stringComparator);

        maxStringDeque.addLast("apple");
        maxStringDeque.addLast("banana");
        maxStringDeque.addLast("cherry");

        System.out.println("Max string (natural order): " + maxStringDeque.max(stringComparator));

        // self-defined comparator
        Comparator<String> lengthComparator = (a, b) -> Integer.compare(a.length(), b.length());
        System.out.println("Max string (by length): " + maxStringDeque.max(lengthComparator));
    }
}
