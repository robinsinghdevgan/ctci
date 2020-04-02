import java.util.ArrayList;

public class Ques8_4 {
    public static void main(String[] args) {
        int[] arr = {0,1};
        printPowerSet(arr);
        
        int[] arr1 = {0,1,2};
        printPowerSet(arr1);
        int[] arr2 = {0,1,2,3};
        printPowerSet(arr2);
        int[] arr3 = {0,1,2,3,4};
        printPowerSet(arr3);
    }

    private static void printPowerSet(int[] arr) {
        var powerSet = new ArrayList<ArrayList<Integer>>();
        //power-set contains an empty subset
        powerSet.add(new ArrayList<Integer>());

        for (int i=0; i<arr.length; ++i) {
            var newSetCollection = new ArrayList<ArrayList<Integer>>();
            for(var subset : powerSet) {
                var newSubset = new ArrayList<Integer>();
                newSubset.addAll(subset);
                newSubset.add(arr[i]);
                newSetCollection.add(newSubset);
            }
            powerSet.addAll(newSetCollection);
        }
        print2DArray(powerSet);
    }

    private static void print2DArray(ArrayList<ArrayList<Integer>> mx) {
        for (var i : mx) {
            for (var j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}