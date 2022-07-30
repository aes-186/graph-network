package cs1501_p4;

import java.util.NoSuchElementException;

// band width is comparator 
public class BWMinPQ {

    private Edge[] pq; // store items at indices 1 to n
    private int n; // num items on priority queue
    
    public BWMinPQ(int initCap) {
        pq = new Edge[ initCap+1 ]; 
        n = 0; 
    }

    public BWMinPQ(){
        this(1); 
    }

    public boolean isEmpty(){
        return n==0; 
    }    

    public int size(){
        return n; 
    }

    public Edge min(){
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow"); 
        return pq[1]; 
    }

    private void resize(int cap){
        assert cap > n; 
        Edge[] temp = new Edge[cap];
        for (int i=1; i<=n; i++){
            temp[i]=pq[i]; 
        }
        pq = temp; 
    }

    public void insert(Edge x){
        // double size of array if necessary
        if (n == pq.length - 1) resize(2 * pq.length);

        // add x, and percolate it up to maintain heap invariant
        pq[++n] = x;
        swim(n);
        assert isMinHeap();
    }

    /**
     * Removes and returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Edge delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Edge min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;     // to avoid loitering and help with garbage collection
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMinHeap();
        return min;
    }

    /***************************************************************************
    * Helper functions to restore the heap invariant.
    ***************************************************************************/

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    /***************************************************************************
    * Helper functions for compares and swaps.
    ***************************************************************************/
    private boolean greater(int i, int j) {
        
        Edge e1 = pq[i];
        Edge e2 = pq[j];

        return e1.bandwidth()-e2.bandwidth() > 0; 

    }

    private void exch(int i, int j) {
        Edge swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is pq[1..n] a min heap?
    private boolean isMinHeap() {
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = n+1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMinHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && greater(k, left))  return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

}
