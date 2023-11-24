package bstmap;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class BSTMap<K extends Comparable, V extends Comparable> implements Map61B{

    private BSTNode root;
    private int size;

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(Object key) {
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
    public Object get(Object key) {
        // if (key == null) return null;
        BSTNode current = root;
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
    public void put(Object key, Object value) {
        BSTNode<K, V> newNode = new BSTNode<>();
        newNode.k = (K) key;
        newNode.v = (V) value;
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
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    private class BSTNode<K extends Comparable, V extends Comparable> {
        K k;
        V v;
        BSTNode left;
        BSTNode right;
    }

    public static void main(String[] args){
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 10; i++) {
            b.put("hi" + i, 1+i);
        }
        b.printInOrder();
        System.out.println("contains hi5: " + b.containsKey("hi5"));
        System.out.println("hi3: " + b.get("hi3"));
    }
}
