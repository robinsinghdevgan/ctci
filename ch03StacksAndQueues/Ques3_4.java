import java.util.Stack;

public class Ques3_4 {
    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        for (int i = 0; i < 10; ++i) {
            q.add(new java.util.Random().nextInt(999));
        }
        System.out.println(q);
        System.out.println(q.remove());
        System.out.println(q);
    }

    static class MyQueue {
        // 3.4 Queue via Stacks: Implement a MyQueue class which implements a queue
        // using two stacks.
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        public void add(Integer data) {
            s1.push(data);
        }

        public Integer remove() {
            transferToStack2();
            return s2.pop();
        }

        public Integer front() {
            transferToStack2();
            return s2.peek();
        }

        private void transferToStack2() {
            if (s2.isEmpty() && !s1.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
        }

        @Override
        public String toString() {
            transferToStack2();
            return s2.toString();
        }
    }

}