package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size = 0;
    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    @Override
    public void clear() {
        root = null; //while root is null, all the other nodes have no references to them, and these nodes will become unreachable(garbabge?)
        size = 0;
    }


    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to containsKey() is null");
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {return null;}
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        }else if ( cmp > 0) {
            return get(node.right, key);
        }else {
            return node.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {throw new IllegalArgumentException("first argument to put() is null");}
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value){
            if (node == null) {return new Node(key, value);}
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                 node.left = put(node.left, key, value);
            }else if (cmp > 0) {
                 node.right = put(node.right, key, value);
            }else {
                 node.value = value;
            }
            return node;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        addKeys(root, set);
        return set;
    }

    private void addKeys(Node node, Set<K> set) {
        if (node == null) {
            return;
        }
        set.add(node.key);
        addKeys(node.left, set);
        addKeys(node.right, set);
    }

    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            V toBeRemoved = get(key);
            root = remove(root, key);
            size = size - 1;
            return toBeRemoved;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {return null;}
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            node.left = remove(node.left, key);
        }else if (cmp > 0){
            node.right = remove(node.right, key);
        }else {
            if (node.left == null && node.right != null){
                return node.right;
            }else if (node.right == null && node.left != null){
                return node.left;
            }else if (node.left != null && node.right != null){
                Node minNode = findMin(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
                node.right = remove(node.right, minNode.key); //remove the one used to replace the toBeRemoved node.
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public V remove(K key, V value) {
        if (containsKey(key)){
            V toBeRemoved = get(key);
            if (toBeRemoved.equals(value)){
                root = remove(root, key);
                size = size - 1;
                return toBeRemoved;
            }
        }
        return null;
    }

    public void printInOrder(Node node){
        if (node == null) {return;}
        printInOrder(node.left);
        System.out.println(node.key.toString() + " -> " + node.value.toString());
        printInOrder(node.right);

    }

    @Override
    public Iterator iterator() {
        return keySet().iterator();
    }
}