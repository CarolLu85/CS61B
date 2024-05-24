package deque;

import org.w3c.dom.Node;


public class LinkedListDeque<T> implements Deque<T> {
    private static class Node<T>{
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> sentinelHead;
    private Node<T> sentinelTail;
    private int size;
    public LinkedListDeque(){
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
    public void addFirst(T item){
        Node<T> newNode = new Node<>(item);
        newNode.next = sentinelHead.next;
        newNode.prev = sentinelHead;
        sentinelHead.next.prev = newNode;
        sentinelHead.next= newNode;
        size += 1;
    }

    public void addLast(T item){
        Node<T> newNode = new Node<>(item);
        newNode.prev = sentinelTail.prev;
        newNode.next = sentinelTail;
        sentinelTail.prev.next = newNode;
        sentinelTail.prev = newNode;
        size += 1;
    }

    public void printDeque(){
        if (size == 0){return;}
        for (int i = 0; i < size; i ++){
            System.out.println(this.get(i));
            System.out.println();
        }
    }

    public T removeFirst(){
        if (isEmpty()) {return null;}
        Node<T> first = sentinelHead.next;
        sentinelHead.next = first.next;
        first.next.prev = sentinelHead;
        size -= 1;
        return first.data;
    }

    public T removeLast(){
        if (isEmpty()){return null;}
        Node<T> last = sentinelTail.prev;
        sentinelTail.prev = last.prev;
        last.prev.next = sentinelTail;
        return last.data;
    }

    public T get(int index){
        if (index < 0 || index >= size){return null;}
        Node<T> current = sentinelHead.next;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
    }

//    public Iterator<T> iterator()

//    public boolean equals(Object o)

//    public LinkedListDeque()
    //public T getRecursive(int index)
}
