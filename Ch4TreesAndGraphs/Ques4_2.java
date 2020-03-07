
//4,2 Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimal height.
import java.util.*;

class BST {

    class TreeNode {
        TreeNode left;
        Integer data;
        TreeNode right;

        TreeNode() {
            data = null;
            left = null;
            right = null;
        }
    }

    TreeNode root;

    public BST(int[] arr) {
        Arrays.sort(arr);
        root = createTree(arr, 0, arr.length);
    }

    private TreeNode createTree(final int[] arr, int start, int end) {
        if (start < end) {
            TreeNode root = new TreeNode();
            int mid = (start + end) / 2;
            //System.out.println("s: " + start + " e: " + end + " m:" + mid);
            root.data = arr[mid];
            root.left = createTree(arr, start, mid);
            root.right = createTree(arr, mid+1, end);
            return root;
        }
        return null;
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    final int COUNT = 5;
    // Function to print binary tree in 2D
    // It does reverse inorder traversal
    void print2DUtil(TreeNode root, int space) {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        print2DUtil(root.right, space);

        // Print current TreeNode after space
        // count
        //System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.data + "\n");

        // Process left child
        print2DUtil(root.left, space);
    }

    // Wrapper over print2DUtil()
    void print2D() {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }
}

public class Ques4_2 {
    public static void main(String[] args) {
        final int nodeCount = 10;
        int arr[] = new int[nodeCount];
        for (int i = 0; i < nodeCount; ++i) {
            arr[i] = new java.util.Random().nextInt(999);
        }
        for (int i = 0; i < nodeCount; ++i) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
        BST bst = new BST(arr);
        bst.inOrder();
        bst.preOrder();
        bst.print2D();
    }

    
}