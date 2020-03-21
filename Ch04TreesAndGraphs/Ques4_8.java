import java.util.*;


public class Ques4_8 {
    public static void main(String[] args) {
        final Integer nodeCount = 150;
        Integer arr[] = new Integer[nodeCount];
        for (Integer i = 0; i < nodeCount; ++i) {
            arr[i] = new java.util.Random().nextInt(999);
        }
        Arrays.sort(arr);
        BT<Integer> bt = new BT<Integer>(arr);
        int i=0, j=20;
        System.out.println("\nfirstCommonAncestor of " + arr[i] + " and " + arr[j] + " is: " + bt.firstCommonAncestor(arr[i], arr[j]));
    }
	
	static class BT<T extends Comparable<T>> {
		T firstCommonAncestor(T nodeValue1, T nodeValue2) {
			TreeNode x = findNode(nodeValue1);
			TreeNode y = findNode(nodeValue2);
			ArrayList<T> qx = getPathTo(x);
			ArrayList<T> qy = getPathTo(y);
            T result = null;
            var iqx = qx.iterator();
            var iqy = qy.iterator();
            while(iqx.hasNext() || iqy.hasNext()) {
                T tempx = null, tempy = null;
                if (iqx.hasNext())
                    tempx = iqx.next();
                if (iqy.hasNext())
                    tempy = iqy.next();
                if(!( tempx == tempy)) {
                    break;
                }
                result = tempx;
            }
			return result;
        }
        
        public ArrayList<T> getPathTo(TreeNode n) {
            // ArrayList to store the path  
            ArrayList<T> arr = new ArrayList<T>(); 
        
            // if required node 'x' is present  
            // then print the path  
            if (hasPath(root, arr, n.data))  
            {  
                System.out.println();
                for (int i=0; i<arr.size()-1; i++)      
                    System.out.print(arr.get(i)+" -> "); 
                System.out.print(arr.get(arr.size() - 1));     
            }  
            
            // 'x' is not present in the binary tree   
            else
                System.out.print("No Path");
            return arr;
        }
        private boolean hasPath(TreeNode root, ArrayList<T> arr, T x)  {  
            // if root is NULL  
            // there is no path  
            if (root==null)  
                return false;  
            
            // push the node's value in 'arr'  
            arr.add(root.data);      
            
            // if it is the required node  
            // return true  
            if (root.data == x)      
                return true;  
            
            // else check whether the required node lies  
            // in the left subtree or right subtree of   
            // the current node  
            if (hasPath(root.left, arr, x) ||  
                hasPath(root.right, arr, x))  
                return true;  
            
            // required node does not lie either in the   
            // left or right subtree of the current node  
            // Thus, remove current node's value from   
            // 'arr'and then return false      
            arr.remove(arr.size()-1);  
            return false;              
        }  

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
            System.out.println();
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