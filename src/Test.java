import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        //example1();
        //example2();
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
        testCase6();
        testCase7();
        testCase8();
    }

    public static void example1(){
        ArrayList<String> v = new ArrayList<>();
        v.add("y1");
        v.add("y2");
        v.add("y3");
        v.add("y4");
        ArrayList<DirectedEdgeData> e = new ArrayList<>();
        e.add(new DirectedEdgeData("y1", "y2", 2));
        e.add(new DirectedEdgeData("y1", "y3", 9));
        e.add(new DirectedEdgeData("y1", "y4", 8));
        e.add(new DirectedEdgeData("y2", "y3", 4));
        e.add(new DirectedEdgeData("y3", "y2", 5));
        e.add(new DirectedEdgeData("y3", "y4", 6));
        e.add(new DirectedEdgeData("y4", "y1", 10));

        SFG sfg = new SFG(v, e);

        System.out.println("forward paths: " + sfg.getAllForwardPaths());

        System.out.println("individual loops: " + sfg.getAllIndividualLoops());

        System.out.println("n non touching loops: " + sfg.getAllNonTouchingLoops());

        System.out.println("delta: " + sfg.getDelta());

        System.out.println("all deltas for forward paths: " + sfg.getAllDeltasOfForwardPaths());

        System.out.println("total transfer function: " + sfg.getTotalTransferFunction());
    }

    public static void example2(){ // sheet #04 problem #03
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
    
    public static void testCase1(){
        ArrayList<String> v = new ArrayList<>();
        for(int i=0; i<=5; i++){
            v.add(String.valueOf(i));
        }
        ArrayList<DirectedEdgeData> e = new ArrayList<>();
        e.add(new DirectedEdgeData("0", "1", 1));
        e.add(new DirectedEdgeData("1", "2", 1));
        e.add(new DirectedEdgeData("2", "3", 1));
        e.add(new DirectedEdgeData("3", "4", 1));
        e.add(new DirectedEdgeData("4", "5", 1));
        e.add(new DirectedEdgeData("2", "1", -1));
        e.add(new DirectedEdgeData("3", "2", -1));
        e.add(new DirectedEdgeData("3", "3", -1));
        e.add(new DirectedEdgeData("1", "3", 1));
        e.add(new DirectedEdgeData("1", "4", 1));

        SFG sfg = new SFG(v, e);

        if(sfg.getTotalTransferFunction() == 1.25){
            System.out.println("Test Case 1 : Succeeded");
        }
        else{
            System.out.println("Test Case 1 : Failed");
        }
    }

    public static void testCase2(){
        ArrayList<String> v = new ArrayList<>();
        for(int i=0; i<=4; i++){
            v.add(String.valueOf(i));
        }
        ArrayList<DirectedEdgeData> e = new ArrayList<>();
        e.add(new DirectedEdgeData("0", "1", 1));
        e.add(new DirectedEdgeData("1", "2", 1));
        e.add(new DirectedEdgeData("2", "3", 1));
        e.add(new DirectedEdgeData("3", "4", 1));
        e.add(new DirectedEdgeData("0", "2", 1));
        e.add(new DirectedEdgeData("3", "2", -1));
        e.add(new DirectedEdgeData("3", "1", -1));

        SFG sfg = new SFG(v, e);

        if(sfg.getTotalTransferFunction() == 2.0/3){
            System.out.println("Test Case 2 : Succeeded");
        }
        else{
            System.out.println("Test Case 2 : Failed");
        }

    }

    public static void testCase3(){
        ArrayList<String> v = new ArrayList<>();
        for(int i=0; i<=5; i++){
            v.add(String.valueOf(i));
        }
        ArrayList<DirectedEdgeData> e = new ArrayList<>();
        e.add(new DirectedEdgeData("0", "1", 1));
        e.add(new DirectedEdgeData("1", "2", 1));
        e.add(new DirectedEdgeData("2", "3", 1));
        e.add(new DirectedEdgeData("3", "4", 1));
        e.add(new DirectedEdgeData("4", "5", 1));
        e.add(new DirectedEdgeData("2", "1", -1));
        e.add(new DirectedEdgeData("4", "3", -1));
        e.add(new DirectedEdgeData("1", "3", 1));
        e.add(new DirectedEdgeData("4", "1", -1));

        SFG sfg = new SFG(v, e);

        if(sfg.getTotalTransferFunction() == 1.0/3){
            System.out.println("Test Case 3 : Succeeded");
        }
        else{
            System.out.println("Test Case 3 : Failed");
        }

    }

    public static void testCase4(){
        ArrayList<String> v = new ArrayList<>();
        for(int i=0; i<=6; i++){
            v.add(String.valueOf(i));
        }
        ArrayList<DirectedEdgeData> e = new ArrayList<>();
        e.add(new DirectedEdgeData("0", "1", 1));
        e.add(new DirectedEdgeData("1", "2", 1));
        e.add(new DirectedEdgeData("2", "3", 1));
        e.add(new DirectedEdgeData("4", "5", 1));
        e.add(new DirectedEdgeData("5", "6", 1));
        e.add(new DirectedEdgeData("3", "2", -1));
        e.add(new DirectedEdgeData("4", "4", -1));
        e.add(new DirectedEdgeData("1", "4", 1));
        e.add(new DirectedEdgeData("3", "5", 1));
        e.add(new DirectedEdgeData("5", "3", -1));
        e.add(new DirectedEdgeData("5", "1", -1));

        SFG sfg = new SFG(v, e);

        if(sfg.getTotalTransferFunction() == 0.4){
            System.out.println("Test Case 4 : Succeeded");
        }
        else{
            System.out.println("Test Case 4 : Failed");
        }

    }

    public static void testCase5(){
        ArrayList<String> v = new ArrayList<>();
        for(int i=0; i<=8; i++){
            v.add(String.valueOf(i));
        }
        ArrayList<DirectedEdgeData> e = new ArrayList<>();
        e.add(new DirectedEdgeData("0", "1", 1));
        e.add(new DirectedEdgeData("1", "2", 1));
        e.add(new DirectedEdgeData("2", "3", 1));
        e.add(new DirectedEdgeData("3", "4", 1));
        e.add(new DirectedEdgeData("4", "5", 1));
        e.add(new DirectedEdgeData("5", "6", 1));
        e.add(new DirectedEdgeData("6", "7", 1));
        e.add(new DirectedEdgeData("7", "8", 1));
        e.add(new DirectedEdgeData("2", "7", 1));
        e.add(new DirectedEdgeData("7", "1", -1));
        e.add(new DirectedEdgeData("5", "2", -1));
        e.add(new DirectedEdgeData("7", "4", -1));

        SFG sfg = new SFG(v, e);

        if(sfg.getTotalTransferFunction() == 0.5){
            System.out.println("Test Case 5 : Succeeded");
        }
        else{
            System.out.println("Test Case 5 : Failed");
        }

    }

    public static void testCase6(){
        ArrayList<String> v = new ArrayList<>();
        for(int i=0; i<=8; i++){
            v.add(String.valueOf(i));
        }
        ArrayList<DirectedEdgeData> e = new ArrayList<>();
        e.add(new DirectedEdgeData("0", "1", 1));
        e.add(new DirectedEdgeData("1", "2", 1));
        e.add(new DirectedEdgeData("2", "3", 1));
        e.add(new DirectedEdgeData("3", "4", 1));
        e.add(new DirectedEdgeData("4", "5", 1));
        e.add(new DirectedEdgeData("5", "6", 1));
        e.add(new DirectedEdgeData("6", "7", 1));
        e.add(new DirectedEdgeData("7", "8", 1));
        e.add(new DirectedEdgeData("3", "6", 1));
        e.add(new DirectedEdgeData("5", "7", 1));
        e.add(new DirectedEdgeData("5", "4", -1));
        e.add(new DirectedEdgeData("7", "5", -1));
        e.add(new DirectedEdgeData("6", "2", -1));
        e.add(new DirectedEdgeData("7", "1", -1));

        SFG sfg = new SFG(v, e);

        if(sfg.getTotalTransferFunction() == 1.0/3){
            System.out.println("Test Case 6 : Succeeded");
        }
        else{
            System.out.println("Test Case 6 : Failed");
        }

    }

    public static void testCase7(){
        ArrayList<String> v = new ArrayList<>();
        for(int i=0; i<=7; i++){
            v.add(String.valueOf(i));
        }
        ArrayList<DirectedEdgeData> e = new ArrayList<>();
        e.add(new DirectedEdgeData("0", "1", 1));
        e.add(new DirectedEdgeData("1", "2", 1));
        e.add(new DirectedEdgeData("2", "3", 1));
        e.add(new DirectedEdgeData("3", "4", 1));
        e.add(new DirectedEdgeData("4", "5", 1));
        e.add(new DirectedEdgeData("5", "6", 1));
        e.add(new DirectedEdgeData("6", "7", 1));
        e.add(new DirectedEdgeData("2", "1", -1));
        e.add(new DirectedEdgeData("4", "3", -1));
        e.add(new DirectedEdgeData("6", "5", -1));

        SFG sfg = new SFG(v, e);

        if(sfg.getTotalTransferFunction() == 0.125){
            System.out.println("Test Case 7 : Succeeded");
        }
        else{
            System.out.println("Test Case 7 : Failed");
        }

    }

    public static void testCase8(){
        ArrayList<String> v = new ArrayList<>();
        for(int i=0; i<=9; i++){
            v.add(String.valueOf(i));
        }
        ArrayList<DirectedEdgeData> e = new ArrayList<>();
        e.add(new DirectedEdgeData("0", "1", 1));
        e.add(new DirectedEdgeData("1", "2", 1));
        e.add(new DirectedEdgeData("2", "3", 1));
        e.add(new DirectedEdgeData("3", "4", 1));
        e.add(new DirectedEdgeData("4", "5", 1));
        e.add(new DirectedEdgeData("5", "6", 1));
        e.add(new DirectedEdgeData("6", "7", 1));
        e.add(new DirectedEdgeData("7", "8", 1));
        e.add(new DirectedEdgeData("8", "9", 1));
        e.add(new DirectedEdgeData("2", "1", -1));
        e.add(new DirectedEdgeData("4", "3", -1));
        e.add(new DirectedEdgeData("6", "5", -1));
        e.add(new DirectedEdgeData("8", "7", -1));

        SFG sfg = new SFG(v, e);

        if(sfg.getTotalTransferFunction() == 0.0625){
            System.out.println("Test Case 8 : Succeeded");
        }
        else{
            System.out.println("Test Case 8 : Failed");
        }

    }

}
