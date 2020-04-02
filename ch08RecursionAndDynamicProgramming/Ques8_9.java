import java.util.ArrayList;

public class Ques8_9 {
    public static void main(String[] args) {
        findAllValidParenthesisPermutations(1);
        findAllValidParenthesisPermutations(2);
        findAllValidParenthesisPermutations(3);
        findAllValidParenthesisPermutations(4);
    }

    private static ArrayList<String> findAllValidParenthesisPermutations(int count) {
        ArrayList<String> result = new ArrayList<String>();
        char[] str = new char[count * 2];
        findAllValidParenthesisPermutations(0, count, count, str, result);
        System.out.println(result);
        return result;
    }

	private static void findAllValidParenthesisPermutations(int i, int leftRem, int rightRem, char[] str,
			ArrayList<String> result) {
        //base case (invalid state)
        if (leftRem < 0 || rightRem < leftRem)
            return;
        //base case (valid state)
        if (leftRem == 0 && rightRem == 0) {
            String s = String.copyValueOf(str);
            result.add(s);
            return;
        }
        //main logic

        if (leftRem > 0) {
            str[i] = '(';
            findAllValidParenthesisPermutations(i+1, leftRem-1, rightRem, str, result);
        }

        if (rightRem > leftRem) {
            str[i] = ')';
            findAllValidParenthesisPermutations(i+1, leftRem, rightRem - 1, str, result);
        }
	}
}