package com.company.Leetcode_Complete_Solution;

import java.util.*;

public class CreateBinaryTree_DFS_BFS {
    public static void main(String[] args) {
        int[] root = {3,9,20, -1, -1,15,7};

        TreeNode tree = createBinaryTree(root, new TreeNode(), 0);

//        printBinaryTree(tree);

        boolean bfsFound = breadthFirstSearch(tree, 7);
        System.out.println(bfsFound);

        boolean dfsFoundUsingStack = depthFirstSearchUsingStack(tree, 7);
        System.out.println(dfsFoundUsingStack);

        boolean dfsFoundUsingRecursion = depthFirstSearchUsingRecursion(tree, 7);
        System.out.println(dfsFoundUsingRecursion);
    }

    private static boolean depthFirstSearchUsingRecursion(TreeNode tree, int target) {
        if(tree == null){
            return false;
        }

        if(tree.val == target){
            return true;
        }
        //recurse in left depth
        boolean foundInLeftDepth = depthFirstSearchUsingRecursion(tree.left, target);
        //recurse in right depth
        boolean foundInRightDepth = depthFirstSearchUsingRecursion(tree.right, target);

        return foundInLeftDepth || foundInRightDepth;
    }

    private static boolean depthFirstSearchUsingStack(TreeNode tree, int target) {
        if(tree == null){
            return false;
        }

        // stack to keep track of incoming nodes of the tree
        // stack is used to imitate traverse the tree depth wise
        Stack<TreeNode> stack = new Stack<>();
        stack.push(tree);

        // track visited nodes
        Set<TreeNode> visitedSet = new HashSet<TreeNode>();

        while(!stack.isEmpty()){
            TreeNode popNode = stack.pop();

            if(popNode.val == target){ // check for a match
                return true;
            }

            if(popNode.right != null){ // insert right node in the queue
                stack.add(popNode.right);
            }
            if(popNode.left != null){ // insert left node in the queue
                stack.add(popNode.left);
            }

            visitedSet.add(popNode);
        }

        // still not found
        return false;
    }

    private static boolean breadthFirstSearch(TreeNode tree, int target) {
        if(tree == null){
            return false;
        }

        // queue to keep track of incoming nodes of the tree in level-order fashion
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(tree);

        // track visited nodes
        Set<TreeNode> visitedSet = new HashSet<TreeNode>();

        // implement BFS
        while(!queue.isEmpty()){
            TreeNode popNode = queue.poll(); // get the current node

            if(popNode.val == target){ // check for a match
                return true;
            }

            if(popNode.left != null){ // insert left node in the queue
                queue.add(popNode.left);
            }
            if(popNode.right != null){ // insert right node in the queue
                queue.add(popNode.right);
            }

            visitedSet.add(popNode); // update the visited set
        }
        // still not found
        return false;
    }

    private static void printBinaryTree(TreeNode tree) {
        if(tree == null){
            System.out.println(" null ");
            return ;
        }
        printBinaryTree(tree.left);
        System.out.println(tree.val);
        printBinaryTree(tree.right);
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

    public static int log2(int N) {

        // calculate log2 N indirectly
        // using log() method
        int result = (int)(Math.log(N) / Math.log(2));

        return result;
    }
}

class PairInteger{
    int value;
    int index;
    PairInteger(int value, int index){
        this.value = value;
        this.index = index;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


