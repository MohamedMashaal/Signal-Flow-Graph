import java.util.ArrayList;
import java.util.List;

public class AlgoUtils {

    private static ArrayList<VerticesAndGain> combination;
    private static ArrayList<ArrayList<VerticesAndGain>> combinations;
    private static boolean[] used;

    public static ArrayList<ArrayList<VerticesAndGain>>
                    getAllCombinations(ArrayList<VerticesAndGain> arr, int length){

        combination = new ArrayList<>();
        combinations = new ArrayList<>();
        used = new boolean[arr.size()];

        getAllCombinationsRecurse(0, arr, length);
        return combinations;
    }

    private static void getAllCombinationsRecurse(int index, ArrayList<VerticesAndGain> arr, int length){
        //System.out.println(combination + " " + combination.size());
        if(combination.size() == length){
            //System.out.println("found: " + combination + " all: " + combinations);
            ArrayList<VerticesAndGain> aCombination = new ArrayList<>();
            aCombination.addAll(combination);
            combinations.add(aCombination);
            //combination = new ArrayList<>();
            return;
        }

        if(index == arr.size())
            return;

        // take it
        combination.add(arr.get(index));
        getAllCombinationsRecurse(index+1, arr, length);

        //leave it
        combination.remove(arr.get(index));
        getAllCombinationsRecurse(index+1, arr, length);
    }

}
