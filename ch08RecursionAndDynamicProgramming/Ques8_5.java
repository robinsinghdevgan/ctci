public class Ques8_5 {
    public static void main(final String[] args) {
        long startTime, endTime, duration;
        startTime = System.nanoTime();

        System.out.println(multiplyWithoutMultiplicationOperator(100, 10));
        System.out.println(multiplyWithoutMultiplicationOperator(5, 4));
        System.out.println(multiplyWithoutMultiplicationOperator(10, 1000));
        System.out.println(multiplyWithoutMultiplicationOperator(0,0));
        System.out.println(multiplyWithoutMultiplicationOperator(0,10000));
        System.out.println(multiplyWithoutMultiplicationOperator(10000,0));

        endTime = System.nanoTime();

        duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("duration: " + duration);
/*
        //-negative numbers test
        System.out.println(multiplyWithoutMultiplicationOperator(-1000, 10));
        System.out.println(multiplyWithoutMultiplicationOperator(1000, -10));
        System.out.println(multiplyWithoutMultiplicationOperator(-1000, -10));
        System.out.println(multiplyWithoutMultiplicationOperator(-0,-10000));
        System.out.println(multiplyWithoutMultiplicationOperator(-10000,0));
*/

        //ctci solution 3
        startTime = System.nanoTime();
        System.out.println(QuestionC.minProduct(100, 10));
        System.out.println(QuestionC.minProduct(5, 4));
        System.out.println(QuestionC.minProduct(10, 1000));
        System.out.println(QuestionC.minProduct(0,0));
        System.out.println(QuestionC.minProduct(0,10000));
        System.out.println(QuestionC.minProduct(10000,0));
        
        endTime = System.nanoTime();

        duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("duration: " + duration);
    }

    private static int multiplyWithoutMultiplicationOperator(int multiplicand, int multiplier) {
        boolean negative = false;

        if((multiplicand < 0)){
            multiplicand = -multiplicand;
            negative = !negative;
        }
        if (multiplier < 0) {
            multiplier = -multiplier;
            negative = !negative;
        }
        int bigger = multiplicand > multiplier ? multiplicand : multiplier;
        int smaller = multiplicand > multiplier ? multiplier : multiplicand;
        int result = multiplyTwoPositiveIntegers(bigger, smaller);
        return negative ? -result : result; 
    }

    private static int multiplyTwoPositiveIntegers(final int multiplicand, final int multiplier) {
        //System.out.println("multiplier: " + multiplier);
        if (multiplier == 0)
            return 0;
        if (multiplier == 1)
            return multiplicand;
        final int n = nearestDegreeOfPowerOf2(multiplier);
        final int leftShiftOperation = (multiplicand << n);
        //System.out.println("    " + multiplicand + " << " + n + " = " + leftShiftOperation);
        final int result = leftShiftOperation + multiplyTwoPositiveIntegers(multiplicand, (multiplier - (1 << n)));
        return result;
    }

    /*
    * finds the nearest possible degree power of 2 which is just less than the provided
    * multiplier
    */
    
    private static int nearestDegreeOfPowerOf2(final int multiplier) {
        int i = 0;
        while ( (1 << i) <= multiplier) {
            ++i;
        }
        return i-1;
    }
}

class QuestionC {

	public static int counter = 0;
	
	public static int minProductHelper(int smaller, int bigger) {
		if (smaller == 0) {
			return 0;
		} else if (smaller == 1) {
			return bigger;
		} 
		
		int s = smaller >> 1;
		int halfProd = minProductHelper(s, bigger);
		
		if (smaller % 2 == 0) {
			counter++;
			return halfProd + halfProd;
		} else {
			counter+=2;
			return halfProd + halfProd + bigger;
		}
	}

	
	public static int minProduct(int a, int b) {
		int bigger = a < b ? b : a;
		int smaller = a < b ? a : b;
		
		return minProductHelper(smaller, bigger);
	}
}