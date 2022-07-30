package cs1501_p4;

import java.util.ArrayList; 

public class AnzuApp {

    public static void print(String x){
        System.out.println(x.toString()); 
    }

    public static void main(String[] args) {

        NetAnalysis na2 = new NetAnalysis("app/src/main/resources/network_data2.txt");

        ArrayList<Integer> path1 = na2.lowestLatencyPath(0,4);

        // print(path1.toString()); 

        ArrayList<Integer> path2 = na2.lowestLatencyPath(1,2);

        // print(path2.toString()); 

        ArrayList<Integer> bwPath1 = new ArrayList<>(); 
        bwPath1.add(0);
        bwPath1.add(2);
        bwPath1.add(5);
        bwPath1.add(8);
        bwPath1.add(6);
        bwPath1.add(0);

        int bw1 = na2.bandwidthAlongPath(bwPath1);

        // print(bw1+"");

        if( na2.copperOnlyConnected() ){
            print("TRUE: copper only connected");
        } else {
            print("FALSE: not copper only connected");
        }

        if( na2.connectedTwoVertFail() ){
            print("tri connected");
        } else {
            print("NOT tri connected" ); 
        }

        ArrayList<STE> pathSTE = na2.lowestAvgLatST(); 

        print(pathSTE.toString());




        //NetAnalysis na = new NetAnalysis("/Users/Anzu/OneDrive - University of Pittsburgh/JavaPrograms/CS1501/Assignments/project4-aes-186/app/src/main/resources/network_dataAnzu.txt");

        // ArrayList<Integer> path = na.lowestLatencyPath(1,0);

        // if ( path == null ){
        //     System.out.print("NULL"); 
        // }
        
        // // System.out.print( path ); 

        // ArrayList<Integer> p = new ArrayList<>();
        // p.add(1); 
        // p.add(0); 

        // int x = na.bandwidthAlongPath(p);

        // // print( "Min band width: " + x  ); 

        // boolean val = na.copperOnlyConnected(); 

        // if ( val ){
        //     print("Copper only connected TRUE");
        // } else {
        //     print("Copper only connected FALSE");
        // }

        // boolean val2 = na.connectedTwoVertFail(); 

        // if (val2){
        //     print("Triconnected TRUE");
        // } else {
        //     print("Triconnected FALSE");
        // }

        // ArrayList<STE> lalPath = new ArrayList<>(); 

        // lalPath = na.lowestAvgLatST(); 

        // print( lalPath.toString() ); 

        // NetAnalysis na1 = new NetAnalysis("app/src/main/resources/network_data3.txt"); 

        // ArrayList<Integer> path = na1.lowestLatencyPath(0,5); 

        // if (path==null){
        //     print("NULL path");
        // } else {
        //     print( path.toString() ); 
        // }

        // ArrayList<Integer> ints = new ArrayList<>();
        // ints.add(1);
        // ints.add(4);
        // ints.add(5);
        // ints.add(6);

        // int x = na1.bandwidthAlongPath(ints); 

        // print(x+"");

        // boolean val = na1.copperOnlyConnected();

        // if(val) print("Copper only connected TRUE");
        // else print("NOT copper only connected");

        // boolean val2 = na1.connectedTwoVertFail();
        // if(val2) print("Triconnected TRUE");
        // else print("Triconnected false");

        

    }
    
}
