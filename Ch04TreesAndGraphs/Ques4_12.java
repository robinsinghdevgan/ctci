
/*
Input : k = 5  
        Root of below binary tree:
           1
        /     \
      3        -1
    /   \     /   \
   2     1   4     5                        
        /   / \     \                    
       1   1   2     6  
*/
// Java program to print all paths with sum k. 
import java.util.*;

public class Ques4_12 {
    static ArrayList<Integer> path = new ArrayList<Integer>();

    // This function prints all paths that have sum k
    static void countOfPathWithSumKUtil(Node root, int k, int[] totalPaths) {
        // empty node
        if (root == null)
            return;

        // add current node to the path
        path.add(root.data);

        // check if there's any k sum path
        // in the left sub-tree.
        countOfPathWithSumKUtil(root.left, k, totalPaths);

        // check if there's any k sum path
        // in the right sub-tree.
        countOfPathWithSumKUtil(root.right, k, totalPaths);

        // check if there's any k sum path that
        // terminates at this node
        // Traverse the entire path as
        // there can be negative elements too
        int f = 0;
        for (int j = path.size() - 1; j >= 0; j--) {
            f += path.get(j);

            // If path sum is k, print the path
            if (f == k) {
                ++totalPaths[0];
                printVector(path, j);
            }               
        }

        // Remove the current element from the path
        path.remove(path.size() - 1);
    }

    // A wrapper over countOfPathWithSumKUtil()
    static int countOfPathWithSumK(Node root, int k) {
        path = new ArrayList<Integer>();
        int[] totalPaths = new int[1];
        countOfPathWithSumKUtil(root, k, totalPaths);
        return totalPaths[0];
    }

    // utility function to print contents of
    // a ArrayList from index i to it's end
    static void printVector(ArrayList<Integer> v, int i) {
        for (int j = i; j < v.size(); j++)
            System.out.print(v.get(j) + " ");
        System.out.println();
    }

    // binary tree node
    static class Node {
        int data;
        Node left, right;

        Node(int x) {
            data = x;
            left = right = null;
        }
    };

    // Driver code
    public static void main(String args[]) {
        Node root = new Node(1);
        root.left = new Node(3);
        root.left.left = new Node(2);
        root.left.right = new Node(1);
        root.left.right.left = new Node(1);
        root.right = new Node(-1);
        root.right.left = new Node(4);
        root.right.left.left = new Node(1);
        root.right.left.right = new Node(2);
        root.right.right = new Node(5);
        root.right.right.right = new Node(2);

        int k = 5;
        System.out.println(countOfPathWithSumK(root, k));
    }
}