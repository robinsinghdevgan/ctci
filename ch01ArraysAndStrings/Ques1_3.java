public final class Ques1_3{

    private static int trueLength(String s) {
        char prev = '\0';
        int pos = 0;
        for (int i = 0; s.charAt(i) != '\0'; ++i){
            char c = s.charAt(i);
            if (c == ' '){
                if(c == prev)
                    break;
            }
            prev = c;
            pos = i;   
        }
        return pos;
    }

    public static void main(String[] args) {
        String s = "Mr John Smith      ";
        char[] cArr = s.toCharArray();
        int trueLength = trueLength(s);
        String temp = new String(cArr);
        System.out.println(temp.length());
        System.out.println(trueLength);
        String result = urlIfy(cArr, trueLength);
        System.out.println(result);
    }

    private static String urlIfy(char[] cArr, int trueLength) {
        for (int i = 0; i < trueLength; ++i) {
            if (cArr[i] == ' ') {
                //push two pos right
                for (int j = trueLength - 1; j>i; j--){
                    cArr[j+2] = cArr[j];
                }
                cArr[i] = '%';
                cArr[i+1] = '2';
                cArr[i+2] = '0';
                i += 2;
                trueLength += 2;
            }
        }
        return new String(cArr);
    }
}