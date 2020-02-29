import java.util.HashSet;
import java.util.Random;

class MyLinkedList  {

    class Node  {
        private Integer data;
        private Node next;
        protected Node (Integer data) {
            this.data = data;
            next = null;
        }
        protected Integer getData() {return data;}
        protected Node getNext() {return next;}
        protected void setNext(Node node) {this.next = node;}
        protected void setData(Integer data) {this.data = data;}
    }

    private Node start, end;
    private Integer count = 0;

    MyLinkedList() {start = end = null;}

    public Integer size() {
        return count;
    }

    private void incrementSize() {++count;}
    private void decrementSize() {--count;}

    public Node getStart() {return start;}

    void pushBack(Integer data) {
        Node node = new Node(data);
        if (start == null && end == null) {
            start = end = node;
        }
        else {
            end.setNext(node);
            end = node;
        }
        incrementSize();
    }

    void pushBack(Node n) {
        if (start == null && end == null) {
            start = end = n;
        }
        else {
            end.setNext(n);
            end = n;
        }
        incrementSize();
    }

    void pushFront(Integer data) {
        Node node = new Node(data);
        if (start == null && end == null) {
            start = end = node;
        }
        else {
            node.setNext(start);
            start = node;
        }
        incrementSize();
    }

    void pushMiddle (Integer data) {
        Node node = new Node(data);
        if (start == null && end == null) {
            start = end = node;
        }
        else {
            Node temp = start;
            Integer mid = size()/2;
            Integer i = 0;
            while (i != mid && temp != null) {
                temp = temp.getNext();
                ++i;
            }
            if (temp.getNext() != null)
                node.setNext(temp.getNext());
            temp.setNext(node);
        }
        incrementSize();
    }

    void pushAtIndex (Integer data, Integer index) {
        if (index < 0)
            return;
        Node node = new Node(data);
        if (start == null && end == null) {
            start = end = node;
        }
        else {
            Node temp = start;
            Integer i = 0;
            while (i != index && temp != null) {
                temp = temp.getNext();
                ++i;
            }
            if (temp.getNext() != null)
                node.setNext(temp.getNext());
            temp.setNext(node);
        }
        incrementSize();
    }

    void popBack() {
        if (start == null) {
            return;
        }
        Node n = start;
        while (n.getNext() != end){
            n = n.getNext();
        }
        n.setNext(null);
        end = null;
        end = n;
        decrementSize();
    }

    void popFront() {
        if (start != null) {
            if (start.getNext() == null)
                return;
            Node temp = start;
            start = null;
            start = temp.getNext();
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
            Node temp = start;
            Integer mid = size()/2;
            Integer i = 0;
            while (i < mid && temp != null) {
                temp = temp.getNext();
                ++i;
            }
            if (temp.getNext().getNext() != null)
                temp.setNext(temp.getNext().getNext());
        }
        decrementSize();
    }

    Node findNode(Integer key) {
        Node n = start;
        while (n != end) {
            if (n.getData() == key) {
                return n;
            }
            n = n.getNext();
        }
        return null;
    }

    @Override
    public String toString() {
        if (loopDetection())
            return "This list has a cycle. It will not be printed.";
        StringBuilder sb = new StringBuilder();
        Node temp = start;
        while (temp.getNext() != null) {
            sb.append(temp.getData());
            sb.append(" -> ");
            temp = temp.getNext();
        }
        sb.append(temp.getData());
        return sb.toString();
    }

    /*2.1 Remove Dups! Write code to remove duplicates from an unsorted linked list.*/
    public void removeDups() {
        HashSet<Integer> hs = new HashSet<Integer>();
        Node behind = null;
        Node ahead = start;

        while (ahead != null) {
            Node temp = ahead.getNext();
            if (hs.contains(ahead.getData())) {
                //remove this node (ahead)
                if (behind != null) {
                    behind.setNext(ahead.getNext());
                    //System.out.println("deletedAhead" + behind.getData() + " " + ahead.getNext().getData()); 
                    if(ahead == end) {
                        end = behind;
                    }
                    ahead.setData(null);
                    ahead.setNext(null);
                }
                else if (ahead == start) {
                    start.setNext(start.getNext());
                }
            }
            else {
                hs.add(ahead.getData());
                behind = ahead;
            }
            ahead = temp;
        }
        /*java.util.Iterator i = hs.iterator(); 
        while (i.hasNext()) 
            System.out.println(i.next());*/
    }
    
    /*2.2 Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.*/
	public Node kthToLastNode(Integer k) {
		return getNodeAtIndex(size() - k + 1);
	}
	
	public Node getNodeAtIndex(Integer index) {
		Node temp = start;
		Integer i = 0;
		while (i != index && temp != null) {
			temp = temp.getNext();
			++i;
		}
		return temp;
	}

    /* 2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
        the first and last node, not necessarily the exact middle) of a singly linked list, given only access 
        to that node.*/
    public void deleteGivenNodeInMiddle(Node n) {
        Node temp = start;
        while ((temp.getNext() != n) && (temp.getNext() != null)) {
            temp = temp.getNext();
        }
        if (temp.getNext() != null) {
            if (temp.getNext().getNext() != null)
                temp.setNext(temp.getNext().getNext());
        }
        else {
            temp.setNext(null);
        }
        
    }
	
	/*2.4 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
		before all nodes greater than or equal to x. Ifxis contained within the list, the values of x only need
		to be after the elements less than x (see below). Integerhe partition element x can appear anywhere in the
		"right partition"; it does not need to appear between the left and right partitions.
	*/
	public MyLinkedList partition(Integer key) {
		MyLinkedList leftPartition = new MyLinkedList();
		MyLinkedList rightPartition = new MyLinkedList();
		
		Node temp = start;
        while (temp != null) {
			if (temp.getData() < key) {
				leftPartition.pushBack(temp.data);
			}
			else if (temp.getData() >= key) {
				rightPartition.pushBack(temp.data);
			}
            temp = temp.getNext();
        }
        if (leftPartition.start != null) {
            if (rightPartition.start != null)
		        leftPartition.end.setNext(rightPartition.start);
            return leftPartition;
        }
        else if (rightPartition.start != null) {
            return rightPartition;
        }
        return new MyLinkedList();
	}

    /*2.5 Sum Lists: You have two numbers represented by a linked list, where each node contains a single
		digit. Integerhe digits are stored in reverse order, such that the Vs digit is at the head of the list. Write a
		function that adds the two numbers and returns the sum as a linked list.
		EXAMPLE
		Input: ( 7 - > 1 -> 6) + (5 -> 9 -> 2).Integerhat is,617 + 295.
		Output: 2 -> 1 -> 9. Integerhat is, 912.
		FOLLOW UP
		Suppose the digits are stored in forward order. Repeat the above problem.
		EXAMPLE
		Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).Integerhat is, 617 + 295,
		Output:9 -> 1 -> 2,Integerhatis,912.
	*/
	public MyLinkedList sumKthPlaceOfTwoLinkedListReverseOrder(MyLinkedList l2) {
		Node list1Pointer, list2Pointer;
		list1Pointer = this.getStart();
		list2Pointer = l2.getStart();
		Integer l1Size = this.size();
		Integer l2Size = l2.size();
		MyLinkedList l3 = new MyLinkedList();
        Integer carry = 0;
		while ((list1Pointer != null) && (list2Pointer != null)){
            Integer sum =list1Pointer.getData() + list2Pointer.getData() + carry;
            if (sum > 9) {
                carry = sum/10;
                l3.pushBack(sum%10);
            }
            else {
                l3.pushBack(sum);
                carry = 0;
            }
			list1Pointer = list1Pointer.getNext();
			list2Pointer = list2Pointer.getNext();
        }
		if (l1Size > l2Size) {
			while ((list1Pointer != null)){
                Integer sum =list1Pointer.getData() + carry;
                if (sum > 9) {
                    carry = sum/10;
                    l3.pushBack(sum%10);
                }
                else {
                    l3.pushBack(sum);
                    carry = 0;
                }
				list1Pointer = list1Pointer.getNext();
			}
		}
		else {
			while ((list2Pointer != null)){
				Integer sum =list2Pointer.getData() + carry;
                if (sum > 9) {
                    carry = sum/10;
                    l3.pushBack(sum%10);
                }
                else {
                    l3.pushBack(sum);
                    carry = 0;
                }
				list2Pointer = list2Pointer.getNext();
			}
        }
        if (carry > 0) {
            //add a new node
            l3.pushBack(carry);
        }
		return l3;
    }
    public MyLinkedList sumKthPlaceOfTwoLinkedListForwardOrder(MyLinkedList l2) {
        java.util.Stack<Integer> stack1 = new java.util.Stack<Integer>();
        java.util.Stack<Integer> stack2 = new java.util.Stack<Integer>();
		Node list1Pointer, list2Pointer;
		list1Pointer = this.getStart();
        list2Pointer = l2.getStart();
        while(list1Pointer != null) {stack1.add(list1Pointer.getData()); list1Pointer = list1Pointer.getNext();}
        while(list2Pointer != null) {stack2.add(list2Pointer.getData()); list2Pointer = list2Pointer.getNext();}
		Integer l1Size = this.size();
		Integer l2Size = l2.size();
		MyLinkedList l3 = new MyLinkedList();
        Integer carry = 0;
		while ((!stack1.empty()) && (!stack2.empty())){
            Integer sum = stack1.pop() + stack2.pop() + carry;
            if (sum > 9) {
                carry = sum/10;
                l3.pushBack(sum%10);
            }
            else {
                l3.pushBack(sum);
                carry = 0;
            }
        }
		if (l1Size > l2Size) {
			while (!stack1.empty()){
                Integer sum = stack1.pop() + carry;
                if (sum > 9) {
                    carry = sum/10;
                    l3.pushBack(sum%10);
                }
                else {
                    l3.pushBack(sum);
                    carry = 0;
                }
			}
		}
		else {
			while (!stack2.empty()){
                Integer sum = stack2.pop() + carry;
                if (sum > 9) {
                    carry = sum/10;
                    l3.pushBack(sum%10);
                }
                else {
                    l3.pushBack(sum);
                    carry = 0;
                }
			}
        }
        if (carry > 0) {
            //add a new node
            l3.pushBack(carry);
        }
		return l3;
	}

    /*2.6 Palindrome: Implement a function to check if a linked list is a palindrome.*/
    public Boolean palindrome(){
        java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
        Integer createStackTillThisIndex = size()/2 - 1;
        Node list1Pointer = this.getStart();
        for(int i = 0; (i <= createStackTillThisIndex) && (list1Pointer != null); ++i) {
            stack.add(list1Pointer.getData());
            list1Pointer = list1Pointer.getNext();
        }
        if((size() % 2) != 0) {
            //move ahead of middle element
            list1Pointer = list1Pointer.getNext();
        }
        while (list1Pointer != null) {
            if (!(list1Pointer.getData() == stack.pop())) {
                return false;
            }
            list1Pointer = list1Pointer.getNext();
        }
        return true;
    }

    /*2.7 Intersection; Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting
    node. Note that the intersection is defined based on reference, not value. That is, if the kth
    node of the first linked list is the exact same node (by reference) as the j t h node of the second
    linked list, then they are intersecting.*/
    public boolean intersect(MyLinkedList l2) {
        java.util.HashSet<Integer> hm = new java.util.HashSet<Integer>();
        Node list1Pointer = this.getStart();
        while(list1Pointer != null) {
            hm.add(list1Pointer.getData());
            list1Pointer = list1Pointer.getNext();
        }
        Node list2Pointer = l2.getStart();
        while(list2Pointer != null) {
            if (hm.contains(list2Pointer.getData())) {
                System.out.println("The two linked lists intersect at node: " + list2Pointer.getData() + " " + list2Pointer);
                return true;
            }
            list2Pointer = list2Pointer.getNext();
        }
        return false;
    }


    /*2.8 Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
    beginning of the loop.
    DEFINITION
    Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
    as to make a loop in the linked list.
    EXAMPLE
    Input: A - > 8 - > C - > D - > E - > C [the same C as earlier]
    Output: C*/
	public boolean loopDetection() {
		Node slowPointer = this.getStart();
		Node fastPointer = this.getStart();
		if (fastPointer.getNext() != null) {
			fastPointer = fastPointer.getNext();
		}
        Integer i = 0;
		Integer maxNodeToAccess = 2*size();
        while((i < maxNodeToAccess) && (fastPointer != null)) {
            if (slowPointer == fastPointer)
				return true;
            
            fastPointer = fastPointer.getNext().getNext();
			slowPointer = slowPointer.getNext();
			++i;
        }
		return false;
	}

}

public class Ch2 {
    
    public static void main(String[] args) {
        /*MyLinkedList l = new MyLinkedList();
        for (Integer i = 15; i >= 6; --i) {
            l.pushFront(new Random().nextInt(99));
        }
        for (Integer i = 0; i < 6; ++i) {
            l.pushBack(99);
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
        l.deleteGivenNodeInMiddle(l.findNode(99));
        System.out.println(l);
        //2.1
        l.removeDups();
        System.out.println(l);

        MyLinkedList l1 = new MyLinkedList();
        l1.pushBack(9); l1.pushBack(9); l1.pushBack(9); 
        System.out.println(l1);

        MyLinkedList l2 = new MyLinkedList();
        l2.pushBack(1); l2.pushBack(9); l2.pushBack(1); l2.pushBack(8);
        System.out.println(l2);
        
        //2.2
        MyLinkedList l3 = l1.sumKthPlaceOfTwoLinkedListReverseOrder(l2);
        System.out.println(l3);

        //2.4
        System.out.println(l.partition(10));

        //2.5
        System.out.println(l1.sumKthPlaceOfTwoLinkedListForwardOrder(l2));
        */

        /*
        //2.6
        MyLinkedList palindromeCheckEven = new MyLinkedList();
        palindromeCheckEven.pushBack(1); palindromeCheckEven.pushBack(2); palindromeCheckEven.pushBack(3);
        palindromeCheckEven.pushBack(3); palindromeCheckEven.pushBack(2); palindromeCheckEven.pushBack(1);
        System.out.println(palindromeCheckEven);
        System.out.println(palindromeCheckEven.palindrome());

        MyLinkedList palindromeCheckOdd = new MyLinkedList();
        palindromeCheckOdd.pushBack(1); palindromeCheckOdd.pushBack(2); palindromeCheckOdd.pushBack(5); palindromeCheckOdd.pushBack(5);
        palindromeCheckOdd.pushBack(3); palindromeCheckOdd.pushBack(2); palindromeCheckOdd.pushBack(1);
        System.out.println(palindromeCheckOdd);
        System.out.println(palindromeCheckOdd.palindrome());
        */
        /*
        //2.7
        MyLinkedList test2_7_1 = new MyLinkedList();
        for (Integer i = 0; i < 15 ; ++i) {
            test2_7_1.pushFront(new Random().nextInt(999));
        }
        System.out.println(test2_7_1);
		
        MyLinkedList test2_7_2 = new MyLinkedList();
        for (Integer i = 0; i < 10 ; ++i) {
            test2_7_2.pushFront(new Random().nextInt(999));
        }
		test2_7_2.pushBack(test2_7_1.kthToLastNode(5));
		
		System.out.println(test2_7_2);
		
		System.out.println(test2_7_1.intersect(test2_7_2));
		*/
		
		//2.8
		//create a linkedList cycle
		MyLinkedList test2_8 = new MyLinkedList();
        for (Integer i = 0; i < 15 ; ++i) {
            test2_8.pushFront(new Random().nextInt(999));
        }
        System.out.println(test2_8);
		
		test2_8.pushBack(test2_8.kthToLastNode(7));
		System.out.println(test2_8.loopDetection());
        System.out.println(test2_8);
        

    }

}