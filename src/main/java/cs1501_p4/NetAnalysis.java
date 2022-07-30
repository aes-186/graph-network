/**
 * Network Analysis for CS1501 Project 4
 * @author	Anzu Sekikawa
 */

package cs1501_p4;

// import cs1501_p4.List.Node; 

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList; // import the ArrayList class

public class NetAnalysis implements NetAnalysis_Inter {

    // ************* DEBUG METHODS *************** // 

    public void print(String x){
        System.out.println(x); 
    }

    // *********** attributes *********** // 

    private int V; // number of vertices 

    private int E; // number of edges

    private List[] adj; //adjacency list //store ref to a Bag of Edges in each index

    private UF uf;  

    // private List copperEdges; 

    // private boolean[] copperVertices; 

    private EdgeWeightedGraph copperGraph; 

    private EdgeWeightedDigraph digraph; 

    private Graph vGraph; 

    private EdgeWeightedGraph primGraph; 

    //constructor
    public NetAnalysis(String fName){

        File file = new File( fName );

        Scanner sc = null;

        try {
            sc = new Scanner(file);

            int lineNum = 0; 

            //Check if there is another line of input
            while( sc.hasNextLine() ){

                String str = sc.nextLine(); //current line

                lineNum++;

                parseLine(str, lineNum);  

            }
        } catch (IOException exp) {
            exp.printStackTrace();
        }

        sc.close(); 
    }

    private void parseLine(String str, int lineNum){

        Scanner sc = new Scanner(str); 

        if ( lineNum == 1 ){
            V = sc.nextInt(); // num vertices in graph

            E = 0; 
            
            adj = new List[V]; 
            for (int v=0; v<V; v++) {
                adj[v] = new List(); 
            }

            uf = new UF(V);

            // copperEdges = new List(); 
            // copperVertices = new boolean[V]; 
            copperGraph = new EdgeWeightedGraph(V); 

            digraph = new EdgeWeightedDigraph(V); 

            vGraph = new Graph(V); 

            primGraph = new EdgeWeightedGraph(V); 
    
        } else {

            sc.useDelimiter(" "); 

            while( sc.hasNext()){

                int v = sc.nextInt(); 
                //print(v +""); 

                int w = sc.nextInt(); 
                //print(w+"");

                String type = sc.next(); 
                //print(type); 

                int bw = sc.nextInt(); 
                //print(bw+"");

                int length = sc.nextInt(); 
                //print(length+"");

                Edge newEdge = new Edge( v, w, type, bw, length); 

                //print("newEdge done");
                //print("\n");

                DirectedEdge e1 = new DirectedEdge( v, w, type, bw, length );
                DirectedEdge e2 = new DirectedEdge( w, v, type, bw, length);

                if (type.equals("copper")){
                    //copperEdges.add( newEdge );

                    copperGraph.addEdge(newEdge); 

                    //copperVertices[v]=true;
                    //copperVertices[w]=true; 
                }

                addEdge( newEdge );

                vGraph.addEdge(v,w); 

                digraph.addEdge(e1); 
                digraph.addEdge(e2); 

                primGraph.addEdge(newEdge); 

            } //end while

        } // end else 

        sc.close();

    }

    /**
     * Returns the number of vertices in this edge-weighted graph
     * @return the number of vertices in this edge-weighted graph
     */
    public int V(){
        return V; 
    }

    /**
     * Returns the number of edges in this edge-weighted graph
     * 
     * @return the number of edges in this edge weighted graph 
     */
    public int E(){
        return E; 
    }

    private void validateVertex(int v){
        if ( v<0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the undirected edge to this edge-weighted graph.
     * 
     * @param e the edge
     * @throws IllegalArgumentException unless both endpoints are between 0 and V-1
     */
    public void addEdge(Edge e){
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
     * Returns the edges incident on vertex {@code v}
     * 
     * @param v the vertex
     * @return the edges incident on vertex {@code v} as a Bag
     * @throws IllegalArgumentException unless {@code 0 <= v <= V}
     */
    public List adj(int v){
        validateVertex(v);
        return adj[v]; 
    }

    /**
     * Returns the degree of vertex {@code v}.
     * 
     * @param v the vertex
     * @return the degree of vertex 
     * @throws IllegalArgumentException unless {@code 0 <= v <= V}
     */
    public int degree(int v){
        validateVertex(v); 
        return adj[v].size(); 
    }

    /**
     * Return a List of all edges in this graph 
     * 
     * @return a List of all edges in this graph
     */
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
	 * Find the lowest latency path from vertex `u` to vertex `w` in the graph
	 *
	 * @param	u Starting vertex
	 * @param	w Destination vertex
	 *
	 * @return	ArrayList<Integer> A list of the vertex id's representing the
	 * 			path (should start with `u` and end with `w`)
	 * 			Return `null` if no path exists
	 */
	public ArrayList<Integer> lowestLatencyPath(int u, int w){

        ArrayList<Integer> pathVertices = new ArrayList<>(); 

        DijkstraSP sp = new DijkstraSP( digraph, u); // u is the source vertex

        Stack path = sp.pathTo(w); 

        if (path==null) return null;  

        while ( !path.isEmpty() ){
            DirectedEdge curr = path.pop();

            int p = curr.from(); 
            //int q = curr.to(); 

            pathVertices.add(p); 
        }

        pathVertices.add(w); 

        return pathVertices; 
    }



	/**
	 * Find the bandwidth available along a given path through the graph
	 * (the minimum bandwidth of any edge in the path). Should throw an
	 * `IllegalArgumentException` if the specified path is not valid for
	 * the graph.
	 *
	 * @param	ArrayList<Integer> A list of the vertex id's representing the
	 * 			path
	 *
	 * @return	int The bandwidth available along the specified path
	 */
	public int bandwidthAlongPath(ArrayList<Integer> p) throws IllegalArgumentException{

        // check that the path is connected 

        int pathLength = p.size(); 

        BWMinPQ minPQ = new BWMinPQ(pathLength); 

        for (int i=0; i<pathLength-1; i++){

            int v = p.get(i);
            int w = p.get(i+1);
            
            validateVertex( v ); 
            validateVertex( w ); 

            List edgeList = adj[v]; 

            Edge currEdge = edgeList.findEdge(v,w); //throws IllegalArgException if no edge btwn v and w

            minPQ.insert( currEdge ); // add Edge to minPQ

        }

        return minPQ.min().bandwidth(); 

    }

	/**
	 * Return `true` if the graph is connected considering only copper links
	 * `false` otherwise
	 *
	 * @return	boolean Whether the graph is copper-only connected
	 */
	public boolean copperOnlyConnected(){
        
        // int copperV = 0; 

        // // count number of vertices 
        // for( int i=0; i<V; i++ ){
        //     if (copperVertices[i]){
        //         copperV++; 
        //     }
        // }

        // print("num copper vertices " + copperV ); 

        // EdgeWeightedGraph copperGraph = new EdgeWeightedGraph(V); 

        //Graph copperGraph = new Graph(copperV); 

        // print("created empty copperGraph with correct num vertices");

        //Node curr = copperEdges.first;
        //Edge currEdge; 

        // while ( curr != null){

        //     currEdge = curr.item; 

        //     int v1 = currEdge.either();
        //     int v2 = currEdge.other(v1); 

        //     if (v1 >= copperV || v2 >= copperV ){
        //         v1--;
        //         v2--;
        //     }

        //     copperGraph.addEdge(v1,v2); 

        //     // copperGraph.addEdge( currEdge );

        //     print("adding edge (" + v1 + ", " + v2 + ")"  );

        //     curr = curr.next; 
            
        // }

        // print("exited while loop");

        if ( copperGraph.connectedComponents() == 1){
            return true;
        } else {

            // TODO: check this 
            // assert copperGraph.connectedComponents() > 1 ;

            return false; 
        }
    }

	/**
	 * Return `true` if the graph would remain connected if any two vertices in
	 * the graph would fail, `false` otherwise
	 *
	 * @return	boolean Whether the graph would remain connected for any two
	 * 			failed vertices
	 */
	public boolean connectedTwoVertFail(){

        if (uf.count()>1){
            return false; 
        }

        Biconnected bc = new Biconnected(vGraph); 

        for(int i=0; i<V; i++){
            if ( bc.isArticulation(i) ){
                return false; 
            }
        }

        // TODO: what if the 2nd articulation point is hidden

        // reconstruct Graph with V-1 vertices
        // at most, iterate V times 
        // each iteration, remove one vertex, check that it's still biconnected

        // per iteration, try removing vertex 'j'

        for( int j=0; j<V; j++ ){

            // what if i make this graph size V? 
            Graph tempGraph = new Graph(V-1); 

            List edgeList = primGraph.edges();
            Edge[] arr = edgeList.toArray(); 

            // construct new Graph with V-1 edges 
            // with vertex j missing
            for( int x=0; x<arr.length; x++ ){
                Edge e = arr[x]; 

                int v1 = e.either();
                int v2 = e.other(v1);
                
                if (v1 != j && v2 != j){

                    if (v1>j){
                        v1 = v1-1; 
                    }
    
                    if (v2>j){
                        v2 = v2-1; 
                    }

                    tempGraph.addEdge(v1,v2);
                }
            }

            // if it gets this far - the graph is biconnected 

            Biconnected tempBc = new Biconnected( tempGraph ); 
            
            for(int i=0; i<V-1; i++){
                if ( tempBc.isArticulation(i) ){

                    // System.out.println("Current vertex: " + i); 

                    return false; 
                }
            }
        }

        return true; 

    }

	/**
	 * Find the lowest average (mean) latency spanning tree for the graph
	 * (i.e., a spanning tree with the lowest average latency per edge). Return
	 * it as an ArrayList of STE edges.
	 *
	 * Note that you do not need to use the STE class to represent your graph
	 * internally, you only need to use it to construct return values for this
	 * method.
	 *
	 * @return	ArrayList<STE> A list of STE objects representing the lowest
	 * 			average latency spanning tree
	 * 			Return `null` if the graph is not connected
	 */
	public ArrayList<STE> lowestAvgLatST(){

        if ( uf.count() > 1 ) return null; 

        ArrayList<STE> latST = new ArrayList<>(); 

        LazyPrimMST prim = new LazyPrimMST( primGraph );

        Queue mst = prim.edges(); 
        // go from Queue to creating ArrayList<STE>
        
        for( Edge e : mst ){

            int v1 = e.either();
            int v2 = e.other(v1); 

            STE edge = new STE(v1, v2); 

            latST.add(edge); 
        }

        return latST; 
    }

    
}
