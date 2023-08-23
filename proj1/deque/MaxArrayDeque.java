package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        T tmpMax = get(0);
        for (int i = 1; i < size(); i++) {
            if (c.compare(get(i), tmpMax) > 0) {
                tmpMax = get(i);
            }
        }
        return tmpMax;
    }
}
