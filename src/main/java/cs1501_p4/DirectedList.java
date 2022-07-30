package cs1501_p4;


// same as List, except stores collection of Directed Edges
public class DirectedList {

    protected Node first; // beginning of bag
    protected int n; // number of items in bag

    protected class Node {
        protected DirectedEdge item; 
        protected Node next; 
    }

    /**
     * Initialize an empty bag
     */
    public DirectedList(){
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
    public void add(DirectedEdge item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst; 
        n++; 
    }

    public DirectedEdge[] toArray(){

        DirectedEdge[] result = new DirectedEdge[ n ]; 
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
