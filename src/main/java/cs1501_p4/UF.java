package cs1501_p4;

/**
 * The {@code UF} class represents a union-find data type 
 * It supports the classic union and find operations, along with a count operation
 * that returns the total number of sets. 
 */
public class UF {

    private int[] parent; // parent[i] = parent of i 
    private int[] size; // size[i] = size of subtree rooted at i
    private int count; // number of components

    public UF(int n) {
        if (n<0) throw new IllegalArgumentException(); 
        count = n; 
        parent = new int[n]; 
        size = new int[n]; 
        for (int i=0; i<n; i++){
            parent[i]=i; 
            size[i]=0; 
        }
    }

    public int find(int p){
        validate(p); 
        while (p != parent[p]){
            parent[p] = parent[ parent[p] ]; // path compression by halving
            p = parent[p]; 
        }
        return p; 
    }

    public int count() {
        return count; 
    }

    public boolean connected(int p, int q){
        return find(p)==find(q); 
    }

    public void union(int p, int q){
        int rootP = find(p); 
        int rootQ = find(q); 
        if (rootP == rootQ) return; 

        // make root of smaller subtree point to root of larger subtree
        if ( size[rootP] < size[rootQ]) parent[rootP]=rootQ;
        else if (size[rootP] > size[rootQ]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP; 
            size[rootP]+= size[rootQ]; 
        }
        count--; 
    }

    private void validate(int p){
        int n = parent.length; 
        if (p<0 || p>=n) {
            throw new IllegalArgumentException("index "+p+" is not between 0 and "+(n-1)); 
        }
    }
    
}
