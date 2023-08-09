package deque;

public class ArrayDeque<T> {
        private T dummy;
        private int start;
        private int end;
        private int size;
        private Object[] arr;

        public ArrayDeque(){
            this.arr = new Object[2000000];
            this.start = 1000000;
            this.end = 1000000;
            this.size = 0;
        }

        public T getRecursive(int index) {
            return dummy;
        }

        public void addFirst(T item){
            start -= 1;
            arr[start] = item;
            size +=1;
        }

        public void addLast(T item){
            arr[end] = item;
            end += 1;
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
            T temp = (T) arr[start];
            arr[start] = null;
            start += 1;
            size -= 1;
            return temp;
        }
        public T removeLast(){
            if (isEmpty()) return null;
            end -= 1;
            T temp = (T) arr[end];
            arr[end] = null;
            size -= 1;
            return temp;
        }

        public T get(int index){
            return (T) arr[start + index];
        }

//    public Iterator<T> iterator(){}
//    public boolean equals(Object o){}

}
