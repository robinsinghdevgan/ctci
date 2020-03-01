import java.util.Stack;

class NodeWithMin {
    Integer data;
    Integer min;
    NodeWithMin next;
    NodeWithMin(Stack<NodeWithMin> s, Integer data) {
        this.data = data;
        min = Math.min(s.empty() ? Integer.MAX_VALUE : s.peek().min, data);
    }
    @Override
    public String toString() {
        return ("Data: " + data + "   Min: " + min + "\n");
    }
}



public class Ques3_2 {
    public static void main(String[] args) {
        Stack<NodeWithMin> s = new Stack<NodeWithMin>();
        for (int i = 0; i < 10; ++i)
            s.add(new NodeWithMin(s, new java.util.Random().nextInt(999)));

        System.out.println(s);
        s.add(new NodeWithMin(s, -99));
        System.out.println(s);
    }
    
}