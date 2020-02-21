public class Ques1_5 {
    public static void main(String[] args) {
        String a = "pale";
        String b = "bale";
        System.out.println(oneAway(a, b)); 
    }

    static boolean oneAway(String a, String b) {
        if (a == "" || b == "")
            return false;
        if (a == b)
            return true;
        if (Math.abs(a.length() - b.length()) <= 1) {
            System.out.println("here");
            if (a.length() == b.length()){
                if(oneReplacementAway(a,b))
                    return true;
            }
            else if(oneInsertOrRemovalAway(a,b))
                return true;
        }
        System.out.println("h3");
        return false;
    }

    private static boolean oneInsertOrRemovalAway(String a, String b) {
        System.out.println("oneInsertOrRemovalAway");
        String smaller, larger;
        if (a.length() > b.length()){
            larger = a;
            smaller = b;
        }
        else{
            smaller = a;
            larger = b;
        }

        boolean oneEdit = false;
        for (int i = 0, j = 0, smallerStringLength = smaller.length();
                i < smallerStringLength && j < smallerStringLength;
                    ) {
            if (smaller.charAt(i) == larger.charAt(j)){
                ++i;++j;
            }
            else {
                if (oneEdit)
                    return false;
                oneEdit = true;
                ++j;
            }            
        }
        return true;
    }

    private static boolean oneReplacementAway(String a, String b) {
        //"if atleast length - 1 chars are same in both the strings"
        System.out.println("oneReplacementAway");
        
        boolean oneEdit = false;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                if (oneEdit)
                    return false;
                else
                    oneEdit = true;
            }
        }
        return true;
    }
}