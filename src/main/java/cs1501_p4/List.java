package cs1501_p4;


/**
 * linked list implemented using singly linked list
 * 
 * use singly linked list with nested class Node 
 * 
 */
public class List {

    protected Node first; // beginning of bag
    protected int n; // number of items in bag

    protected class Node {
        protected Edge item; 
        protected Node next; 
    }

    /**
     * Initialize an empty bag
     */
    public List(){
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
    public void add(Edge item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst; 
        n++; 
    }

    /**
     * Returns an edge that connects vertex p and vertex q 
     * 
     * @param p the endpoint of this edge
     * @param q the other endpoint of this edge 
     * @return the Edge that connects endpoints p and q
     * @throws IllegalArgumentException if p or q are invalid vertices
     * @throws IllegalArgumentException if no such Edge exists 
     */
    public Edge findEdge(int p, int q) {

        Node curr = first; 
        Edge currEdge; 
        while (curr!=null){

            currEdge = curr.item; 
            if (currEdge.either()==p && currEdge.other(p)==q){
                return currEdge; 
            } else if ( currEdge.either()==q && currEdge.other(q)==p){
                return currEdge; 
            }

            curr = curr.next; 
        }

        throw new IllegalArgumentException("No Edge exists between endpoints " + p + " and " + q);

    }

    public Edge[] toArray(){
        Edge[] result = new Edge[ n ]; 
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
