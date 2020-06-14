import java.util.*;

public class Ques4_5 {
    public static void main(String[] args) {
        final Integer nodeCount = 10;
        Integer arr[] = new Integer[nodeCount];
        for (Integer i = 0; i < nodeCount; ++i) {
            arr[i] = new java.util.Random().nextInt(999);
        }

        BT<Integer> bt1 = new BT<Integer>(arr);
        System.out.println(bt1.isBST());
        Arrays.sort(arr);
        BT<Integer> bt2 = new BT<Integer>(arr);
        System.out.println(bt2.isBST());

    }

    static class BT<T extends Comparable<T>> {
        class TreeNode {
            T data = null;
            TreeNode left = null, right = null;

            @Override
            public String toString() {
                return data.toString();
            }

            TreeNode(T value, TreeNode lChild, TreeNode rChild) {
                data = value;
                left = lChild;
                right = rChild;
            }
        }
        TreeNode root = null;
        public BT(T[] arr) {
            root = createTree(arr, 0, arr.length);
            inOrder();
        }

        public void inOrder() {
            inOrder(root);
        }
        private void inOrder(TreeNode root) {
            if (root != null) {
                inOrder(root.left);
                System.out.print(root.data + " ");
                inOrder(root.right);
            }
        }
        

        private TreeNode createTree(T[] arr, int low, int high) {
            TreeNode node = null;
            if (low < high) {
                int mid = (low + high)/2;
                node = new TreeNode(arr[mid], createTree(arr, low, mid), createTree(arr, mid+1, high)); 
            }
            return node;
        }

        public Boolean isBST() {
            return isBSTHelper(root);
        }

        private Boolean isBSTHelper(TreeNode node) {
            if (node == null)
                return true;
            if (node.left == null && node.right == null)
                return true;
            T data = node.data;
            Boolean leftProperty = true;
            Boolean rightProperty = true;
            if (node.left != null) {
                //System.out.println("\n" + data + " | L" + node.left.data + " = " + data.compareTo(node.left.data));
                leftProperty = data.compareTo(node.left.data) == 1;
            }
            if (node.right != null) {
                //System.out.println("\n" + data + " | R" + node.right.data + " = " + data.compareTo(node.right.data));
                rightProperty = data.compareTo(node.right.data) < 1;
            }
            if (leftProperty && rightProperty)
                return true && isBSTHelper(node.left) && isBSTHelper(node.right);
            return false;
        }
    }
}