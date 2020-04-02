import java.util.*;
public class Ques8_7 {
    public static void main(String[] args) {
        ArrayList<Object> a = new ArrayList<Object>();
        String s = "abc";
        for (var c : s.toCharArray()) {
            a.add(c);
        }
        permutations(a);
        ArrayList<Object> b = new ArrayList<Object>();
        for (var i : new Integer[]{1,2,3}){
            b.add(i);
        }
        permutations(b);
        
        stringPermutations(s);
    }

    private static ArrayList<String> stringPermutations(String s) {
        ArrayList<String> result = new ArrayList<String>();
        stringPermutationsHelper(0, new StringBuilder(s), result);
        for(var list : result) {
            System.out.println(list);
        }
        return result;
    }


    private static void stringPermutationsHelper(int i, StringBuilder sb, ArrayList<String> result) {
        if (i == sb.length() - 1) {
            result.add(new String(sb.toString()));
            return;
        }
        for (int j=i; j < sb.length(); ++j) {
            swap(sb, i, j);
            stringPermutationsHelper(i+1, sb, result);
            swap(sb, i, j);
        }
    }

    private static void swap(StringBuilder sb, int i, int j) {
        char temp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, temp);
    }

    private static ArrayList<ArrayList<Object>> permutations(ArrayList<Object> A) {
        ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
        directedPermutations(0, A, result);
        for(var list : result) {
            System.out.println(list);
        }
        return result;
    }

    private static void directedPermutations(int i, ArrayList<Object> A, ArrayList<ArrayList<Object>> result) {
        if (i == A.size() - 1) {
            result.add(new ArrayList<>(A));
            return;
        }
        for (int j=i; j < A.size(); ++j) {
            Collections.swap(A, i, j);
            directedPermutations(i+1, A, result);
            Collections.swap(A, i, j);
        }
    }
}