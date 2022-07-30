package cs1501_p4;

/**
 * Used in Graph.java
 */
public class VertexList {

    protected Node first; // beginning of bag
    protected int n; // number of items in bag

    protected class Node {
        protected Integer item; 
        protected Node next; 
    }

    /**
     * Initialize an empty bag
     */
    public VertexList(){
        first = null;
        n=0; 
    }
    
    /**
     * Returns true if this bag is empty 
     * 
     * @return {@code true} if this bag is empty;
     *          {@code false} otherwise
     */
    public boolean isEmpty(){
        return first == null; 
    }

    /**
     * Returns the number of items in this bag.
     * 
     * @return the number of items in this bag
     */
    public int size(){
        return n; 
    }

    /**
     * Adds the item to this bag
     * 
     * @param item the item to add to this bag 
     */
    public void add(Integer item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst; 
        n++; 
    }

    public Integer[] toArray(){
        Integer[] result = new Integer[ n ]; 
        int index = 0; 
        Node currNode = first;

        while ( ( index<n) && (currNode!=null) ){
            result[index]=currNode.item; 
            currNode = currNode.next; 
            index++; 
        } // end while 

        return result; 
    } // end toArray 





    
}
