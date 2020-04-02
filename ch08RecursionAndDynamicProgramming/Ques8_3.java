public class Ques8_3 {
    public static void main(String[] args) {
        int[] arr1 = {-40,-20,-1,1,2,3,5,7,9,12,13};
        int[] arr2 = {-10,-5 , 2,2,2,3,4,6,9,12,13};

        System.out.println(magicIndex(arr1));
        System.out.println(magicIndex(arr2));
    }

    private static int magicIndex(final int[] arr) {
        return magicIndexHelper(arr, 0, arr.length);
    }

    private static int magicIndexHelper(final int[] arr, int low, int high) {
        if (low <= high){
            int mid = (low + high)/2;
            int middleValue = arr[mid];
            if (middleValue == mid) {
                return mid;
            }
            int leftHighBoundaryIndex = Math.min(mid-1, middleValue);
            int left = magicIndexHelper(arr, low, leftHighBoundaryIndex);
            if (left >= 0)
                return left;
            int rightHighBoundaryIndex = Math.max(mid+1, middleValue);
            int right = magicIndexHelper(arr, rightHighBoundaryIndex, high);
            return right;
        }
        return -1;
    }
}