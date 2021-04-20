#include "RBtree.h"

//Used to create each node of the RB tree
TreeNode::TreeNode(int value, TreeNode* parent, TreeNode* leftLeaf, TreeNode* rightLeaf) {
  this->value = value;
  parentNode = parent;
  this->leftLeaf = leftLeaf;
  this->rightLeaf = rightLeaf;
}
int TreeNode::getValue(){ return value; }
TreeNode* TreeNode::getParent(){ return parentNode; }
TreeNode* TreeNode::getLeftLeaf(){ return leftLeaf; }
TreeNode* TreeNode::getRightLeaf(){ return rightLeaf; }
bool TreeNode::isRed(){ return value<0; }
void TreeNode::flipColor(){ value*=-1; }
void TreeNode::setParent(TreeNode* parent){ parentNode=parent; }
void TreeNode::setLeftLeaf(TreeNode* leaf){ leftLeaf=leaf; }
void TreeNode::setRightLeaf(TreeNode* leaf){rightLeaf=leaf;}

//Implementation of creating the RB-tree
void RedBlackTree::insert(int value){
  if(head==NULL){
    head = new TreeNode(value, NULL, NULL, NULL);
  } else {
    //Walks tree to the a null leaf where the value should be intiallity placed
    TreeNode* tempPtr = head;
    bool flag = true;
    while(flag && (tempPtr->getLeftLeaf()!=NULL || tempPtr->getRightLeaf()!=NULL)){
      if(value<tempPtr->getValue() && tempPtr->getLeftLeaf()!=NULL){
        tempPtr = tempPtr->getLeftLeaf();
      } else if(value>tempPtr->getValue() && tempPtr->getRightLeaf()!=NULL){
        tempPtr = tempPtr->getRightLeaf();
      } else {
        flag = false;
      }
    }

    //Insert element
    TreeNode* newNode;
    if(value<tempPtr->getValue()){
      tempPtr->setLeftLeaf(new TreeNode(-value, tempPtr, NULL, NULL));
      newNode = tempPtr->getLeftLeaf();
    } else if (value>tempPtr->getValue()){
      tempPtr->setRightLeaf(new TreeNode(-value, tempPtr, NULL, NULL));
      newNode = tempPtr->getRightLeaf();
    } else {
      cout << value +"has already been inserted in the tree" << endl;
    }

    //Gets releative tree nodes for check
    TreeNode* grandparent = tempPtr->getParent();
    TreeNode* parent = tempPtr;
    TreeNode* uncle;
    //Gets Uncle
    if(grandparent->getValue() < parent->getValue()){
      uncle=grandparent->getLeftLeaf();
    } else {
      uncle=grandparent->getRightLeaf();
    }

    if(parent->isRed()){
      //Rule #1: parent and uncle both red (negative), then flip grandparent, uncle, and parent
      if(uncle->isRed(){
        uncle->flipColor(); grandparent->flipColor(); parent->flipColor();
      } else if(){ //Conditional for whether needs left or right rotation

      }
    }
    
  }
}