import java.util.HashMap;

public final class Ques1_2 {
    public static void main(String[] args) {
        String s1 = "abcdefg";
        String s2 = "gfedcca";
        System.out.println(checkPermutation(s1, s2));
    }

    private static boolean checkPermutation(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        HashMap<Character, Integer> hm1 = new HashMap<Character, Integer>();
        for (int i = 0, size = s1.length(); i<size; ++i) {
            Character c = s1.charAt(i);
            if (hm1.containsKey(c)){
                hm1.put(c, hm1.get(c)+1);
            }
            else {
                hm1.put(c, 1);
            }
        }

        HashMap<Character, Integer> hm2 = new HashMap<Character, Integer>();
        for (int i = 0, size = s2.length(); i<size; ++i) {
            Character c = s2.charAt(i);
            if (hm2.containsKey(c)){
                hm2.put(c, hm2.get(c)+1);
            }
            else {
                hm2.put(c, 1);
            }
        }

        if (hm1.equals(hm2))
            return true;
        
        return false;
    }
}