package com.company.Leetcode_Complete_Solution;

public class Leetcode100_SameTree {
    public static void main(String[] args) {
        int[] root = {3,9,20, -1, -1,15,7};
        int[] root2 = {3,9,20,-1,-1,15,7};

        TreeNode tree = createBinaryTree(root, new TreeNode(), 0);
        TreeNode tree2 = createBinaryTree(root2, new TreeNode(), 0);

        boolean sameTree = depthFirstSearchUsingRecursion(tree, tree2);

        System.out.println(sameTree);

    }

    private static TreeNode createBinaryTree(int[] arr, TreeNode root, int i) {

        if (i < arr.length && arr[i] != -1) {
            root = new TreeNode(arr[i]);

            // Insert left child
            root.left = createBinaryTree(arr, root.left, 2 * i + 1);

            // Insert right child
            root.right = createBinaryTree(arr, root.right, 2 * i + 2);
        }
        return root;

    }

    private static boolean depthFirstSearchUsingRecursion(TreeNode tree, TreeNode tree2) {
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
