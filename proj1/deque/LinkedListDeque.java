package deque;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> sentinelHead;
    private Node<T> sentinelTail;
    private int size;

    public LinkedListDeque() {
        sentinelHead = new Node<>(null);
        sentinelTail = new Node<>(null);
        sentinelHead.next = sentinelTail;
        sentinelTail.prev = sentinelHead;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = sentinelHead.next;
        newNode.prev = sentinelHead;
        sentinelHead.next.prev = newNode;
        sentinelHead.next = newNode;
        size += 1;
    }

    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.prev = sentinelTail.prev;
        newNode.next = sentinelTail;
        sentinelTail.prev.next = newNode;
        sentinelTail.prev = newNode;
        size += 1;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(this.get(i));
            System.out.println();
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> first = sentinelHead.next;
        Node<T> second = first.next;

        sentinelHead.next = second;
        if (second != null) {
            second.prev = sentinelHead;
        }

        size--;
        return first.data;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> last = sentinelTail.prev;
        sentinelTail.prev = last.prev;
        last.prev.next = sentinelTail;
        return last.data;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> current = sentinelHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public T getRecursive(int index) {
        Node<T> found;
        if (index < 0 || index >= size) {
            return null;
        }
        return recursiveHelper(sentinelHead.next, index);
    }

    private T recursiveHelper(Node<T> node, int index){
        if (index == 0){
            return node.data;
        }
        return recursiveHelper(node.next, index - 1);
    }

    public Iterator<T> iterator() { //return an iterator object.
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int n; //starting from n

        private LinkedListDequeIterator() {
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

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {//objects type check>
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
}