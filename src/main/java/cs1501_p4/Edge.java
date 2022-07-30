package cs1501_p4;

/**
 * Edge class, represent weighted edge in EdgeWeightedGraph
 */
public class Edge {

    private int v; 
    private int w; 
    private String type; // "optical" or "copper"
    private int bw; // bandwidth in megabits per second
    private int length; // meters 

    private double latency; // length/speed 

    private final int COPPER_SPEED = 230000000; // meters per second
    private final int OPTICAL_SPEED = 200000000; // meters per second
    
    public Edge( int v, int w, String type, int bw, int length){
        
        if (v < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (w < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");

        // TODO: check this 
        // if (! type.equals("optical") || !type.equals("copper")) throw new IllegalArgumentException("cable type must be copper or optical");

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

    public String type() {
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

    public int either() {
        return v; 
    }

    /**
     * Returns endpoint of this edge that is different from given vertex.
     * 
     * @param vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the endpoints of this edge
     */
    public int other(int vertex){
        if (vertex == v) return w;
        else if (vertex==w) return v; 
        else throw new IllegalArgumentException("Illegal endpoint"); 
    }

    // TODO: implement comparison methods based on each attribute 

    public String toString() {
        return String.format("%d-%d %.5f", v, w, type, bw, length, latency); 
    }
    
}
