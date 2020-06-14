import java.util.*;

public class Ques4_6 {
    public static void main(String[] args) {
        final Integer nodeCount = 30;
        Integer arr[] = new Integer[nodeCount];
        for (Integer i = 0; i < nodeCount; ++i) {
            arr[i] = new java.util.Random().nextInt(999);
        }
        Arrays.sort(arr);
        /*BT<Integer> bt = new BT<Integer>(arr);
        for (Integer i = 0; i < nodeCount; ++i) {
            System.out.println(arr[i] + " : " + bt.inOrderSuccessor(arr[i]));
        }*/
        BTWithParentPointers<Integer> btWithPointers = new BTWithParentPointers<Integer>(arr);
        for (Integer i = 0; i < nodeCount; ++i) {
            System.out.println(arr[i] + " : " + btWithPointers.inOrderSuccessor(arr[i]));
        }
    }

    static class BTWithParentPointers<T extends Comparable<T>> {
        private class TreeNode {
            T data = null;
            TreeNode left = null, right = null, parent = null;

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
        TreeNode root = null;
        public BTWithParentPointers(T[] arr) {
            root = createTree(arr, 0, arr.length);
            setParents(root, null);
            inOrder();
            System.out.println("\n");
            preOrder(root);
            System.out.println("\n");
            print2D();
        }

        private void preOrder(BTWithParentPointers<T>.TreeNode root2) {
            if (root2!=null) {
                System.out.print(root2.data + " -> ");
                preOrder(root2.left);
                preOrder(root2.right);
            }
        }

        private void setParents(TreeNode node, TreeNode parent) {
            var s = new Stack<TreeNode>();
            root.parent = null;
            s.push(root);
            while(!s.isEmpty()) {
                //push all lefts 
                if (s.peek().left!=null)
                    s.peek().left.parent = s.peek();
                var t = s.peek().left;
                while (t != null) {
                    if (t.left!=null)
                        t.left.parent = t;
                    s.push(t);
                    t=t.left;
                }
                while(!s.isEmpty()) {
                    t=s.pop();
                    //if this was a root node, go to right child
                    if(t.right != null){
                        t.right.parent = t;
                        s.push(t.right);
                        break;
                    }
                }
            }
        }

        public void inOrder() {
            inOrder(root);
        }
        private void inOrder(TreeNode root) {
            if (root != null) {
                inOrder(root.left);
                System.out.print(root.data + " : " + root.parent + " || ");
                inOrder(root.right);
            }
        }
        public T inOrderSuccessor(T val) {
            TreeNode node = findNode(val);
            if (node!=null){
                if (node.right != null) {
                    //return leftmost child
                    TreeNode n = node.right;
                    while (n.left!=null){
                        n=n.left;
                    }
                    if(n!=null)
                        return n.data;
                }
                else {
                    TreeNode q = node;
                    TreeNode x = q.parent;
                    //go up 
                    while (x!=null && x.left!=q) {
                        q=x;
                        x=x.parent;
                    }
                    if(x!=null)
                        return x.data;
                }
            }
            return null;
        }

        private TreeNode createTree(T[] arr, int low, int high) {
            TreeNode node = null;
            if (low < high) {
                int mid = (low + high)/2;
                node = new TreeNode(arr[mid], createTree(arr, low, mid), createTree(arr, mid+1, high));
            }
            return node;
        }
        public TreeNode findNode(T value) {
            return findNode(root, value);
        }

        private TreeNode findNode(TreeNode node, T value) {
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

    static class BT<T extends Comparable<T>> {
        private class TreeNode {
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
            inOrderIterative();
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

        public void inOrderIterative() {
            System.out.println();
            var s = new Stack<TreeNode>();
            s.push(root);
            while(!s.isEmpty()) {
                //push all lefts 
                var t = s.peek().left;
                while (t != null) {
                    s.push(t);
                    t=t.left;
                }
                while(!s.isEmpty()) {
                    t=s.pop();
                    System.out.print(t + " ");
                    //if this was a root node, go to right child
                    if(t.right != null){
                        s.push(t.right);
                        break;
                    }
                }
            }
            System.out.println();
        }
        public T inOrderSuccessor(T val) {
            TreeNode node = findNode(val);
            boolean nodeFound = false;
            var s = new Stack<TreeNode>();
            s.push(root);
            while(!s.isEmpty()) {
                //push all lefts 
                var t = s.peek().left;
                while (t != null) {
                    s.push(t);
                    t=t.left;
                }
                while(!s.isEmpty()) {
                    t=s.pop();
                    if (nodeFound)
                        return t.data;
                    if (t==node)
                        nodeFound = true;
                    //System.out.print(t + " ");
                    //if this was a root node, go to right child
                    if(t.right != null){
                        s.push(t.right);
                        break;
                    }
                }
            }
            System.out.println();
            return null;
        }
		       

        public TreeNode findNode(T value) {
            return findNode(root, value);
        }

        private TreeNode findNode(TreeNode node, T value) {
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

        private TreeNode createTree(T[] arr, int low, int high) {
            TreeNode node = null;
            if (low < high) {
                int mid = (low + high)/2;
                node = new TreeNode(arr[mid], createTree(arr, low, mid), createTree(arr, mid+1, high)); 
            }
            return node;
        }
    }
}