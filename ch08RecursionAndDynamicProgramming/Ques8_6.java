import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Ques8_6 {
    static BufferedWriter out;

    public static void main(String[] args) throws IOException {
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "ASCII"),
                512);
        towerOfHanoi(1, 1, 2, 3);
        out.newLine();
        towerOfHanoi(2, 1, 2, 3);
        out.newLine();
        towerOfHanoi(3, 1, 2, 3);
        /*
        out.write("64 disk solution");
        towerOfHanoi(64, 1, 2, 3);
        */
        out.flush();
    }

    private static void towerOfHanoi(int n, int A, int B, int C) throws IOException {
        if (n>0){
            towerOfHanoi(n-1, A, C, B); // move from A to B using C
            out.write("(" + A + "," + C + ") -> ");
            towerOfHanoi(n-1, B, A, C); // move from B to C using A
        }
    }
}