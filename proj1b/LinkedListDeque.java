public class LinkedListDeque<T> implements Deque<T> {

    // try circular approach, using only one sentinel node.
    // instance variables.
    private final Node<T> sentinel;
    private int size;

        // nested class.
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
        this.sentinel = new Node(1, null, null); // create sentinel node for all LinkedListDeque instances.
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        size += 1;
        Node<T> p = new Node<>(item, sentinel.next, sentinel); // create a new Node and link p's next to s.n, prev to s.
        sentinel.next.prev = p; // because we are using doubly linked list, we need to link another two arrows, from s.n to p
        sentinel.next = p; // and from s to p.
    }

    @Override
    public void addLast(T item) {
        size += 1;
        Node<T> p = new Node<>(item, sentinel, sentinel.prev);
        sentinel.prev.next = p;
        sentinel.prev = p;
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
        Node<T> cur = sentinel.next; // the first Node.
        while (cur != sentinel) {
            System.out.print(cur.item);
            System.out.print(" ");
            cur = cur.next;
        }
        System.out.println("");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> rem = sentinel.next; // get the first Node.
        sentinel.next.next.prev = sentinel; // LHS is the pointer(.next or .prev area), RHS is the Node be pointed.
        sentinel.next = sentinel.next.next;
        size -= 1;
        return rem.item;
    }

    @Override
    public T removeLast() {
        // remember to check case with 0 and 1 element for circular sentinel!
        if (isEmpty()) {
            return null;
        }
        Node<T> rem = sentinel.prev; // get the last Node.
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return rem.item;
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
