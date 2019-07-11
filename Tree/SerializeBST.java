/**
 * @author Srinivas Maroju
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
package Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 *     
 *     Test Case
 *               5
 *            4     7   
 *          1  2   8  9
 * }
 */
public class SerializeBST {
	public static void main(String []args) {
		Codec codec = new Codec();
		
		//Construct Tree
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(2);
		
		root.right.left = new TreeNode(8);
		root.right.right = new TreeNode(9);
		
		System.out.println("Initial Tree is:");
		System.out.println(codec.printTree(root, 0, 0));
		
		TreeNode node = codec.deserialize(codec.serialize(root));
		System.out.println("Tree After Deserialize is:");
		System.out.println(node.val);
		System.out.println(codec.printTree(node, 0, 0));
	}
}
class Codec {
	static int index = 0;
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuffer result = this.serializeDFS(root, new StringBuffer(""));
		System.out.println(result);
		return result.toString();
	}
	
	public StringBuffer serializeDFS(TreeNode node, StringBuffer result) {
		if(node==null) {
			result.append("null" + ",");
			return result;
		}
	
		result.append(node.val + ",");
		serializeDFS(node.left, result);
		serializeDFS(node.right, result);
		
		return result;
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		List<String> arrayData = Arrays.asList(data.split(","));
		//System.out.println(arrayData);
		TreeNode root = this.deserializeDFS(arrayData, true);
		return root;
	}
	
	public TreeNode deserializeDFS(List<String> data, boolean isLeft) {
		TreeNode node = null;
		//System.out.println("Value of index:" + index);

		if(data.size() >= index-1) {
			
			String value = data.get(index);
			index = index + 1;

			if(value.equals("null")) {
				//System.out.println("Processing value: null");
				return node;
			} else {
				//System.out.println("Processing value: " + value);
				node = new TreeNode(Integer.parseInt(value));

				//index = index+1;
				node.left = deserializeDFS(data,  true);
				//index = index+1;
				node.right = deserializeDFS(data, false);
			}
		}
		return node;
	}
	public String printTree(TreeNode node, int level, int type) {
		String result = "";
		if(node==null) return result;

		result += this.getPadding(level);
		
		if(type==1) result+= "l:";
		if(type==2) result+= "r:";
		result +=  node.val;
		result += "\n" + printTree(node.left, level+1, 1) + 
				printTree(node.right, level+1, 2);

		//this.toString()
		return result;
	}
	private String getPadding(int level) {
		StringBuffer padding = new StringBuffer();
		for(int i=0; i<level; i++) {
			padding.append(" ");
		}
		return padding.toString();
	}
}
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
	public String toString() {
		return val + "";
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
