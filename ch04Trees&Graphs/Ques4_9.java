import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class Ques4_9 {
    public static void main(String[] args) {
        BST bst = setupBST();
        ArrayList<LinkedList<Integer>> result = allPossibleArraysThatCanCreateThisBST(bst.root);
        System.out.println("All possible arrays that led to creation of this bst is as follows");
        for(var res : result) {
            System.out.print("[");
            for(var i : res) {
                System.out.print(i + " ");
            }
            System.out.print("]\n");
        }
    }

    private static ArrayList<LinkedList<Integer>> allPossibleArraysThatCanCreateThisBST(BST.TreeNode root) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
        if (root == null) {
            result.add(new LinkedList<Integer>());
            return result;
        }
        //add data to first 'prefix' list
        LinkedList<Integer> prefix = new LinkedList<Integer>();
        prefix.add(root.data);

        //complete this job for left and right subtrees
        ArrayList<LinkedList<Integer>> resultOfLeftSubTree = allPossibleArraysThatCanCreateThisBST(root.left);
        ArrayList<LinkedList<Integer>> resultOfRightSubTree = allPossibleArraysThatCanCreateThisBST(root.right);
        
        //for each list in 'list of list of items' returned from left subtree
            //for each list returned from right subtree
                //find all possible lists of sequenced items that can create the BST
                //and add it to result
        for (LinkedList<Integer> left : resultOfLeftSubTree) {
            for (LinkedList<Integer> right : resultOfRightSubTree) {
                ArrayList<LinkedList<Integer>> possibleNodeSequence = new ArrayList<LinkedList<Integer>>();
                createPossibleNodeSequence(possibleNodeSequence, left, right, prefix);
                result.addAll(possibleNodeSequence);
            }
        }
        return result;
	}

	private static void createPossibleNodeSequence(ArrayList<LinkedList<Integer>> results, LinkedList<Integer> first,
            LinkedList<Integer> second, LinkedList<Integer> prefix) {
        //if first or second being empty, add to result first new list = prefix + first + second
        if(first.isEmpty() || second.isEmpty()) {
            LinkedList<Integer> res = new LinkedList<Integer>(prefix);
            res.addAll(first);
            res.addAll(second);
            results.add(res);
            return;
        }


        //add front of first to end of prefix
        prefix.addLast(first.removeFirst());
        //recurse till base condition
        createPossibleNodeSequence(results, first, second, prefix);
        //add end of prefix to front of second
        first.addFirst(prefix.removeLast());

        //add front of second to end of prefix
        prefix.addLast(second.removeFirst());
        //recurse till base condition
        createPossibleNodeSequence(results, first, second, prefix);
        //add end of prefix to front of second
        second.addFirst(prefix.removeLast());
            
    }

    private static BST setupBST() {
        final int nodeCount = 4;
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
        return bst;
    }

    
}

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
