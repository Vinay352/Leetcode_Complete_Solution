package com.company.Leetcode_Complete_Solution;

public class Leetcode617_MergeTwoBinaryTrees {
    
    public static void main(String[] args) {
        int[] root = {1,3,2,5,-1};
        int[] root2 = {2,1,3,-1,4};

        TreeNode617 tree = createBinaryTree(root, new TreeNode617(), 0);
        TreeNode617 tree2 = createBinaryTree(root2, new TreeNode617(), 0);
        
        TreeNode617 mergedTrees = mergedTwoTreesFirst(tree, tree2);
        printBinaryTree(mergedTrees);
    }

    private static TreeNode617 mergedTwoTreesFirst(TreeNode617 tree1, TreeNode617 tree2) {
        if(tree1 == null && tree2 == null){
            return null;
        }
        if(tree1 == null && tree2 != null){
            return tree2;
        }
        if(tree1 != null && tree2 == null){
            return tree1;
        }

        TreeNode617 mergedTrees = tree1;
        mergedTrees = mergedTwoTrees(tree1, tree2, mergedTrees);
        return mergedTrees;
    }

    private static TreeNode617 mergedTwoTrees(TreeNode617 tree1, TreeNode617 tree2, TreeNode617 mergedTrees) {

        if(tree1 == null && tree2 == null){ // if both don't exist
            mergedTrees = null;
        }

        else if(tree1 != null && tree2 == null){ // if 1 is not null but 2 is
            mergedTrees = tree1;

            //recurse in left depth
            mergedTrees.left = mergedTwoTrees(tree1.left, null, mergedTrees.left);

            //recurse in right depth
            mergedTrees.right = mergedTwoTrees(tree1.right, null, mergedTrees.right);

        }

        else if(tree1 == null && tree2 != null){ // if 1 is null but 2 is not
            mergedTrees = tree2;

            //recurse in left depth
            mergedTrees.left = mergedTwoTrees(null, tree2.left, mergedTrees.left);

            //recurse in right depth
            mergedTrees.right = mergedTwoTrees(null, tree2.right, mergedTrees.right);

        }

        else if(tree1 != null && tree2 != null){ // if both exists, add them up in 1 node

            mergedTrees = tree1;
            mergedTrees.val += tree2.val;

            //recurse in left depth
            mergedTrees.left = mergedTwoTrees(tree1.left, tree2.left, mergedTrees.left);

            //recurse in right depth
            mergedTrees.right = mergedTwoTrees(tree1.right, tree2.right, mergedTrees.right);
        }

        return mergedTrees;
    }

    private static TreeNode617 createBinaryTree(int[] arr, TreeNode617 root, int i) {

        if (i < arr.length && arr[i] != -1) {
            root = new TreeNode617(arr[i]);

            // Insert left child
            root.left = createBinaryTree(arr, root.left, 2 * i + 1);

            // Insert right child
            root.right = createBinaryTree(arr, root.right, 2 * i + 2);
        }
        return root;

    }

    private static void printBinaryTree(TreeNode617 tree) {
        if(tree == null){
            System.out.println(" null ");
            return ;
        }
        printBinaryTree(tree.left);
        System.out.println(tree.val);
        printBinaryTree(tree.right);
    }
}

class TreeNode617 {
    int val;
    TreeNode617 left;
    TreeNode617 right;
    TreeNode617() {}
    TreeNode617(int val) { this.val = val; }
    TreeNode617(int val, TreeNode617 left, TreeNode617 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
