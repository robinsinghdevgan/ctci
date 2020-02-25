public class Ques8_3 {
    static Integer magicIndex(final int[] A, final int start, final int end) {
        if (start > end)
            return null;

        final int midIndex = (start + end) / 2;
        final int midValue = A[midIndex];
        if (midIndex == midValue)
            return midIndex;

        /*search left*/
        int leftIndex = Math.min(midIndex - 1, midValue);
        Integer left = magicIndex(A, start, leftIndex);
        if(left != null)
            return left;
        
        /*search right*/
        int rightIndex = Math.max(midIndex + 1, midValue);
        Integer right = magicIndex(A, rightIndex, end);
        
        return right;
    }
	
	public static void main(String[] args) {
        int[] A = {-10,-5,2,2,2,3,4,7,9,12,13};
        System.out.println(magicIndex(A, 0, A.length));
	}

}