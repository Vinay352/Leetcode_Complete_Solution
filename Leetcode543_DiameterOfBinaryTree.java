package com.company.Leetcode_Complete_Solution;

public class Leetcode543_DiameterOfBinaryTree {

    public static void main(String[] args) {
        int[] root = {1,2,3,4,5};
        int[] root2 = {1,2};

        TreeNode543 tree = createBinaryTree(root, new TreeNode543(), 0);
        TreeNode543 tree2 = createBinaryTree(root2, new TreeNode543(), 0);

        int diameter = diameterOfBinaryTree(tree) ;
        System.out.println(diameter);
    }

    // function to calculate diameter of binary tree
    private static int diameterOfBinaryTree(TreeNode543 tree) {
        if(tree == null){
            return 0;
        }

        int[] result = new int[1];

        int heightOfTree = diameterOfBinaryTreeUtil(tree, result);
        int diameter = result[0];// max diameter is stored in result[0]
        return diameter;
    }

    // use postfix approach and DFS
    private static int diameterOfBinaryTreeUtil(TreeNode543 tree, int[] result) {
        if(tree == null){
            return -1; // convention is [height = -1] for null nodes
        }

//        if(tree.left == null && tree.right == null){
//            return 0; // convention is [height = 0] for leaf nodes
//        }

        int leftHeight = diameterOfBinaryTreeUtil(tree.left, result);
        int rightHeight = diameterOfBinaryTreeUtil(tree.right, result);

        // to update the maximum diameter
        int diameterSoFar = calculateDiameter(leftHeight, rightHeight);
        if(diameterSoFar >= result[0]){ // if new diameter is greater than our max diameter
            result[0] = diameterSoFar;
        }

        // to return the new maxHeight to the calling function
        int maxHeightSoFar = calculateHeight(leftHeight, rightHeight);

        return maxHeightSoFar; // return the new maximum height to its parent function
    }

    // a function to calculate diameter of current node
    // idea is to add heights from left and right subtrees
    // and + 2
    // because 1 edge from parent node to left node
    // and 1 edge from parent node to right node
    public static int calculateDiameter(int leftHeight, int rightHeight){
        return 2 + leftHeight + rightHeight;
    }

    // height of current node would be whatever is the maximum from left and right subtree
    // + 1
    // because of adding a new level/height i.e. current/parent node
    public static int calculateHeight(int leftHeight, int rightHeight){
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static TreeNode543 createBinaryTree(int[] arr, TreeNode543 root, int i) {

        if (i < arr.length && arr[i] != -1) {
            root = new TreeNode543(arr[i]);

            // Insert left child
            root.left = createBinaryTree(arr, root.left, 2 * i + 1);

            // Insert right child
            root.right = createBinaryTree(arr, root.right, 2 * i + 2);
        }
        return root;

    }
}

class TreeNode543 {
    int val;
    TreeNode543 left;
    TreeNode543 right;
    TreeNode543() {}
    TreeNode543(int val) { this.val = val; }
    TreeNode543(int val, TreeNode543 left, TreeNode543 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class PairInteger543{
    int withSplit;
    int withoutSplit;
    int result;
    PairInteger543(int withSplit, int withoutSplit){
        this.withoutSplit = withoutSplit;
        this.withSplit = withSplit;
        this.result = calculateMaxResult(0, withSplit, withoutSplit);
    }

    public int calculateMaxResult(int result, int withSplit, int withoutSplit){
        return Math.max(result, Math.max(withoutSplit, withSplit));
    }
}
