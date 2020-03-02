import java.util.EmptyStackException;
import java.util.Stack;

public class Ques3_5 {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        for (int i=0; i<11; ++i) {
            s.push(new java.util.Random().nextInt(999));
        }
        System.out.println(s);
        System.out.println(s.peek());
        sortStack(s);
        System.out.println(s);
        System.out.println(s.peek());
    }

    private static void sortStack(Stack<Integer> a) {
        Stack<Integer> b = new Stack<Integer>();
        while (!a.isEmpty()) {
            Integer temp = a.pop();
            try {
                if (temp < b.peek()) {
                    int countOfBMovedToA = 0;
                    
                        while (temp < b.peek()) {
                            a.push(b.pop());
                            ++countOfBMovedToA;
                        }
                    
                    b.push(temp);
                    while(countOfBMovedToA-- > 0) {
                        b.push(a.pop());
                    }
                }
                else {
                    b.push(temp);
                }
            }catch(EmptyStackException e) {
                //b seems empty, push some data eh!
                b.push(temp);
            }
        }
        while(!b.isEmpty()) {
            a.push(b.pop());
        }
    }
}