package cs1501_p4;


public class EdgeWeightedGraph {

    private int V; 
    private int E; 
    private List[] adj; 

    private UF uf;  


    /**
     * Initializes an empty edge-weighted graph with {@code V} vertices and 0 edges.
     *
     * @param  V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public EdgeWeightedGraph(int V){
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = new List[V]; 
        for (int v = 0; v < V; v++) {
            adj[v] = new List();
        }

        uf = new UF(V); 

    }

    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the undirected edge {@code e} to this edge-weighted graph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless both endpoints are between {@code 0} and {@code V-1}
     */
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);

        uf.union(v,w); 

        E++;
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}               
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public List edges(){
        List list = new List();
        for( int v=0; v<V; v++){

            List currList = adj[v];

            Edge[] listArray = currList.toArray(); 

            for( int i=0; i< listArray.length; i++){
                list.add( listArray[i] ); 
            }
        }
        return list; 
    }

    /**
     * Returns the edges incident on vertex {@code v}.
     *
     * @param  v the vertex
     * @return the edges incident on vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public List adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public String toString() {
        String s = ""; 
        s += (V + E + "\n"); 
        for (int v=0; v<V; v++){
            s += (v + ": ");

            List currList = adj[v]; 
            Edge[] arr = currList.toArray(); 
            for( int i=0; i<arr.length; i++){
                Edge e = arr[i]; 
                s += (e + " "); 
            }
            s+="\n"; 
        }
        return s; 
    }

    /**
     * Returns the number of connected components in this graph
     * 
     * @return the number of connected components in this graph
     */
    public int connectedComponents(){
        return uf.count(); 
    }

   









    
}
