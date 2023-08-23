package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private final Node<T> first;
    private final Node<T> last;

    public LinkedListDeque() {
        this.size = 0;
        this.first = new Node<>();
        this.last = new Node<>();
        first.next = last;
        last.previous = first;
    }

    @Override
    public void addFirst(T item) {
        Node<T> temp = new Node<>();
        temp.item = item;
        temp.next = first.next;
        temp.next.previous = temp;
        temp.previous = first;
        first.next = temp;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node<T> temp = new Node<>();
        temp.item = item;
        temp.previous = last.previous;
        temp.previous.next = temp;
        temp.next = last;
        last.previous = temp;
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
        if (isEmpty()) {
            return null;
        }
        Node<T> temp = first.next;
        first.next = temp.next;
        first.next.previous = first;
        size -= 1;
        return temp.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> temp = last.previous;
        last.previous = temp.previous;
        last.previous.next = last;
        size -= 1;
        return temp.item;
    }

    @Override
    public T get(int index) {
        Node<T> temp = first.next;
        while (index > 0) {
            temp = temp.next;
            index -= 1;
        }
        return temp.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, first.next);
    }

    private T getRecursiveHelper(int index, Node<T> node) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
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

    private static class Node<S> {
        S item;
        Node<S> next;
        Node<S> previous;
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int index;

        LinkedListDequeIterator() {
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
