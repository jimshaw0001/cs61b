package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int initialSize;
    private int currentSize;
    private int numItems;
    private double maxLoad;

    /** Constructors */
    public MyHashMap() {
        this(10);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 10.0);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        initialSize = initialSize;
        maxLoad = maxLoad;
        buckets = createTable(initialSize);
        currentSize = initialSize;
        numItems = 0;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<Node>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        buckets = createTable(initialSize);
        currentSize = initialSize;
        numItems = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (currentSize < 1) return false;
        Collection<Node> bucket = buckets[Math.floorMod(key.hashCode(), currentSize)];
        for (Node node : bucket) {
            if (node.key.equals(key)) return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (currentSize < 1) return null;
        Collection<Node> bucket = buckets[Math.floorMod(key.hashCode(), currentSize)];
        for (Node node : bucket) {
            if (node.key.equals(key)) return node.value;
        }
        return null;
    }

    @Override
    public int size() {
        return numItems;
    }

    @Override
    public void put(K key, V value) {
        putHelper(key, value);
        if (numItems / currentSize > maxLoad) increaseSize();
    }

    private void putHelper(K key, V value) {
        Collection<Node> bucket = buckets[Math.floorMod(key.hashCode(), currentSize)];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        bucket.add(createNode(key, value));
        numItems++;
    }

    private void increaseSize() {
        int oldSize = currentSize;
        Collection<Node>[] oldBuckets = buckets;
        currentSize *= 2;
        buckets = createTable(currentSize);
        for (int i = 0; i < oldSize; i++) {
            for (Node node : oldBuckets[i]) {
                buckets[Math.floorMod(node.key.hashCode(), currentSize)].add(node);
            }
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> hs = new HashSet<>();
        for (int i = 0; i < currentSize; i++) {
            for (Node node : buckets[i]) {
                hs.add(node.key);
            }
        }
        return hs;
    }

    @Override
    public V remove(K key) {
        Collection<Node> bucket = buckets[Math.floorMod(key.hashCode(), currentSize)];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                V val = node.value;
                bucket.remove(node);
                return val;
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

}
