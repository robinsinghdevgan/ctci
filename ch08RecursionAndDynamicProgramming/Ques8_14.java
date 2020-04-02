import java.util.HashMap;

public class Ques8_14 {	public static int count = 0;
	public static boolean stringToBool(String c) {
		return c.equals("1") ? true : false;
	}
	
	public static int countEval(String s, boolean result, HashMap<String, Integer> memo) {
		count++;
		if (s.length() == 0) return 0;
		if (s.length() == 1) return stringToBool(s) == result ? 1 : 0;
		if (memo.containsKey(result + s)) return memo.get(result + s);

		int ways = 0;
		
		for (int i = 1; i < s.length(); i += 2) {
			char c = s.charAt(i);
			String left = s.substring(0, i);
			String right = s.substring(i + 1, s.length());
			
			int subWays = 0;
			if (c == '^') { // required: one true and one false
				if (result) {
					int leftTrue = countEval(left, true, memo);
					int leftFalse = countEval(left, false, memo);
					int rightTrue = countEval(right, true, memo);
					int rightFalse = countEval(right, false, memo);
					subWays = leftTrue * rightFalse + leftFalse * rightTrue;
				} else {
					int leftTrue = countEval(left, true, memo);
					int leftFalse = countEval(left, false, memo);
					int rightTrue = countEval(right, true, memo);
					int rightFalse = countEval(right, false, memo);
					subWays = leftTrue * rightTrue + leftFalse * rightFalse;				
				}
			} else if (c == '&') { // required: both true
				if (result) {
					int leftTrue = countEval(left, true, memo);
					int rightTrue = countEval(right, true, memo);
					subWays = leftTrue * rightTrue;
				} else {
					int leftTrue = countEval(left, true, memo);
					int leftFalse = countEval(left, false, memo);
					int rightTrue = countEval(right, true, memo);
					int rightFalse = countEval(right, false, memo);
					subWays = leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;				
				}				
			} else if (c == '|') { // required: anything but both false
				if (result) {
					int leftTrue = countEval(left, true, memo);
					int leftFalse = countEval(left, false, memo);
					int rightTrue = countEval(right, true, memo);
					int rightFalse = countEval(right, false, memo);
					subWays = leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue;
				} else {
					int leftFalse = countEval(left, false, memo);
					int rightFalse = countEval(right, false, memo);
					subWays = leftFalse * rightFalse;				
				}
			}
			ways += subWays;
		}
		memo.put(result + s, ways);
		return ways;
	}
	
	public static void main(String[] args) {		
		System.out.println(countEval("0&0&0&1^1|0", true, new HashMap<String, Integer>()));
        System.out.println(count);
        //with memoization 173 recursive calls
        //without memoization 1099 calls
	}

}