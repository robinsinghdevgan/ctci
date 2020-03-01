import java.util.EmptyStackException;
import java.util.PriorityQueue;

class MyStack<T> {
    private static class StackNode<T> {
        private T data;
        private StackNode<T> next;

        public StackNode(T data) {
            this.data = data;
        }
    }

    private StackNode<T> top;
    private Integer size;
    private Integer usedSize;
    private PriorityQueue<T> minQueue;

    public MyStack(Integer size) {
        if (size < 1) {
            System.out.println("Stack size cannot be negative.");
            throw new IllegalArgumentException();
        }
        this.size = size;
        usedSize = 0;
        minQueue = new PriorityQueue<T>(size);
    }

    public T pop() {
        if (top == null)
            throw new EmptyStackException();
        T item = top.data;
        top = top.next;
        --usedSize;
        minQueue.remove(item);
        return item;
    }

    public void push(T item) {
        if (isFull())
            throw new StackOverflowError();
        StackNode<T> t = new StackNode<T>(item);
        t.next = top;
        top = t;
        ++usedSize;
        minQueue.add(item);
    }

    public T peek() {
        if (top == null)
            throw new EmptyStackException();
        return top.data;
    }

    public T peekMin() {
        if (top == null)
            throw new EmptyStackException();
        return minQueue.peek();
    }

    public boolean isEmpty() {
        return top == null;
    }
    
    public boolean isFull() {
        return usedSize == size;
    }
}

public class Ques3_2_O_log_n {
    public static void main(String[] args) {
        MyStack<Integer> ms = new MyStack<Integer>(10);
        for(int i=0; i<12; ++i)
            ms.push(i);
        for(int i=0; i<10; ++i) {
            System.out.print("Min: " + ms.peekMin());
            System.out.print("  Popped: " + ms.pop() + "\n");
        }
    }
}