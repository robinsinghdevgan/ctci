import java.util.ArrayList;
import java.util.HashMap;

public class Ques8_8 {
    public static void main(String[] args) {
        String[] S = {"aaaaaaa", "abc", "aabbc"};
        for (var s : S) {
            HashMap<Character, Integer> hm = buildFrequencyHashMap(s);
            ArrayList<String> result = new ArrayList<String>();
            findPermutations(hm, "", s.length(), result);

            System.out.println(result);
        }
    }

    private static void findPermutations(HashMap<Character, Integer> hm, String prefix, int charactersYetToBeProcessed,
            ArrayList<String> result) {
        //base case
        if (charactersYetToBeProcessed == 0) {
            result.add(prefix);
            return;
        }
        for (Character c : hm.keySet()) {
            Integer count = hm.get(c);
            if (count > 0) {
                hm.put(c, count - 1);
                findPermutations(hm, prefix + c, charactersYetToBeProcessed - 1, result);
                hm.put(c, count);
            }
        }        
    }

    private static HashMap<Character, Integer> buildFrequencyHashMap(String s) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for (Character c : s.toCharArray()) {
            if (!hm.containsKey(c)) {
                hm.put(c, 1);
            }
            else {
                hm.put(c, hm.get(c) + 1);
            }
        }
        return hm;
    }

}