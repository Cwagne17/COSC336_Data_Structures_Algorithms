#include<iostream>
#include <queue>
#include <cstdlib>

using namespace std;

class TreeNode {
  private: 
    TreeNode* parentNode;
    TreeNode* leftLeaf;
    TreeNode* rightLeaf;
    int value;
  public:
    TreeNode(int value, TreeNode* parent, TreeNode* leftLeaf, TreeNode* rightLeaf);
    int getValue();
    int getAbsValue();
    TreeNode* getParent();
    TreeNode* getLeftLeaf();
    TreeNode* getRightLeaf();
    bool isRed();
    void flipColor();
    void setLeftLeaf(TreeNode* leaf);
    void setRightLeaf(TreeNode* leaf);
    void setParent(TreeNode* parent);
};

class RedBlackTree {
  private: 
    TreeNode* head;
    void leftRotation(TreeNode* pivot);
    void rightRotation(TreeNode* pivot);
  public:
    RedBlackTree();
    void insert(int value);
    void printTree();
};

