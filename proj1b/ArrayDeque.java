public class ArrayDeque<T> implements Deque<T> {

    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private final int initSize = 4;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[initSize];
        int mid = items.length / 2;
        nextFirst = mid - 1;
        nextLast = mid;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextFirst] = item;
        nextFirst = ((nextFirst - 1) + items.length) % items.length; // this step is very important!
        size += 1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        //System.arraycopy(items, 0, a, 0, size);
        int startPos = a.length/2 - size/2; // this marks the beginning of the first element in the new array! (half pos in the original item size)
        int oldIndex = nextFirst + 1;
        int newIndex = startPos;
        int count = 0;

        while (count < size) {
            a[newIndex] = items[oldIndex % items.length];
            newIndex += 1;
            oldIndex += 1;
            count += 1;
        }
        items = a;
        nextFirst = startPos - 1;
        nextLast = newIndex;
    }

    private void resizeDown() {
        resize(Math.max(size * 2, initSize));
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        int first = (nextFirst + 1) % items.length;
        int last = ((nextLast - 1) + items.length) % items.length;
        if (first < last) {
            for (int i = first; i <= last; i += 1) {
                System.out.print(items[i]);
            }
        }
        else {
            int i = first;
            while (i < items.length) {
                System.out.print(items[i]);
                i += 1;
            }
            i = 0;
            while (i <= last) {
                System.out.print(items[i]);
                i += 1;
            }
        }
        System.out.println("");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((double) size / items.length < 0.25 && items.length > initSize) {
            resizeDown();
        }
        T res = getFirst();
        nextFirst = (nextFirst + 1) % items.length;
        items[nextFirst] = null;
        size -= 1;
        return res;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((double) size / items.length < 0.25 && items.length > initSize) {
            resizeDown();
        }
        T res = getLast();
        nextLast = ((nextLast - 1) + items.length) % items.length;
        items[nextLast] = null;
        size -= 1;
        return res;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int first = (nextFirst + 1) % items.length;
        int realIndex = (first + index) % items.length;
        return items[realIndex];
    }

    public T getLast() {
        int last = ((nextLast - 1) + items.length) % items.length;
        return items[last];
    }

    public T getFirst() {
        int first = (nextFirst + 1) % items.length;
        return items[first];
    }
}
