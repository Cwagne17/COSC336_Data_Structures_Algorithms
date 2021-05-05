import java.util.LinkedList;
import java.util.Queue;

// data structure that represents a node in the tree
class Node {
	int data; // holds the key
	Node parent; // pointer to the parent
	Node left; // pointer to left child
	Node right; // pointer to right child
	int color; // 1 . Red, 0 . Black
}

// class RedBlackTree implements insert and print in Red Black Tree datastructure
public class RedBlackTree {
	private Node root; //Root of the tree
	private Node TNULL; //The null child leaves that are black
  
  public RedBlackTree() {
		TNULL = new Node();
		TNULL.color = 0;
		TNULL.left = null;
		TNULL.right = null;
		root = TNULL;
  }

  // insert the key to the tree in its appropriate position
	// and fix the tree
	public void insert(int key) {
		// Ordinary Binary Search Insertion
		Node node = new Node();
		node.parent = null;
		node.data = key;
		node.left = TNULL;
		node.right = TNULL;
		node.color = 1; // new node must be red

		Node y = null;
		Node x = this.root;

    //Finds the place to insert the next int
		while (x != TNULL) {
			y = x;
			if (node.data < x.data) {
				x = x.left;
			} else {
				x = x.right;
			}
		}

		// y is parent of x
		node.parent = y;
		if (y == null) {
			root = node;
		} else if (node.data < y.data) {
			y.left = node;
		} else {
			y.right = node;
		}
		// if new node is a root node, simply return
		if (node.parent == null){
			node.color = 0;
			return;
		}
		// if the grandparent is null, simply return
		if (node.parent.parent == null){
			return;
		}
		fixInsert(node);
  }
  
	// fix the red-black tree
	private void fixInsert(Node node){
    Node uncle;
		while (node.parent.color == 1) { //While parent is red, goes up the tree once fixed
			if (node.parent == node.parent.parent.right) { //Covers cases when inserted node is to the right of grandparent
				uncle = node.parent.parent.left;
        if (uncle.color == 1) { //Rule 1
					uncle.color = 0;
					node.parent.color = 0;
					node.parent.parent.color = 1;
					node = node.parent.parent;
				} else { //Rule 2
					if (node == node.parent.left) { //Checks if it needs to rotate 
						node = node.parent;
						rightRotate(node);
					}
					//Flips colors and rotates about grandparent
					node.parent.color = 0;
					node.parent.parent.color = 1;
					leftRotate(node.parent.parent);
				}
			} else { //Covers cases when inserted node is to the left of grandparent
				uncle = node.parent.parent.right; // uncle
				if (uncle.color == 1) { //Rule 1
					uncle.color = 0;
					node.parent.color = 0;
					node.parent.parent.color = 1;
					node = node.parent.parent;	
				} else { //Rule 2 
					if (node == node.parent.right) { //Checks if it needs to rotate
						node = node.parent;
						leftRotate(node);
          }
          //Flips colors and rotates about grandparent
					node.parent.color = 0;
					node.parent.parent.color = 1;
					rightRotate(node.parent.parent);
				}
			}
			if (node == root) {
				break;
			}
		}
		root.color = 0;
  }
  
  // rotate left about node 
	public void leftRotate(Node node) {
		Node rightChild = node.right;
		node.right = rightChild.left;
		if (rightChild.left != TNULL) {
			rightChild.left.parent = node;
		}
		rightChild.parent = node.parent;
		if (node.parent == null) {
			this.root = rightChild;
		} else if (node == node.parent.left) {
			node.parent.left = rightChild;
		} else {
			node.parent.right = rightChild;
		}
		rightChild.left = node;
		node.parent = rightChild;
	}

	// rotate right about node
	public void rightRotate(Node node) {
		Node leftChild = node.left;
		node.left = leftChild.right;
		if (leftChild.right != TNULL) {
			leftChild.right.parent = node;
		}
		leftChild.parent = node.parent;
		if (node.parent == null) {
			this.root = leftChild;
		} else if (node == node.parent.right) {
			node.parent.right = leftChild;
		} else {
			node.parent.left = leftChild;
		}
		leftChild.right = node;
		node.parent = leftChild;
	}

  /**
	 * Uses a linked list as a makeshift queue
	 * null is the flag that determines whether the tree is going to the next line
	 * once an element is removed from the list, if the first element is null and is not empty then null is added
	 */
	public void print() {
		LinkedList<Node> list = new LinkedList<Node>();
		list.add(root);
		list.add(null);
		
		while (!list.isEmpty()) {
			Node tempNode = list.poll();

			// Adds left child to list
			if (tempNode.left != null && tempNode.left.data!=0)
				list.add(tempNode.left);
			// Adds right child to list
			if (tempNode.right != null && tempNode.right.data!=0)
				list.add(tempNode.right);

			//Gets parent information
			int parent = tempNode.parent==null?0:tempNode.parent.data;
			String parentValue = parent!=0?String.valueOf(parent*(tempNode.parent.color == 1?-1:1)):null;
			System.out.print("("+String.valueOf(tempNode.data*(tempNode.color == 1?-1:1))+","+parentValue+") ");
			
			if (list.element()==null) {
				System.out.println();
				list.remove();
				if (!list.isEmpty()) {
					list.add(null);
				}
			}

		}
	}
}