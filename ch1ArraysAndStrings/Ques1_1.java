public class Ques1_1{

    public static boolean isUnique(final String s) {
        // define an ASCII boolean array of 128 bits
        final boolean[] countChar = new boolean[128];
        for (int i = 0, size = s.length(); i < size; ++i) {
            if (countChar[s.charAt(i)])
                return false;
            else
                countChar[s.charAt(i)] = true;
        }
        return true;
    }

    public static void main(final String[] args) {
        System.out.println(isUnique("11234567890abcdefghijklmnopqrstuvwxyz"));
    }
}