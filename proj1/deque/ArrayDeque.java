package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private final static int START_CAPACITY = 8;
    private int start;
    private int end;
    private int size;
    private Object[] arr;

    public ArrayDeque(){
        this.arr = new Object[START_CAPACITY];
        this.start = START_CAPACITY / 3; // first item
        this.end = START_CAPACITY / 3; // next item
        this.size = 0;
    }

    private void resize(int capacity) {
        Object[] newArr = new Object[capacity];
        int newStart = capacity / 3;
        int newEnd = newStart + size();
        for (int i = 0; i < size(); i++) {
            newArr[newStart + i] = arr[start + i];
        }
        start = newStart;
        end = newEnd;
        arr = newArr;
    }

    @Override
    public void addFirst(T item){
        if (start == 0) resize(size() * 3);
        start -= 1;
        arr[start] = item;
        size +=1;
    }

    @Override
    public void addLast(T item){
        if (end == arr.length) resize(size() * 3);
        arr[end] = item;
        end += 1;
        size +=1;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        System.out.println("deque");
    }

    @Override
    public T removeFirst(){
        if (size() < arr.length / 6) resize(size() * 3);
        if (isEmpty()) return null;
        T temp = (T) arr[start];
        arr[start] = null;
        start += 1;
        size -= 1;
        return temp;
    }

    @Override
    public T removeLast(){
        if (size() < arr.length / 6) resize(size() * 3);
        if (isEmpty()) return null;
        end -= 1;
        T temp = (T) arr[end];
        arr[end] = null;
        size -= 1;
        return temp;
    }

    @Override
    public T get(int index){
            return (T) arr[start + index];
        }

    public T getRecursive(int index) {
        return (T) arr[start + index];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o){
        if (o == null) return false;
        if (this == o) return true;
        ArrayDeque<T> obj = (ArrayDeque<T>) o;
        if (size() != obj.size()) return false;
        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(obj.get(i))) return false;
        }
        return true;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        public ArrayDequeIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T tmp = get(index);
            index += 1;
            return tmp;
        }
    }
}
