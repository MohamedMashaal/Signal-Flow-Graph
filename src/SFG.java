import javafx.util.Pair;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.alg.cycle.JohnsonSimpleCycles;
import org.jgrapht.alg.cycle.TarjanSimpleCycles;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SFG {
    private Graph<String, DefaultWeightedEdge> graph;
    private String inputNode;
    private String outputNode;
    private ArrayList<VerticesAndGain> allForwardPaths;
    private ArrayList<VerticesAndGain> allIndividualLoops;
    private ArrayList<ArrayList<ArrayList<VerticesAndGain>>> allNonTouchingLoops;
    private Double delta;

    public SFG(ArrayList<String> vertices, ArrayList<DirectedEdgeData> edges) {
        this.inputNode = vertices.get(0);
        this.outputNode = vertices.get(vertices.size()-1);
        this.graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
        for(String vertex : vertices){
            graph.addVertex(vertex);
        }
        for(DirectedEdgeData edgeData : edges){
            System.out.println(edgeData.toString());
            DefaultWeightedEdge edge = graph.addEdge(edgeData.getFrom(), edgeData.getTo());
            graph.setEdgeWeight(edge, edgeData.getWeight());
        }
    }

    public ArrayList<VerticesAndGain> getAllForwardPaths(){
        if(allForwardPaths != null){
            return allForwardPaths;
        }

        if(graph == null)
            return null;

        ArrayList<VerticesAndGain> forwardPaths = new ArrayList<>();

        AllDirectedPaths<String, DefaultWeightedEdge> pathsFinder = new AllDirectedPaths<>(graph);
        List<GraphPath<String, DefaultWeightedEdge> > graphPaths = pathsFinder.getAllPaths(inputNode, outputNode, true, Integer.MAX_VALUE);

        for(GraphPath<String, DefaultWeightedEdge> graphPath : graphPaths){
            double gain = 1;
            List<DefaultWeightedEdge> edgeList = graphPath.getEdgeList();
            System.out.print(graphPath.getVertexList() + ": ");
            for(DefaultWeightedEdge edge : edgeList){
                System.out.print(graph.getEdgeWeight(edge) + " ");
                gain *= graph.getEdgeWeight(edge);
            }
            System.out.println();
            List<String> vertices = graphPath.getVertexList();

            forwardPaths.add(new VerticesAndGain(vertices, gain));
        }

        this.allForwardPaths = forwardPaths;

        return forwardPaths;
    }

    public ArrayList<VerticesAndGain> getAllIndividualLoops(){
        if(allIndividualLoops != null){
            return allIndividualLoops;
        }

        ArrayList<VerticesAndGain> individualLoops = new ArrayList<>();

        TarjanSimpleCycles<String, DefaultWeightedEdge> cycleDetector
                = new TarjanSimpleCycles<String, DefaultWeightedEdge>(graph);

        List<List<String>> cycleVertices = cycleDetector.findSimpleCycles();

        for(List<String> cycle : cycleVertices){
            double gain = getGain(cycle);
            //System.out.print("cycle: " + cycle + " " + gain + "\n");

            individualLoops.add(new VerticesAndGain(cycle, gain));
        }

        this.allIndividualLoops = individualLoops;

        return individualLoops;

    }

    public ArrayList<ArrayList<ArrayList<VerticesAndGain>>> getAllNonTouchingLoops(){
        if(allNonTouchingLoops != null){
            return allNonTouchingLoops;
        }

        ArrayList<VerticesAndGain> individualLoops = getAllIndividualLoops();
        ArrayList<ArrayList<ArrayList<VerticesAndGain>>> allNonTouchingLoops = new ArrayList<>();
        boolean stop = false;
        for(int n=2; !stop; n++){
            ArrayList<ArrayList<VerticesAndGain>> nCombinations = AlgoUtils.getAllCombinations(individualLoops, n);
            ArrayList<ArrayList<VerticesAndGain>> nNonTouchingLoops = new ArrayList<>();

            for(ArrayList<VerticesAndGain> combination : nCombinations){
                if(isNonTouching(combination)){
                    nNonTouchingLoops.add(combination);
                }
            }

            if(nNonTouchingLoops.isEmpty()){
                stop = true;
                continue;
            }

            allNonTouchingLoops.add(nNonTouchingLoops);

        }

        this.allNonTouchingLoops = allNonTouchingLoops;

        return allNonTouchingLoops;
    }

    public double getDelta(){
        if(delta != null){
            return delta;
        }

        double sumOfIndividualLoopGains = 0;
        for(VerticesAndGain loop : allIndividualLoops){
            sumOfIndividualLoopGains += loop.getGain();
        }

        int sign = 1;
        double resultOfAllNonTouchingLoops = 0;

        for(ArrayList<ArrayList<VerticesAndGain>> nNonTouchingLoops : allNonTouchingLoops){
            double sumOfGainProductsOfNNonTouchingLoops = 0;
            for(ArrayList<VerticesAndGain> combination : nNonTouchingLoops){
                double gainProductOfCombination = 1;
                for(VerticesAndGain loop : combination){
                    gainProductOfCombination *= loop.getGain();
                }
                sumOfGainProductsOfNNonTouchingLoops += gainProductOfCombination;
            }
            resultOfAllNonTouchingLoops += sign * sumOfGainProductsOfNNonTouchingLoops;
            sign *= -1;
        }

        this.delta = 1 - sumOfIndividualLoopGains + resultOfAllNonTouchingLoops;

        return this.delta;
    }

    private boolean isNonTouching(ArrayList<VerticesAndGain> combination){
        for(int i=0; i<combination.size(); i++){
            for(int j=0; j<combination.get(i).getVertices().size(); j++){
                String vertex = combination.get(i).getVertices().get(j);
                for(int k=i+1; k<combination.size(); k++){
                    if(combination.get(k).getVertices().contains(vertex)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private double getGain(List<String> cycle) {
        double gain = 1;
        for(int i=0; i<cycle.size()-1; i++){
            DefaultWeightedEdge edge = graph.getEdge(cycle.get(i), cycle.get(i+1));
            gain *= graph.getEdgeWeight(edge);
        }
        DefaultWeightedEdge edge = graph.getEdge(cycle.get(cycle.size()-1), cycle.get(0));
        gain *= graph.getEdgeWeight(edge);
        return gain;
    }
}
