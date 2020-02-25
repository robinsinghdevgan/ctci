public class Ques1_6{
    public static void main(String[] args) {
        System.out.println(
            stringCompression("aabcccccaaa")+"\n"+stringCompression("abcdefghi")
        );
    }

    static String stringCompression(String a) {
        StringBuffer sb = new StringBuffer();
        Character currentlySeen = a.charAt(0);
        Integer currentlySeenCount = 0;
        for (int i = 1, size = a.length(); i < size; ++i) {
            if (a.charAt(i) != currentlySeen) {
                sb.append(currentlySeen);
                sb.append(currentlySeenCount);
                currentlySeenCount = 1;
                currentlySeen = a.charAt(i);
            }
            else {
                ++currentlySeenCount;
            }
        }
        sb.append(currentlySeen);
        sb.append(currentlySeenCount);
        System.out.println(sb.toString());
        return a.length() > sb.length() ? sb.toString() : a;
    }
}