/*7.2 Caller Center: Imagine you have a call center with three levels of employees: respondent, manager, 
and director. An incoming telephone call must be first allocated to a respondent who is free. If the 
respondent can't handle the call, he or she must escalate the call to a manager. If the manager is not 
free or not able to handle it, then the call should be escalated to a director. Design the classes and 
data structures for this problem. Implement a method dispatchCall() which assigns a call to 
the first available employee.*/

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class CallCenter implements Runnable {
    /*
     * class AnswerCall extends Thread {
     * 
     * }
     */
    // employees
    final static int respondents = 5;
    final static int managers = 1;
    final static int directors = 1;
    static int totalEmployees = respondents + managers + directors;

    static PriorityQueue<Employee> employees = new PriorityQueue<Employee>(totalEmployees, new EmployeeComparator());
    private final BlockingQueue<Integer> queue;

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            // System.out.println("This is call center run method running on thread : " +
            // Thread.currentThread().getId());
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Integer take = queue.take();
                        process(take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }

            });
            t.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }/*
        try {
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }*/

    }

    private void process(Integer take) throws InterruptedException {
        if (employees.isEmpty()) {
            while (employees.isEmpty()) {
                Thread.sleep(5000);
            }
        }
        else {
            Employee dispatcher = employees.poll();
            System.out.println("[Consumer] [Call Center recieves call] , Dispatcher: " + dispatcher.name + " pickups call: " + take);
            Thread.sleep(new Random().nextInt((20000 - 3000) + 1) + 3000);
            System.out.println("Dispatcher: " + dispatcher.name + " releasing call: " + take);
            employees.add(dispatcher);
            System.out.println("Killing Thread : " + Thread.currentThread().getId());
            Thread.currentThread().interrupt();
        }
        
    }

    public CallCenter(BlockingQueue<Integer> queue) {
        int i=0;
        for(i=0;i<respondents;++i) {
            String name = "r"+i;
            employees.add(new Respondent(name));
        }
        for(i=0;i<managers;++i) {
            String name = "m"+i;
            employees.add(new Manager(name));
        }
        for(i=0;i<directors;++i) {
            String name = "d"+i;
            employees.add(new Director(name));
        }
        this.queue = queue;
    }
}



class Caller implements Runnable {

    private final BlockingQueue<Integer> queue;

    @Override
    public void run() {

        try {
            process();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private void process() throws InterruptedException {

        // Put 20 ints into Queue
        for (int i = 0; i < 30; i++) {
            System.out.println("[Producer] [Caller] : " + i);
            queue.put(i);
            //System.out.println("[Producer] [Caller] Queue remainingCapacity : " + queue.remainingCapacity());
            Thread.sleep(1000);
        }

    }

    public Caller(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
}

public class Ques7_2 {
    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        Thread A = new Thread(new CallCenter(queue));
        A.start();
        Thread B = new Thread(new Caller(queue));
        B.start();
        Thread.sleep(50000);
        A.interrupt();
        B.interrupt();
        //Thread.sleep(1000000)
    }
}

class EmployeeComparator implements Comparator<Employee> {
    
    public int compare(Employee e1, Employee e2) {
        if (e1.position.ordinal() < e2.position.ordinal())
            return -1;
        else if (e1.position.ordinal() > e2.position.ordinal())
            return 1;
        else
            return 0;
    }
}

class Employee{
    String name;
    Position position;
    Caller call;
    
    public void endCall(){}

    enum Position {
        RESPONDENT,
        MANAGER,
        DIRECTOR
    }

}

class Respondent extends Employee {
    Respondent(String name) { this.name = name; position = Position.RESPONDENT;}
}

class Manager extends Employee {
    Manager(String name) { this.name = name; position = Position.MANAGER;}
}

class Director extends Employee {
    Director(String name) { this.name = name; position = Position.DIRECTOR;}
}