import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
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
    }
}
