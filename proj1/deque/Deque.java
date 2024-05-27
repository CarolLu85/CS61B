package deque;

// double ended queue or deque
public interface Deque<T> {
    int size();

    void addFirst(T item);

    void addLast(T item);

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);

    default boolean isEmpty() {
        return size() == 0;
    }
}
