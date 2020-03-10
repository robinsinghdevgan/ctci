import java.util.*;

public class Ques4_4 {
    public static void main(String[] args) {
        final Integer nodeCount = 10;
        Integer arr[] = new Integer[nodeCount];
        for (Integer i = 0; i < nodeCount; ++i) {
            arr[i] = new java.util.Random().nextInt(999);
        }

        BT<Integer> bt = new BT<Integer>(arr);

        System.out.println(bt.depth());
        System.out.println(bt.depthRecursive());
        System.out.println(bt.isBalanced());
        
    }

    static class BT <T>{
        class TreeNode {
            T data;
            TreeNode left, right;
            @Override
            public String toString() { return data.toString();}
            TreeNode(T value, TreeNode lChild, TreeNode rChild) {data = value; left = lChild; right = rChild;}
        }
        TreeNode root = null;
        public BT(T[] arr) {
            root = createTree(arr, 0, arr.length);
        }

        private TreeNode createTree(T[] arr, int low, int high) {
            TreeNode node = null;
            if (low < high) {
                int mid = (low + high)/2;
                node = new TreeNode(arr[mid], createTree(arr, low, mid), createTree(arr, mid+1, high)); 
            }
            return node;
        }

        public Boolean isBalanced() {
            if (Math.abs(depthRecursiveHelper(root.left) - depthRecursiveHelper(root.right)) > 1)
                return false;
            return true;
        }

        public Integer depthRecursive() {
            return depthRecursiveHelper(root);
        }


        private Integer depthRecursiveHelper(TreeNode node) {
            if (node == null)
                return 0;
            return 1 + Math.max(depthRecursiveHelper(node.left), depthRecursiveHelper(node.right));
        }

        public Integer depth() {
            List<String> res = new LinkedList<String>();
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> mainQ = new LinkedList<TreeNode>();
            Queue<TreeNode> childQ = new LinkedList<TreeNode>();
            mainQ.add(root);
            int level = 0; 
            while (true){
                ++level;
                while(!mainQ.isEmpty()) {
                    TreeNode n = mainQ.poll();
                    if (n.left != null)
                        childQ.add(n.left);
                    if (n.right != null)
                        childQ.add(n.right);
                }
                if (childQ.isEmpty())
                    break;
                mainQ.addAll(childQ);
                childQ.clear();
            }
            return level;
        }
    }
}