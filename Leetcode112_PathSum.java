package com.company.Leetcode_Complete_Solution;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Leetcode112_PathSum {
    public static void main(String[] args) {
        int[] root = {3,9,20, -1, -1,15,7};
        int[] root2 = {5,4,8,11,-1,13,4,7,2,-1,-1,-1,-1,-1,1};

        int targetSum = 22;

        TreeNode112 tree = createBinaryTree(root, new TreeNode112(), 0);
        TreeNode112 tree2 = createBinaryTree(root2, new TreeNode112(), 0);

        boolean hasSumOrNot = hasPathSumIterative(tree2, targetSum);

        System.out.println(hasSumOrNot);
    }

    private static TreeNode112 createBinaryTree(int[] arr, TreeNode112 root, int i) {

        if (i < arr.length && arr[i] != -1) {
            root = new TreeNode112(arr[i]);

            // Insert left child
            root.left = createBinaryTree(arr, root.left, 2 * i + 1);

            // Insert right child
            root.right = createBinaryTree(arr, root.right, 2 * i + 2);
        }
        return root;

    }

    // recursion 1
    private static boolean hasPathSumRecursion1(TreeNode112 tree, int targetSum, int sum) {
        if(tree == null){
            return sum == targetSum;
        }

        sum += tree.val;

        //recurse in left depth
        boolean leftSum = hasPathSumRecursion1(tree.left, targetSum, sum);

        //recurse in right depth
        boolean rightSum = hasPathSumRecursion1(tree.right, targetSum, sum);

        sum -= tree.val; // when every node's left and right child have been explored, then subtract the node's value

        return leftSum || rightSum;

    }

    private static boolean hasPathSum(TreeNode112 tree, int targetSum) {
        if(tree == null){ // if root is null
            return false;
        }

        return hasPathSumRecursion2(tree, targetSum);
    }

    // recursion 2
    private static boolean hasPathSumRecursion2(TreeNode112 tree, int targetSum) {
        if(tree.left == null && tree.right == null){ // if we've reached the leaf node
            return tree.val == targetSum;
        }

        boolean leftSum = false;
        //recurse in left depth
        if(tree.left != null){
            tree.left.val += tree.val; // update left child value
            leftSum = hasPathSumRecursion2(tree.left, targetSum);
        }

        boolean rightSum = false;
        //recurse in right depth
        if(tree.right != null){
            tree.right.val += tree.val; // update right child value
            rightSum = hasPathSumRecursion2(tree.right, targetSum);
        }

        return leftSum || rightSum;

    }

    // iterative
    private static boolean hasPathSumIterative(TreeNode112 tree, int targetSum) {
        if(tree == null){
            return false;
        }

        // stack to keep track of incoming nodes of the tree
        // stack is used to imitate traverse the tree depth wise
        Stack<TreeNode112> stack = new Stack<>();
        stack.push(tree);

        // track visited nodes
        Set<TreeNode112> visitedSet = new HashSet<TreeNode112>();

        // traverse the tree once while appending every node in the the tree
        while(!stack.isEmpty()){
            int notALeaf = 0; // track whether current popNode is leaf or not
            TreeNode112 popNode = stack.pop();

            if(popNode.right != null){ // insert right node in the queue
                popNode.right.val += popNode.val; // update right child value
                stack.add(popNode.right);
                notALeaf = 1;
            }
            if(popNode.left != null){ // insert left node in the queue
                popNode.left.val += popNode.val; // update left child value
                stack.add(popNode.left);
                notALeaf = 1;
            }

            if(notALeaf == 0){ // if we've encountered a leaf node
                if(popNode.val == targetSum){ // check for a match
                    return true;
                }
            }
            visitedSet.add(popNode);
        }

        return false;
    }

}

class TreeNode112 {
    int val;
    TreeNode112 left;
    TreeNode112 right;

    TreeNode112() {
    }

    TreeNode112(int val) {
        this.val = val;
    }

    TreeNode112(int val, TreeNode112 left, TreeNode112 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}