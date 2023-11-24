package bstmap;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V>{

    private BSTNode root;
    private int size;

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        BSTNode current = root;
        while (current != null) {
            if (current.k.compareTo(key) == 0) {
                return true;
            } else if (current.k.compareTo(key) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        // if (key == null) return null;
        BSTNode<K, V> current = root;
        while (current != null) {
            if (current.k.compareTo(key) == 0) {
                return current.v;
            } else if (current.k.compareTo(key) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        BSTNode<K, V> newNode = new BSTNode<>();
        newNode.k = key;
        newNode.v = value;
        size++;
        if (root == null) {
            root = newNode;
            return;
        }
        BSTNode current = root;
        while (current != null) {
            if (current.k.compareTo(key) > 0) {
                if (current.left == null) {
                    current.left = newNode;
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    return;
                }
                current = current.right;
            }
        }
    }

    public void printInOrder() {
        printNodeTree(root);
    }
    private void printNodeTree(BSTNode<K, V> node) {
        if (node == null) {
            System.out.print("null ");
        } else {
            printNodeTree(node.left);
            System.out.print(node.k + ":" + node.v + " ");
            printNodeTree(node.right);
        }
        System.out.println();
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private class BSTNode<K extends Comparable<K>, V> {
        K k;
        V v;
        BSTNode left;
        BSTNode right;
    }
}
