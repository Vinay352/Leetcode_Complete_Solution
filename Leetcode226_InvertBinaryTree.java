package com.company.Leetcode_Complete_Solution;

public class Leetcode226_InvertBinaryTree {
    public static void main(String[] args) {
        int[] root = {4,2,7,1,3,6,9};
        int[] root2 = {3,9,20,-1,-1,15,7};

        TreeNode226 tree = createBinaryTree(root, new TreeNode226(), 0);
        TreeNode226 tree2 = createBinaryTree(root2, new TreeNode226(), 0);

        TreeNode226 swapTree = invertTree(tree);
        printBinaryTree(swapTree);
    }

    public static TreeNode226 invertTree(TreeNode226 tree) {
        if(tree == null){
            return null;
        }

        TreeNode226 left = invertTree(tree.right);
        TreeNode226 right = invertTree(tree.left);

        tree.left = left;
        tree.right = right;

        return tree;
    }


    public static TreeNode226 createBinaryTree(int[] arr, TreeNode226 root, int i) {
        if (i < arr.length && arr[i] != -1) {
            root = new TreeNode226(arr[i]);

            // Insert left child
            root.left = createBinaryTree(arr, root.left, 2 * i + 1);

            // Insert right child
            root.right = createBinaryTree(arr, root.right, 2 * i + 2);
        }
        return root;
    }

    // infix expression = left root right
    private static void printBinaryTree(TreeNode226 tree) {
        if(tree == null){
            System.out.print(" null ");
            return ;
        }
        printBinaryTree(tree.left);
        System.out.print(" " + tree.val + " ");
        printBinaryTree(tree.right);
    }
}

class TreeNode226 {
    int val;
    TreeNode226 left;
    TreeNode226 right;
    TreeNode226() {}
    TreeNode226(int val) { this.val = val; }
    TreeNode226(int val, TreeNode226 left, TreeNode226 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
