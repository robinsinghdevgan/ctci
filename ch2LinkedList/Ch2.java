class Node <T> {
    T data;
    Node<T> next;
    Node (T data) {
        this.data = data;
        next = null;
    }
    Node() {data = null;}
}

class MyLinkedList <T> {
    private Node<T> start, end;
    private Integer count = 0;

    MyLinkedList() {start = end = null;}

    public Integer size() {
        return count;
    }

    private void incrementSize() {++count;}
    private void decrementSize() {--count;}

    void pushBack(T data) {
        Node<T> node = new Node<T>(data);
        if (start == null && end == null) {
            start = end = node;
        }
        else {
            end.next = node;
            end = node;
        }
        incrementSize();
    }
    void pushFront(T data) {
        Node<T> node = new Node<T>(data);
        if (start == null && end == null) {
            start = end = node;
        }
        else {
            node.next = start;
            start = node;
        }
        incrementSize();
    }

    void pushMiddle (T data) {
        Node<T> node = new Node<T>(data);
        if (start == null && end == null) {
            start = end = node;
        }
        else {
            Node<T> temp = start;
            int mid = size()/2;
            int i = 0;
            while (i != mid && temp != null) {
                temp = temp.next;
                ++i;
            }
            if (temp.next != null)
                node.next = temp.next;
            temp.next = node;
        }
        incrementSize();
    }

    void pushAtIndex (T data, Integer index) {
        if (index < 0)
            return;
        Node<T> node = new Node<T>(data);
        if (start == null && end == null) {
            start = end = node;
        }
        else {
            Node<T> temp = start;
            int i = 0;
            while (i != index && temp != null) {
                temp = temp.next;
                ++i;
            }
            if (temp.next != null)
                node.next = temp.next;
            temp.next = node;
        }
        incrementSize();
    }

    void popBack() {
        if (start == null) {
            return;
        }
        Node<T> n = start;
        while (n.next != end){
            n = n.next;
        }
        n.next = null;
        end = null;
        end = n;
        decrementSize();
    }

    void popFront() {
        if (start != null) {
            if (start.next == null)
                return;
            Node<T> temp = start;
            start = null;
            start = temp.next;
        }
        decrementSize();
    }

    void popMiddle() {
        if (size() < 3) { //first and last should not be deleted
            return;
        }
        if (start == null && end == null) {
            return;
        }
        else {
            Node<T> temp = start;
            int mid = size()/2;
            int i = 0;
            while (i < mid && temp != null) {
                temp = temp.next;
                ++i;
            }
            if (temp.next.next != null)
                temp.next = temp.next.next;
        }
        decrementSize();
    }

    Node<T> findNode(T key) {
        Node<T> n = start;
        while (n != end) {
            if (n.data == key) {
                return n;
            }
            n = n.next;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> temp = start;
        while (temp.next != null) {
            sb.append(temp.data);
            sb.append(" -> ");
            temp = temp.next;
        }
        sb.append(temp.data);
        return sb.toString();
    }

    /*2.1 Remove Dups! Write code to remove duplicates from an unsorted linked list.*/
    public void removeDups() {

    }

    /* 2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
        the first and last node, not necessarily the exact middle) of a singly linked list, given only access 
        to that node.*/
    public void deleteGivenNodeInMiddle(Node<T> n) {
        Node<T> temp = start;
        while (temp.next != n) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }
}

public class Ch2 {
    
 /*   static void popMiddle(MyLinkedList l, Node middle) {
        if (l.size() < 3) { //first and last should not be deleted
            return;
        }
        if (start != null) {
            if (start.next == null)
                return;
            Node temp = start;
            start = null;
            start = temp.next;
        }
        decrementSize();
    }
*/
    public static void main(String[] args) {
        MyLinkedList<Integer> l = new MyLinkedList<Integer>();
        for (int i = 15; i >= 6; --i) {
            l.pushFront(i);
        }
        for (int i = 0; i < 6; ++i) {
            l.pushBack(i);
        }
        System.out.println(l);
        l.popBack();
        System.out.println(l);
        l.popFront();
        System.out.println(l);
        l.pushMiddle(555);
        System.out.println(l);
        l.popMiddle();
        System.out.println(l);
        //2.3
        l.deleteGivenNodeInMiddle(l.findNode(14));
        System.out.println(l);
    }

}