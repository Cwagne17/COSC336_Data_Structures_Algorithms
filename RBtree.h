#include<iostream>

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
    void insert(int value);
    void printTree();
};

