// Java program to check if binary tree 
// is subtree of another binary tree 
class TreeNode { 

	char data; 
	TreeNode left, right; 

	TreeNode(char item) 
	{ 
		data = item; 
		left = right = null; 
	} 
} 

public class Ques4_10 { 

	static TreeNode root; 
	
	public static boolean containsTree(TreeNode t1, TreeNode t2) {
		if (t2 == null) {
			return true; // The empty tree is a subtree of every tree.
		}
		return subTree(t1, t2);
	}
	
	/* Checks if the binary tree rooted at r1 contains the binary tree 
	 * rooted at r2 as a subtree somewhere within it.
	 */
	public static boolean subTree(TreeNode r1, TreeNode r2) {
		if (r1 == null) {
			return false; // big tree empty & subtree still not found.
		} else if (r1.data == r2.data && matchTree(r1,r2)) {
			return true;
		}
		return subTree(r1.left, r2) || subTree(r1.right, r2); 
	}

	/* Checks if the binary tree rooted at r1 contains the 
	 * binary tree rooted at r2 as a subtree starting at r1.
	 */
	public static boolean matchTree(TreeNode r1, TreeNode r2) {
		if (r1 == null && r2 == null) {
			return true; // nothing left in the subtree
		} else if (r1 == null || r2 == null) { 
			return false; // exactly one tree is empty, therefore trees don't match
		} else if (r1.data != r2.data) {  
			return false;  // data doesn't match
		} else {
			return matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right);
		}
	}

	// Driver program to test above functions 
	public static void main(String args[]) 
	{ 
		TreeNode T = new TreeNode('a'); 
		T.left = new TreeNode('b'); 
		T.right = new TreeNode('d'); 
		T.left.left = new TreeNode('c'); 
		T.right.right = new TreeNode('e'); 

		TreeNode S = new TreeNode('a'); 
		S.left = new TreeNode('b'); 
		S.right = new TreeNode('d'); 
		S.left.left = new TreeNode('c'); 

		if (containsTree(T, S)) { 
			System.out.println("Yes, S is a subtree of T"); 
		} 
		else { 
			System.out.println("No, S is not a subtree of T"); 
		} 
	} 
}