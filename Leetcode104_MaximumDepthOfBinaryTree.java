package com.company.Leetcode_Complete_Solution;

import java.util.*;

public class Leetcode104_MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        int[] root = {3,9,20, -1, -1,15,7};
        int[] root2 = {3,9,20,-1,-1,15,7};

        TreeNode104 tree = createBinaryTree(root, new TreeNode104(), 0);
        TreeNode104 tree2 = createBinaryTree(root2, new TreeNode104(), 0);
    }

    private static TreeNode104 createBinaryTree(int[] arr, TreeNode104 root, int i) {

        if (i < arr.length && arr[i] != -1) {
            root = new TreeNode104(arr[i]);

            // Insert left child
            root.left = createBinaryTree(arr, root.left, 2 * i + 1);

            // Insert right child
            root.right = createBinaryTree(arr, root.right, 2 * i + 2);
        }
        return root;

    }

    // calculate maxDepth using Recursion and DFS
    private static int maxDepth(TreeNode104 tree) {
        if(tree == null){
            return 0;
        }

        //recurse in left depth
        int leftDepth = 1 + maxDepth(tree.left);

        //recurse in right depth
        int rightDepth = 1 + maxDepth(tree.right);

        // return the greater depth
        return (leftDepth >= rightDepth) ? leftDepth : rightDepth;
    }

}

class TreeNode104 {
    int val;
    TreeNode104 left;
    TreeNode104 right;
    TreeNode104() {}
    TreeNode104(int val) { this.val = val; }
    TreeNode104(int val, TreeNode104 left, TreeNode104 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
