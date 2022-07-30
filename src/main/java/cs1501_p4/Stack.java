package cs1501_p4;

import java.util.NoSuchElementException;

/**
 * Stack used in Dijkstra's algo 
 */
public class Stack {

    // initial capacity of underlying resizing array
    private static final int INIT_CAPACITY = 8;

    private DirectedEdge[] a;         // array of items
    private int n;            // number of elements on stack

    /**
     * Initializes an empty stack.
     */
    public Stack() {
        a = new DirectedEdge[INIT_CAPACITY];
        n = 0;
    }

     /**
     * Is this stack empty?
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in the stack.
     * @return the number of items in the stack
     */
    public int size() {
        return n;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        DirectedEdge[] copy = new DirectedEdge[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }
        a = copy;

       // alternative implementation
       // a = java.util.Arrays.copyOf(a, capacity);
    }

    /**
     * Adds the item to this stack.
     * @param item the item to add
     */
    public void push(DirectedEdge item) {
        if (n == a.length) resize(2*a.length);    // double size of array if necessary
        a[n++] = item;                            // add item
    }

    /**
     * Removes and returns the item most recently added to this stack.
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public DirectedEdge pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        DirectedEdge item = a[n-1];
        a[n-1] = null;                              // to avoid loitering
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public DirectedEdge peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return a[n-1];
    }
   
}
