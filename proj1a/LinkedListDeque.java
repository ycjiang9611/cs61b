public class LinkedListDeque<T> implements Deque<T> {

    // try circular approach, using only one sentinel node.
    private final Node<T> sentinel;
    private int size;

        public static class Node<T> {
            public T item;
            public Node<T> next;
            public Node<T> prev;
            // like __init__ in python, if we want to use same variable name and parameter
            //name, we need to use this.item = item... (self.age = age)
            /**
            public Node(T i) {
                this(i,null, null);
            }
             */
            public Node(T i, Node<T> n, Node<T> m) {
                item = i;
                next = n;
                prev = m;
            }
        }


    // constructor
    public LinkedListDeque() {
        size = 0;
        this.sentinel = new Node(1, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        size += 1;
        sentinel.next = new Node<>(item, sentinel.next, sentinel);
    }

    @Override
    public void addLast(T item) {
        size += 1;
        sentinel.prev = new Node<>(item, sentinel, sentinel.prev);
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
        Node<T> cur = sentinel.next;
        while (cur != sentinel) {
            System.out.print(cur.item);
            System.out.print(" ");
            cur = cur.next;
        }
        System.out.println("");
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<T> rem = sentinel.next;
        T ele = rem.item;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return ele;
    }

    @Override
    public T removeLast() {
        // remember to check case with 0 and 1 element for circular sentinel!
        if (size == 0) {
            return null;
        }
        Node<T> rem = sentinel.prev;
        T ele = rem.item;
        sentinel.prev.prev.next = sentinel;
        size -= 1;
        return ele;
    }

    @Override
    public T get(int index) {
        if (index > size-1 || index < 0) {
            return null;
        }
        Node<T> cur = sentinel.next;
        int i = 0;
        while (i < index) {
            cur = cur.next;
            i += 1;
        }
        return cur.item;
    }

    public T getRecursive(int index) {
        if (index > size-1 || index < 0) {
            return null;
        }
        return getNode(index).item;
    }

    public Node<T> getNode(int index) {
        if (index == 0) {
            return sentinel.next;
        }
        return getNode(index - 1).next;
    }

}
