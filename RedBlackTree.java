// data structure that represents a node in the tree
class Node {
	int data; // holds the key
	Node parent; // pointer to the parent
	Node left; // pointer to left child
	Node right; // pointer to right child
	int color; // 1 . Red, 0 . Black
}

// class RedBlackTree implements the operations in Red Black Tree
public class RedBlackTree {
	private Node root;
	private Node TNULL;
  
  public RedBlackTree() {
		TNULL = new Node();
		TNULL.color = 0;
		TNULL.left = null;
		TNULL.right = null;
		root = TNULL;
  }

  public Node getRoot(){
		return this.root;
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

	

  // print the tree structure on the screen
	public void print() {
    printHelper(this.root, true);
  }
  
	private void printHelper(Node root, boolean last) {
		// print the tree structure on the screen
	  if (root != TNULL) {
      //Gets parent information
      int parent = root.parent==null?0:root.parent.data;
      int pColor=1;
      String parentValue = "";
      if(parent!=0){
        pColor = root.parent.color == 1?-1:1;
        parentValue = String.valueOf(parent*pColor);
      } else {
        parentValue = null;
      }
    
      //Prints whether red or black
      int sColor = root.color == 1?-1:1;
      System.out.print("("+root.data*sColor+","+parentValue+") ");
      if(last){
        System.out.println();
      }
      printHelper(root.left, false);
      printHelper(root.right, true);
		}
	}
	
}