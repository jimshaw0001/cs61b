package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private static final int START_CAPACITY = 8;
    private int start;
    private int end;
    private int size;
    private int capacity;
    private Object[] arr;

    public ArrayDeque() {
        this.arr = new Object[START_CAPACITY];
        this.start = START_CAPACITY / 3; // first item
        this.end = START_CAPACITY / 3; // next item
        this.size = 0;
        this.capacity = START_CAPACITY;
    }

    private void resize() {
        capacity = Math.max(size() * 3, START_CAPACITY);
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
    public void addFirst(T item) {
        if (start < 1) {
            resize();
        }
        start -= 1;
        arr[start] = item;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (end == arr.length) {
            resize();
        }
        arr[end] = item;
        end += 1;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        System.out.println("deque");
    }

    @Override
    public T removeFirst() {
        if (size() < capacity / 4) {
            resize();
        }
        if (isEmpty()) {
            return null;
        }
        T temp = (T) arr[start];
        arr[start] = null;
        start += 1;
        size -= 1;
        return temp;
    }

    @Override
    public T removeLast() {
        if (size() < capacity / 4) {
            resize();
        }
        if (isEmpty()) {
            return null;
        }
        end -= 1;
        T temp = (T) arr[end];
        arr[end] = null;
        size -= 1;
        return temp;
    }

    @Override
    public T get(int index) {
        return (T) arr[start + index];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> obj = (Deque<T>) o;
        if (size() != obj.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(obj.get(i))) {
                return false;
            }
        }
        return true;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        ArrayDequeIterator() {
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
