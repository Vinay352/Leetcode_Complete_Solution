package com.company.Leetcode_Complete_Solution;

import java.util.*;

public class Leetcode111_MinimumDepthofBinaryTree {
    public static void main(String[] args) {
        int[] root = {2,-1,3,-1,4,-1,5,-1,6};

//        TreeNode111 tree = createBinaryTreeWithNullValuesInArray(root);

//        int minDepth = breadthFirstSearchModified(tree);
//        System.out.println(minDepth);
    }

    private static int breadthFirstSearchModified(TreeNode111 tree) {
        if(tree == null){
            return 0;
        }

        // queue to keep track of incoming nodes of the tree in level-order fashion
        Queue<TreeNode111> queue = new LinkedList<TreeNode111>();
        queue.add(tree);

        // track visited nodes
        Set<TreeNode111> visitedSet = new HashSet<TreeNode111>();

        int minDepth = 1;

        // implement BFS
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){

                TreeNode111 popNode = queue.poll();

                if(popNode.left == null && popNode.right == null){
                    return minDepth;
                }

                if(popNode.left != null){ // insert left node in the queue
                    queue.add(popNode.left);
                }
                if(popNode.right != null){ // insert right node in the queue
                    queue.add(popNode.right);
                }

                visitedSet.add(popNode); // update the visited set
            }
            minDepth++;
        }

        return minDepth;
    }

    // incomplete
//    private static TreeNode111 createBinaryTreeWithNullValuesInArray(int[] arr) {
//
//        TreeNode111 root = null;
//        TreeNode111 temp = root;
//
//        int nullCount = 0;
//
//        for(int i = 0; i < log2(arr.length); i++){
//            int leftIndex = 2 * i + 1 - (2 * nullCount);
//            int rightIndex = 2 * i + 2 - (2 * nullCount);
//
//            if(arr[i] == -1){
//                nullCount++;
//            }
//            else{
//                temp = new TreeNode111(arr[i]);
//                if(leftIndex < arr.length){
//                    temp.left = new TreeNode111(arr[leftIndex]);
//                }
//                if(rightIndex < arr.length){
//                    temp.right = new TreeNode111(arr[rightIndex]);
//                }
//            }
//        }
//        return root;
//    }

    public static int log2(int N) {

        // calculate log2 N indirectly
        // using log() method
        int result = (int)(Math.log(N) / Math.log(2));

        return result;
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
}

class TreeNode111 {
    int val;
    TreeNode111 left;
    TreeNode111 right;
    TreeNode111() {}
    TreeNode111(int val) { this.val = val; }
    TreeNode111(int val, TreeNode111 left, TreeNode111 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
