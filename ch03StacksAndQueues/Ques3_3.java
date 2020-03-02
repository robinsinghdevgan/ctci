import java.util.*;

 // Compiler version JDK 11.0.2
 
 class MyStack {
   Stack<Stack<Integer>> s = new Stack<Stack<Integer>>();
   int capacity;
   
   public MyStack(int capacity){
     this.capacity=capacity;
     s.add(new Stack<Integer>());
   }
   
   public void push(Integer data) {
     if(s.peek().size() >= capacity){
       s.add(new Stack<Integer>());
       
     }
     s.peek().add(data);
   }
   
   public Integer pop(){
     Integer res = s.peek().pop();
     if(s.peek().isEmpty()){
       s.pop();
     }
     return res;
   }

   public Integer peek() {
       Integer res = s.peek().peek();
        return res;
   }

   void printMyStack() {
       if (s.isEmpty())  
            return;  
        
        Stack<Integer> x = s.peek();  
        s.pop();  
    
        // Recursively call the function PrintStack  
        printMyStack();  
    
        // Print the stack element starting  
        // from the bottom  
        System.out.print(x + " ");  
    
        // Push the same element onto the stack  
        // to preserve the order  
        s.push(x);  
   }
 }

 public class Ques3_3
 {
   public static void main(String args[])
   { 
     MyStack s = new MyStack(10);
     for ( int i = 0; i<42 ; ++i){
       s.push(new Random().nextInt(999));
     }
     s.printMyStack();
     System.out.println("\n\nPeek top element: " + s.peek());
     for ( int i = 0; i<10 ; ++i){
       s.pop();
     }
     System.out.println("\n\nAfter pop operation:\n\n");
     s.printMyStack();
   }
 }
