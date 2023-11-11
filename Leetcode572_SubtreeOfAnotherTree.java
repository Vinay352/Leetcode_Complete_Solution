package com.company.Leetcode_Complete_Solution;

public class Leetcode572_SubtreeOfAnotherTree {
    public static void main(String[] args) {
        int[] root = {3,4,5,1,2,-1,-1,-1,-1,0,-1};
        int[] root2 = {4,1,2};

        TreeNode572 tree = createBinaryTree(root, new TreeNode572(), 0);
        TreeNode572 tree2 = createBinaryTree(root2, new TreeNode572(), 0);

        boolean answer = isSubtree(tree, tree2);
        System.out.println(answer);
    }

    private static TreeNode572 createBinaryTree(int[] arr, TreeNode572 root, int i) {
        if (i < arr.length && arr[i] != -1) {
            root = new TreeNode572(arr[i]);

            // Insert left child
            root.left = createBinaryTree(arr, root.left, 2 * i + 1);

            // Insert right child
            root.right = createBinaryTree(arr, root.right, 2 * i + 2);
        }
        return root;
    }

    public static boolean isSubtree(TreeNode572 tree, TreeNode572 subTree){
        if(subTree == null){ // if subtree is null
            return true;
        }
        if(tree == null && subTree != null){ // if tree is null, subtree is not
            return false;
        }
        if(tree != null && subTree == null){ // if tree is not null, subtree is null
            return true;
        }

        if(isSameTree(tree, subTree)){ // if a match from the start node of both trees is found
            return true;
        }

        // if we do not have a value match at the current node of both trees
        boolean leftSide = isSubtree(tree.left, subTree); // check in tree.left for subtree
        boolean rightSide = isSubtree(tree.right, subTree); // check in tree.right for subtree

        return leftSide || rightSide; // if found in left or right
    }

    public static boolean isSameTree(TreeNode572 tree, TreeNode572 tree2) {
        // if both have reached the end
        if(tree == null && tree2 == null){
            return true;
        }

        // if one tree reaches a leaf node and the other one doesn't
        if(tree == null || tree2 == null){
            return false;
        }

        if(tree.val == tree2.val){
            //recurse in left depth
            boolean foundInLeftDepth = isSameTree(tree.left, tree2.left);
            //recurse in right depth
            boolean foundInRightDepth = isSameTree(tree.right, tree2.right);

            // if left and right, both are same
            return foundInLeftDepth && foundInRightDepth;
        }

        return false;
    }
}

class TreeNode572 {
    int val;
    TreeNode572 left;
    TreeNode572 right;
    TreeNode572() {}
    TreeNode572(int val) { this.val = val; }
    TreeNode572(int val, TreeNode572 left, TreeNode572 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
