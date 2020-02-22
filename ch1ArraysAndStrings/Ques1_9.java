public class Ques1_9{
    public static void main(String[] args) {
        String[][] pairs = {
            {"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"},
            {"abcdefghi",rotateStringClockwise("abcdefghi", 2)},
        };
        for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean is_rotation = isStringRotation(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + is_rotation);
		}
    }

    static boolean isStringRotation(String s1, String s2) {
        int len = s1.length();
	    /* check that s1 and s2 are equal length and not empty */
	    if (len == s2.length() && len > 0) { 
	    	/* concatenate s1 and s1 within new buffer */
	    	String s1s1 = s1 + s1;
	    	return isSubstring(s1s1, s2);
	    }
        return false;
    }

    static boolean isSubstring(String big, String small) {
        if (big.indexOf(small) >= 0)
            return true;
        return false;
    }

    static String rotateStringClockwise(String s, int rotations) {
        while (rotations-- > 0){
            System.out.println(rotations + "    " + s);
            StringBuilder sb = new StringBuilder();
            System.out.println(s.charAt(s.length()-1) + "   " + s.substring(0,s.length()-1));
            sb.append(s.charAt(s.length()-1));
            sb.append(s.substring(0,s.length()-1));
            s = sb.toString();
        }
        return s;
    }
}