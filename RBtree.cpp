#include "RBtree.h"

//Used to create each node of the RB tree
TreeNode::TreeNode(int value, TreeNode* parent, TreeNode* leftLeaf, TreeNode* rightLeaf) {
  this->value = value;
  parentNode = parent;
  this->leftLeaf = leftLeaf;
  this->rightLeaf = rightLeaf;
}
int TreeNode::getValue(){ return value; }
int TreeNode::getAbsValue(){ return abs(value); }
TreeNode* TreeNode::getParent(){ return parentNode; }
TreeNode* TreeNode::getLeftLeaf(){ return leftLeaf; }
TreeNode* TreeNode::getRightLeaf(){ return rightLeaf; }
bool TreeNode::isRed(){ return value<0; }
void TreeNode::flipColor(){ value = value*(-1); }
void TreeNode::setParent(TreeNode* parent){ parentNode=parent; }
void TreeNode::setLeftLeaf(TreeNode* leaf){ leftLeaf=leaf; }
void TreeNode::setRightLeaf(TreeNode* leaf){rightLeaf=leaf;}

RedBlackTree::RedBlackTree(){ head=NULL; }

void RedBlackTree::rightRotation(TreeNode* pivot) {
  TreeNode* child = pivot->getLeftLeaf();
  TreeNode* parent = pivot->getParent();
  TreeNode* rightChildLeaf = child->getRightLeaf();
  child->setRightLeaf(pivot);
  pivot->setLeftLeaf(rightChildLeaf);
  if(parent != NULL){
    if(parent->getAbsValue()>pivot->getAbsValue()){
      parent->setLeftLeaf(child);
    } else {
      parent->setRightLeaf(child);
    }
  }
  //Set the correct parents
  pivot->setParent(child);
  child->setParent(parent);
  if(rightChildLeaf != NULL){
    rightChildLeaf->setParent(pivot);
  }
}

void RedBlackTree::leftRotation(TreeNode* pivot) {
  TreeNode* child = pivot->getRightLeaf();
  TreeNode* parent = pivot->getParent();
  TreeNode* leftChildLeaf = child->getLeftLeaf();
  child->setLeftLeaf(pivot);
  pivot->setRightLeaf(leftChildLeaf);
  if(parent != NULL){
    if(parent->getAbsValue()>pivot->getAbsValue()){
      parent->setLeftLeaf(child);
    } else {
      parent->setRightLeaf(child);
    }
  }
  //Set the correct parents
  pivot->setParent(child);
  child->setParent(parent);
  if(leftChildLeaf!=NULL){
    leftChildLeaf->setParent(pivot);
  }
}

//Implementation of creating the RB-tree
void RedBlackTree::insert(int value){
  if(head==NULL){
    head = new TreeNode(value, NULL, NULL, NULL);
  } else {
    //Walks tree to the a null leaf where the value should be intiallity placed
    TreeNode* tempPtr = head;
    bool flag = true;
    while(flag){
      if(value < tempPtr->getAbsValue()){
        if(tempPtr->getLeftLeaf()==NULL){
          flag=false;
        } else {
          tempPtr = tempPtr->getLeftLeaf();
        }
      } else if(value > tempPtr->getAbsValue()){
        if(tempPtr->getRightLeaf()==NULL){
          flag=false;
        } else {
          tempPtr = tempPtr->getRightLeaf();
        }
      }
    }

    //Insert element
    TreeNode* newNode;
    if(value < tempPtr->getAbsValue()){
      tempPtr->setLeftLeaf(new TreeNode(-value, tempPtr, NULL, NULL));
      newNode = tempPtr->getLeftLeaf();
    } else if (value > tempPtr->getAbsValue()){
      tempPtr->setRightLeaf(new TreeNode(-value, tempPtr, NULL, NULL));
      newNode = tempPtr->getRightLeaf();
    } else {
      cout << value << "Has already been inserted in the tree" << endl;
    }

    flag = true;
    while(flag && (newNode->getParent() != NULL && newNode->getParent()->isRed())){
      //Gets releative tree nodes for check
      TreeNode* parent = newNode->getParent();
      TreeNode* grandparent = parent->getParent();
      TreeNode* uncle;
      //Gets Uncle
      if(grandparent->getAbsValue() < parent->getAbsValue()){
        uncle=grandparent->getLeftLeaf();
      } else {
        uncle=grandparent->getRightLeaf();
      }
      //Rule #1: parent and uncle both red (negative), then flip grandparent, uncle, and parent
      if(uncle != NULL && uncle->isRed()){
        uncle->flipColor(); grandparent->flipColor(); parent->flipColor();
        newNode = grandparent;
      } else {
        //Rule #2: parent is red and uncle is black
        //Checks if it needs rotation
        if(parent->getAbsValue() > newNode->getAbsValue() && grandparent->getAbsValue() < parent->getAbsValue()) {
          //right rotation about parent
          rightRotation(parent);
        } else if(parent->getAbsValue() < newNode->getAbsValue() && grandparent->getAbsValue() > parent->getAbsValue()) {
          //left rotation about parent
          leftRotation(parent);
        }
        //Flips after oriented correctly
        if(parent->getAbsValue() < newNode->getAbsValue() && newNode->getAbsValue() < grandparent->getAbsValue()){
          //right rotate about grandparent
          rightRotation(grandparent);
          //flip parent and grandparent colors
          parent->flipColor();
          grandparent->flipColor();
        } else if(grandparent->getAbsValue()<parent->getAbsValue() && newNode->getAbsValue() > parent->getAbsValue()){
          //left rotate about grandparent
          leftRotation(grandparent);
          //flip parent and grandparent colors
          parent->flipColor();
          grandparent->flipColor();
        }
        if(newNode->getParent()==NULL){
          head = newNode;
          newNode->setParent(NULL);
          flag=false;
        } else{
          newNode = newNode->getParent();
        }
      }
    } 
  }
}

void RedBlackTree::printTree() {
    // Base Case 
    if (head == NULL) return; 
  
    // Create an empty queue for level order tarversal 
    queue<TreeNode *> q; 
  
    // Enqueue Root and initialize height 
    q.push(head); 
  
    while (q.empty() == false) { 
        // nodeCount (queue size) indicates number
        // of nodes at current lelvel. 
        int nodeCount = q.size(); 
  
        // Dequeue all nodes of current level and 
        // Enqueue all nodes of next level 
        while (nodeCount > 0) { 
            TreeNode *node = q.front(); 
            cout << "(" << node->getValue();
            if( node->getParent() != NULL){
              cout << "," << node->getParent()->getValue() << ") "; 
            } else {
              cout << ",NULL)";
            }
            
            q.pop(); 
            if (node->getLeftLeaf() != NULL) 
                q.push(node->getLeftLeaf()); 
            if (node->getRightLeaf() != NULL) 
                q.push(node->getRightLeaf()); 
            nodeCount--; 
        } 
        cout << endl; 
    }
}