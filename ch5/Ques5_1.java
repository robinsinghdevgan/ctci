public class Ques5_1 {
    static int updateBits(int n, int m, int i, int j){
        int allOnes  = ~0;
        int left = allOnes << (i+j);
        int right = (1<<i)-1;
        int mask = left|right;

        int n_cleared = n&mask;
        int m_shifted = m<<i;
        return n_cleared|m_shifted;
    }

	// Driver Function to test above function
    public static void main(String args[]) {
		System.out.println(updateBits(1<<10,1<<4|1|1<<2, 2, 6));
    }
}