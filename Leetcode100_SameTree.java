package com.company.Leetcode_Complete_Solution;

public class Leetcode100_SameTree {
    public static void main(String[] args) {
        int[] root = {3,9,20, -1, -1,15,7};
        int[] root2 = {3,9,20,-1,-1,15,7};

        TreeNode100 tree = createBinaryTree(root, new TreeNode100(), 0);
        TreeNode100 tree2 = createBinaryTree(root2, new TreeNode100(), 0);

        boolean sameTree = depthFirstSearchUsingRecursion(tree, tree2);

        System.out.println(sameTree);

    }

    private static TreeNode100 createBinaryTree(int[] arr, TreeNode100 root, int i) {

        if (i < arr.length && arr[i] != -1) {
            root = new TreeNode100(arr[i]);

            // Insert left child
            root.left = createBinaryTree(arr, root.left, 2 * i + 1);

            // Insert right child
            root.right = createBinaryTree(arr, root.right, 2 * i + 2);
        }
        return root;

    }

    private static boolean depthFirstSearchUsingRecursion(TreeNode100 tree, TreeNode100 tree2) {
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
            boolean foundInLeftDepth = depthFirstSearchUsingRecursion(tree.left, tree2.left);
            //recurse in right depth
            boolean foundInRightDepth = depthFirstSearchUsingRecursion(tree.right, tree2.right);

            // if left and right, both are same
            return foundInLeftDepth && foundInRightDepth;
        }

        return false;
    }
}

class TreeNode100 {
    int val;
    TreeNode100 left;
    TreeNode100 right;

    TreeNode100() {
    }

    TreeNode100(int val) {
        this.val = val;
    }

    TreeNode100(int val, TreeNode100 left, TreeNode100 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
