import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        example1();
        example2();
    }

    public static void example1(){
        ArrayList<String> v = new ArrayList<>();
        v.add("y1");
        v.add("y2");
        v.add("y3");
        v.add("y4");
        ArrayList<DirectedEdgeData> e = new ArrayList<>();
        e.add(new DirectedEdgeData("y1", "y2", 2));
        //e.add(new DirectedEdgeData("y1", "y2", 3));
        e.add(new DirectedEdgeData("y1", "y3", 9));
        e.add(new DirectedEdgeData("y1", "y4", 8));
        e.add(new DirectedEdgeData("y2", "y3", 4));
        e.add(new DirectedEdgeData("y3", "y2", 5));
        e.add(new DirectedEdgeData("y3", "y4", 6));
        //e.add(new DirectedEdgeData("y3", "y4", 7));
        e.add(new DirectedEdgeData("y4", "y1", 10));

        SFG sfg = new SFG(v, e);

        System.out.println("forward paths: " + sfg.getAllForwardPaths());

        System.out.println("individual loops: " + sfg.getAllIndividualLoops());

        System.out.println("n non touching loops: " + sfg.getAllNonTouchingLoops());

        System.out.println("delta: " + sfg.getDelta());

        System.out.println("all deltas for forward paths: " + sfg.getAllDeltasOfForwardPaths());

        System.out.println("total transfer function: " + sfg.getTotalTransferFunction());
    }

    public static void example2(){
        ArrayList<String> v = new ArrayList<>();
        v.add("y1");
        v.add("y2");
        v.add("y3");
        v.add("y4");
        v.add("y5");
        v.add("y6");
        v.add("y7");
        ArrayList<DirectedEdgeData> e = new ArrayList<>();
        e.add(new DirectedEdgeData("y1", "y2", 1));
        //e.add(new DirectedEdgeData("y1", "y2", 3));
        e.add(new DirectedEdgeData("y2", "y3", 5));
        e.add(new DirectedEdgeData("y3", "y4", 10));
        e.add(new DirectedEdgeData("y4", "y5", 2));
        e.add(new DirectedEdgeData("y5", "y7", 1));
        e.add(new DirectedEdgeData("y4", "y3", -1));
        e.add(new DirectedEdgeData("y5", "y4", -2));
        e.add(new DirectedEdgeData("y5", "y2", -1));
        e.add(new DirectedEdgeData("y2", "y6", 10));
        e.add(new DirectedEdgeData("y6", "y5", 2));
        e.add(new DirectedEdgeData("y6", "y6", -1));

        SFG sfg = new SFG(v, e);

        System.out.println("forward paths: " + sfg.getAllForwardPaths());

        System.out.println("individual loops: " + sfg.getAllIndividualLoops());

        System.out.println("n non touching loops: " + sfg.getAllNonTouchingLoops());

        System.out.println("delta: " + sfg.getDelta());

        System.out.println("all deltas for forward paths: " + sfg.getAllDeltasOfForwardPaths());

        System.out.println("total transfer function: " + sfg.getTotalTransferFunction());
    }
}
