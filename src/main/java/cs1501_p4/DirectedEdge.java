package cs1501_p4;

/**
 * This class represents a weighted edge in an EdgeWeightedDigraph
 */
public class DirectedEdge {

    private int v; 
    private int w;

    private String type; 
    private int bw; 
    private int length; 

    private double latency; 

    private final int COPPER_SPEED = 230000000; // meters per second
    private final int OPTICAL_SPEED = 200000000; // meters per second

    public DirectedEdge(int v, int w, String type, int bw, int length){
        if (v < 0) throw new IllegalArgumentException("Vertex names must be non-negative integers");
        if (w < 0) throw new IllegalArgumentException("Vertex names must be non-negative integers");

        this.v = v;
        this.w = w;

        this.type = type;
        this.bw = bw; 
        this.length = length; 

        if ( type.equals("optical")){
            latency = (double)length/OPTICAL_SPEED;
        } else if (type.equals("copper")){
            latency = (double)length/COPPER_SPEED;
        }

    }

    /**
     * Returns the tail vertex of the directed edge.
     * @return the tail vertex of the directed edge
     */
    public int from(){
        return v; 
    }
    
    /**
     * Returns the head vertex of the directed edge.
     * @return the head vertex of the directed edge
     */
    public int to(){
        return w; 
    }

    public String type(){
        return type;
    }

    public int bandwidth(){
        return bw;
    }

    public int length() {
        return length; 
    }

    public double latency(){
        return latency; 
    }

    public int compareLatencyTo( DirectedEdge q){

        if ( this.latency > q.latency){
            return 1; 
        } else if ( this.latency < q.latency ){
            return -1;
        } else {
            return 0; 
        }
    }






    



    
}
