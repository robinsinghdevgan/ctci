import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Integer nodeCount = 150;
        Integer arr[] = new Integer[nodeCount];
        for (Integer i = 0; i < nodeCount; ++i) {
            arr[i] = new java.util.Random().nextInt(999);
        }
        Arrays.sort(arr);
        BT bt = new BT(arr);
        System.out.println(bt.getIthNode(5));
        System.out.println(bt.getRandomNode());

    }
    
    static class BT {
        
        //Variables
        TreeNode root = null;
        Integer size = 0;
        
        //Inner Class TreeNode
        class TreeNode {
            Integer data = null;
            TreeNode left = null, right = null;

            @Override
            public String toString() {
                return data.toString();
            }

            TreeNode(Integer value, TreeNode lChild, TreeNode rChild) {
                data = value;
                left = lChild;
                right = rChild;
            }

            TreeNode(Integer value) {
                data = value;
                left = null;
                right = null;
            }

        }

        //Constructor
        public BT(Integer[] arr) {
            root = createTree(arr, 0, arr.length);
            size = arr.length;
            inOrder();
            System.out.println();
        }
        private TreeNode createTree(Integer[] arr, int low, int high) {
            TreeNode node = null;
            if (low < high) {
                int mid = (low + high)/2;
                node = new TreeNode(arr[mid], createTree(arr, low, mid), createTree(arr, mid+1, high)); 
            }
            return node;
        }

        //Methods
        
        //Ques 4.11
        public TreeNode getRandomNode() {
            Random r = new Random();
            int i = r.nextInt(size);
            return GetIthNode.getIthNode(root, i);
        }

        public TreeNode getIthNode(int i) {
            return GetIthNode.getIthNode(root, i);
        }

        static class GetIthNode {
            static TreeNode result = null;
            static int count = 0;

            public static TreeNode getIthNode(TreeNode root, int i) {
                getIthNodeHelper(root, i);
                return result;
            }

            private static void getIthNodeHelper(TreeNode node, final int i) {  
                if (node == null)  
                    return;  
    
                if (count <= i) {
                    
                    //System.out.print("c: " + count + " result: " + result + "\t\t");
                    /* first recur on left child */
                    getIthNodeHelper(node.left, i);  
                    ++count;
                    // when count = i then print element  
                    if (count == i) {
                        result = node;
                    }  
    
                    /* now recur on right child */
                    getIthNodeHelper(node.right, i);  
                }
            }

            
        }
        

        // Insertion
        //insert inOrder
        public void insertInOrder(final Integer d) {
            insertInOrder(root, d);
            ++size;
        }
        private void insertInOrder(TreeNode node, final Integer d) {
            if (d < node.data) {
                if (node.left == null){
                    node.left = new TreeNode(d);
                }
                else {
                    insertInOrder(node.left, d);
                }
            }
            else {
                if (node.right == null){
                    node.right = new TreeNode(d);
                }
                else {
                    insertInOrder(node.right, d);
                }
            }
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

        public TreeNode findNode(Integer value) {
            return findNode(root, value);
        }

        private TreeNode findNode(TreeNode node, Integer value) {
            if (node != null) {
                if (node.data == value) {
                    return node;
                }
                TreeNode left = findNode(node.left, value);
                TreeNode right = findNode(node.right, value);
                if (left != null)
                    return left;
                else
                    return right;
            }
            return null;
        }

        
    }
}

