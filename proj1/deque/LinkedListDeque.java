package deque;

public class LinkedListDeque<T> {
    private T dummy;
    private int size;
    private Node<T> first;
    private Node<T> last;

    public LinkedListDeque(){
        this.size = 0;
        this.first = new Node<>();
        this.last = new Node<>();
        first.next = last;
        last.previous = first;
    }

    public T getRecursive(int index) {
        return dummy;
    }

    public void addFirst(T item){
        Node<T> temp = new Node<>();
        temp.item = item;
        temp.next = first.next;
        temp.next.previous = temp;
        temp.previous = first;
        first.next = temp;
        size +=1;
    }

    public void addLast(T item){
        Node<T> temp = new Node<>();
        temp.item = item;
        temp.previous = last.previous;
        temp.previous.next = temp;
        temp.next = last;
        last.previous = temp;
        size +=1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        System.out.println("deque");
    }
    public T removeFirst(){
        if (isEmpty()) return null;
        Node<T> temp = first.next;
        first.next = temp.next;
        first.next.previous = first;
        size -= 1;
        return temp.item;
    }
    public T removeLast(){
        if (isEmpty()) return null;
        Node<T> temp = last.previous;
        last.previous = temp.previous;
        last.previous.next = last;
        size -= 1;
        return temp.item;
    }

    public T get(int index){
        return dummy;
    }

//    public Iterator<T> iterator(){}
//    public boolean equals(Object o){}

    private class Node<T>{
        public T item;
        public Node next;
        public Node previous;
    }
}
