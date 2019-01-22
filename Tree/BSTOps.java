package Tree;

import java.util.Stack;

public class BSTOps {
	public static void main(String []args) {
		int[] nodes = {1,2,4,7,34,21,6,43,45,67};
		//int[] nodes = {1,2,4};
		BST tree = new BST();
		for(int i : nodes) {
			tree.addNode(i);
		}
		//tree.printUtil();
		System.out.println("-------Tree is ---------------");
		System.out.println(tree);
		tree.inOrder();
		
		System.out.println("Deleting node ---------------");
		tree.removeNode(8);
		
		System.out.println("-------Tree is ---------------");
		System.out.println(tree);
		
	}
}


class BST {
	BNode root;
	public BST() {
		this.root = null;
	}
	public void printUtil() {
		System.out.println(this.root);
	}
	public void addNode(int value) {
		if(this.root == null) {
			this.root = new BNode(value);
		} else {
			System.out.println("inserting node " + value);
			this.root = this.insertNode_Recursive(value, this.root);
		}
	}
	public void inOrder() {
		System.out.println("-----In Order Recursive Travel------");
		System.out.println(this.inOrderTraversalBFS_Recursive(this.root));
		System.out.println("-----In Order Iterative Travel------");
		System.out.println(this.inOrderTraversalBFS_Iterative(this.root));
	}
	public BNode insertNode_Iterative(int value, BNode node){
		boolean notInserted = true;
		while(notInserted) {
			if(node == null) {
				node = new BNode(value);
				notInserted = false;
			} else if(node.value == value) {
				notInserted = false;
				
			} else if(value<node.value) {
				if(node.left!=null) {
					node = node.left;
				} else {
					node.left = new BNode(value);
					notInserted = false;
				}
			} else {
				if(node.right!=null) {
					node = node.right;
				} else {
					node.right = new BNode(value);
					notInserted = false;
				}
			}
		}
		node.height = this.max(getHeight(node.left), getHeight(node.right)) + 1;
		return node;
	}

	public BNode insertNode_Recursive(int value, BNode node) {
		if(node==null) {
			node = new BNode(value);
		}
		else if(value == node.value) {
			return node;
		}
		else if(value < node.value) {
			//System.out.println("Insert node to left");
			node.left = insertNode_Recursive(value, node.left);
		} else {
			//System.out.println("Insert node to right");
			node.right = insertNode_Recursive(value, node.right);
		}
		node.height = this.max(getHeight(node.left), getHeight(node.right)) + 1;
		int balance = this.getBalance(node);
		//Left heavy
		if(balance > 1) {
			System.out.println("Balance >1 for node: " + node.value + "with balance:" + balance); 
			//Left Left case
			if(node.value > value) {
			}
			//Left Right case
			else {
				node.left = this.leftRotate(node.left);
			}
			return this.rightRotate(node);
		} else if(balance < -1) {
			System.out.println("Balance <1 for node: " + node.value + "with balance:" + balance); 
			//Right Right case
			if(node.value < value) {
				
			} 
			//Right Left case
			else {
				node.right = this.rightRotate(node.right);
			}
			return this.leftRotate(node);
		}
		return node;
	}
	public BNode leftRotate(BNode node) {
		//System.out.println(this.printtNode(node));
		BNode rightChild = node.right;
		BNode T2 = rightChild.left;
		node.right = T2;
		rightChild.left = node;
		node.height = this.max(getHeight(node.left), getHeight(node.right)) + 1;
		rightChild.height = this.max(getHeight(rightChild.left), getHeight(rightChild.right)) + 1;
		//System.out.println(this.printtNode(rightChild));
		return rightChild;
	}
	
	public BNode rightRotate(BNode node) {
		BNode leftChild = node.left;
		if(leftChild.right!=null) {
			BNode T2 = leftChild.right;
			node.left = T2;
		}
		leftChild.right = node;
		node.height = this.max(getHeight(node.left), getHeight(node.right)) + 1;
		leftChild.height = this.max(getHeight(leftChild.left), getHeight(leftChild.right)) + 1;
		return leftChild;
	}
	
	public int max(int a, int b) {
		return a>b?a:b;
	}
	public int getBalance(BNode node) {
		return getHeight(node.left) - getHeight(node.right);
	}
	public int getHeight(BNode node) {
		if(node==null) return 0;
		return node.height;
	}
	public void removeNode(int value) {
		this.removeNode_Recursive(this.root, value);
	}
	public BNode removeNode_Recursive(BNode node, int value)  {
		//Find the node first
		if(node == null) return node;
		if(node.value > value) {
			node.left = this.removeNode_Recursive(node.left, value);
		} else if(node.value < value) {
			node.right = this.removeNode_Recursive(node.right, value);
		} else {
			//Found the node to delete
			//Case 1: leaf node
			System.out.println("Found Node with value: " + node.value);
			if(node.left == null && node.right == null) {
				node = null;
				return node;
			}
			//Case 2: Right subtree is null
			else if(node.right == null) {
				return node.left;
			}
			//Case 3: Left subtree is null
			else if(node.left == null){
				return node.right;
			}
			//Case 4: Both are populated
			else {
				//Find min in right SubTree
				BNode minNode = findMin(node.right);
				BNode tempNode = new BNode(minNode.value);
				tempNode.right = node.right;
				tempNode.left = node.left;
				minNode = null;
				node = null;
				return tempNode;
			}
		}
		return node;
	}
	
	public BNode findMin(BNode node) {
		if(node.left==null && node.right==null) {
			return node;
		}
		else if(node.left !=null) {
			return findMin(node.left);
		} else {
			return node;
		}
	}
	
	//left, root, right
	public String inOrderTraversalBFS_Iterative(BNode node) {
		String result = "";
		Stack<BNode> nodes = new Stack<BNode>();
		BNode currentNode = node;
		while(currentNode!=null) {
			while(currentNode.left!=null) {
				nodes.push(currentNode);
				currentNode = currentNode.left;
			}
			result += currentNode.value + ",";
			if(!nodes.isEmpty()) {
				currentNode = nodes.pop();
				result += currentNode.value + ",";
			}
			currentNode = currentNode.right;
		}
		return result;
	}
	
	//Root, Left, Right
	public String preOrderTraversalBFS_Iterative() {
		return null;
	}
	//Left, Right, Root
	public String postOrderTraversalBFS_Iterative() {
		return null;
	}
	//left, root, right
	public String inOrderTraversalBFS_Recursive(BNode node) {
		String result = "";
		if(node==null) return result;
		result += this.inOrderTraversalBFS_Recursive(node.left);
		result += node.value + ",";
		result +=  this.inOrderTraversalBFS_Recursive(node.right);
		return result;
	}
	//Root, Left, Right
	public String preOrderTraversalBFS_Recursive() {
		return null;
	}
	//Left, Right, Root
	public String postOrderTraversalBFS_Recursive() {
		return null;
	}
	public String toString() {
		return this.toString(this.root, 1, 0);
	}
	public String printtNode(BNode node) {
		return this.toString(node, 1, 0);
	}
	private String toString(BNode node, int level, int type) {
		String result = "";
		if(node==null) return result;

		result += this.getPadding(level);
		
		if(type==1) result+= "l:";
		if(type==2) result+= "r:";
		result +=  node.value + ":" + node.height;
		result += "\n" + this.toString(node.left, level+1, 1) + 
					this.toString(node.right, level+1, 2);

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

class BNode {
	BNode left;
	BNode right;
	public int value;
	public int height;
	public BNode(int value) {
		left = right = null;
		this.value = value;
		this.height = 0;
	}
	
	public String toString() {
		String result = this.value + " left:" + this.left + " | right:" + this.right;
		return result;
	}
}