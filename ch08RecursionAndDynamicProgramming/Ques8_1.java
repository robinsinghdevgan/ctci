
import java.util.*;

class Solution {
    static Integer[] numberOfWaysAtEachSteps;

    private static int getCount(int n) {
        if (n < 0){
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (numberOfWaysAtEachSteps[n] != null) {
            return numberOfWaysAtEachSteps[n];
        }
        numberOfWaysAtEachSteps[n] = getCount(n-1) + getCount(n-2) + getCount(n-3);
        return numberOfWaysAtEachSteps[n];
    }

    public static int countNumOfWaysGirlCanReachAtNthStep(int n) {
        numberOfWaysAtEachSteps = new Integer[n+1];
        Arrays.fill(numberOfWaysAtEachSteps, null);
        return getCount(n);
    }
}
public class Ques8_1 {
    public static void main(String args[]) {
        System.out.println(Solution.countNumOfWaysGirlCanReachAtNthStep(3));
    }
}
