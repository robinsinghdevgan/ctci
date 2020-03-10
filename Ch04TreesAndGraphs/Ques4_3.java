import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Ques4_3 {
    public static void main(String[] args) {
        final Integer nodeCount = 10;
        Integer arr[] = new Integer[nodeCount];
        for (Integer i = 0; i < nodeCount; ++i) {
            arr[i] = new java.util.Random().nextInt(999);
        }

        BT<Integer> bt = new BT<Integer>(arr);

        System.out.println(bt.levelOrderTraversal());

        List<String> allLevels = bt.listOfDepths();
        for (String s : allLevels) {
            System.out.println(s);
        }
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

        public List<String> listOfDepths() {
            List<String> res = new LinkedList<String>();
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> mainQ = new LinkedList<TreeNode>();
            Queue<TreeNode> childQ = new LinkedList<TreeNode>();
            mainQ.add(root);
            int level = 0; 
            while (true){
                sb.append("Level " + level++ + "\n");
                while(!mainQ.isEmpty()) {
                    TreeNode n = mainQ.poll();
                    sb.append(n + " -> ");
                    if (n.left != null)
                        childQ.add(n.left);
                    if (n.right != null)
                        childQ.add(n.right);
                }
                res.add(sb.toString());
                sb.setLength(0);
                if (childQ.isEmpty())
                    break;
                mainQ.addAll(childQ);
                childQ.clear();
            }
            return res;
        }

        public String levelOrderTraversal() {
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> q = new LinkedList<TreeNode>();
            q.add(root);
            while(!q.isEmpty()) {
                TreeNode n = q.poll();
                sb.append(n + " -> ");
                if (n.left != null)
                    q.add(n.left);
                if (n.right != null)
                    q.add(n.right);
            }
            return sb.toString();
        }
    }
}