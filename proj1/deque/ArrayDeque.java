package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int head; // it's index of head
    private int tail; // it's index of tail, so type is int

    public ArrayDeque() {
        items = (T[]) new Object[8];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = items[(head + i) % items.length];
        }
        items = newArray;
        head = 0;
        tail = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        head = (head - 1 + items.length) % items.length;
        items[head] = item;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[tail] = item;
        tail = (tail + 1) % items.length;
        size += 1;
    }

    @Override
    public void printDeque() {
        for (T i : this) {
            System.out.println(i);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T firstItem = items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        size -= 1;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        tail = (tail - 1 + items.length) % items.length;
        T lastItem = items[tail];
        items[tail] = null;
        size -= 1;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return lastItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(head + index) % items.length];
    }

    public T peekFirst() {
        if (size == 0) {
            return null;
        }
        return items[head];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int n; // starting from n

        private ArrayDequeIterator() {
            n = 0;
        }

        public boolean hasNext() {
            return n < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = get(n);
            n += 1;
            return item;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) { // objects type check
            return false;
        }
        Deque<T> newO = (Deque<T>) o;
        if (newO.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!newO.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }

    private void printArray() {
        for (T item : items) {
            System.out.print(item + " ");
        }
    }
}
