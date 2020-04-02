public class Ques8_11 {
    static long countWays(int denominations[], int n) { 
		//Time complexity of this function: O(denominations * n) 
		//Space Complexity of this function: O(n) 

		// table[i] will be storing the number of solutions 
		// for value i. We need n+1 rows as the table is 
		// constructed in bottom up manner using the base 
		// case (n = 0) 
		long[] table = new long[n+1]; 

		// Base case (If given value is 0) 
		table[0] = 1; 

		// Pick all coins one by one and update the table[] 
		// values after the index greater than or equal to 
		// the value of the picked coin 
		for (int i=0; i<denominations.length; i++) 
			for (int j=denominations[i]; j <= n; j++) {
                table[j] += table[j - denominations[i]]; 
            }


		return table[n]; 
	}

    // Driver Function to test above function
    public static void main(String args[]) {
        int arr[] = { 25, 10, 5, 1 };
        int n = 50;
        System.out.println(countWays(arr, n));
    }
}