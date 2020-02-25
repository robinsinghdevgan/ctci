import java.util.HashMap;

public final class Ques1_4{
    public static void main(String[] args) {
        String s = "TactoCoa";//"Tact Coa";
        System.out.println(palindromePermutation(s));
    }

    private static Boolean palindromePermutation(String s) {
        //as case in inconsidered here, convert 's' to lowercase
        s = s.toLowerCase();
        //remove all whitespaces, "\\s" is a regex string for whitespace '\s'
        s = s.replaceAll("\\s", "");
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        //atmost one odd count of occurence can be there for a string to be palindrome
        int countOdd = 0;
        for (int i = 0, size = s.length(); i<size; ++i) {
            Character c = s.charAt(i);
            if (hm.containsKey(c)){
                int value = hm.get(c);
                if (value%2 == 0)
                    ++countOdd;
                else
                    --countOdd;
                hm.put(c, value+1);
            }
            else {
                hm.put(c, 1);
                ++countOdd;
            }
        }
        return countOdd <=1;
    }
}