import java.time.LocalDateTime;
import java.util.LinkedList;

public class Ques3_6 {
    public static void main(String[] args) {
        AnimalShelter a = new AnimalShelter();
        for (int i = 0; i < 9; i++) {
            a.enqueue(new Dog());
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }
        for (int i = 0; i < 6; i++) {
            a.enqueue(new Cat());
        }
        System.out.println(a.dequeueAny());
        //System.out.println(a.dequeueCat());
        //System.out.println(a.dequeueDog());

    }

    static class AnimalShelter {
        LinkedList<Animal> dog;
        LinkedList<Animal> cat;

        AnimalShelter() {
            dog = new LinkedList<Animal>();
            cat = new LinkedList<Animal>();
        }
        public void enqueue(Animal a) {
            if (a.kind == "dog") {
                dog.add(a);
            }
            else {
                cat.add(a);
            }
        }

        public Animal dequeueAny() {
            System.out.println(dog.getLast().admissionDate);
            System.out.println(cat.getLast().admissionDate);
            if (dog.getLast().admissionDate.isBefore(cat.getLast().admissionDate)) {
                return dog.removeLast();
            }
            else {
                return cat.removeLast();
            }
        }

        public Animal dequeueDog() {
            return dog.removeLast();
        }

        public Animal dequeueCat() {
            return cat.removeLast();
        }

    }

    static class Animal {
        String kind;
        LocalDateTime admissionDate;
        @Override
        public String toString() {
            return kind;
        }
    }

    static class Dog extends Animal{
        public Dog() {kind = "dog"; admissionDate = LocalDateTime.now();}
    }
    static class Cat extends Animal{
        public Cat() {kind = "cat"; admissionDate = LocalDateTime.now();}
    }

}