package com.company.Leetcode_Complete_Solution;

import java.util.*;

public class AverageOfLevelsInBinaryTree {
    public static void main(String[] args) {
        int[] root = {3,9,20, -1, -1,15,7};
//        root = new int[]{2147483647, 2147483647, 2147483647};

        TreeNode637 tree = createBinaryTree(root, new TreeNode637(), 0);

        List<Double> avgLevelsBT = breadthFirstSearchModified(tree);

        avgLevelsBT.forEach((e) -> {
            System.out.println(e + ", ");
        });
    }

    private static TreeNode637 createBinaryTree(int[] arr, TreeNode637 root, int i) {

        if (i < arr.length && arr[i] != -1) {
            root = new TreeNode637(arr[i]);

            // Insert left child
            root.left = createBinaryTree(arr, root.left, 2 * i + 1);

            // Insert right child
            root.right = createBinaryTree(arr, root.right, 2 * i + 2);
        }
        return root;

    }

    private static List<Double> breadthFirstSearchModified(TreeNode637 tree) {
        if(tree == null){
            return null;
        }

        // queue to keep track of incoming nodes of the tree in level-order fashion
        Queue<TreeNode637> queue = new LinkedList<TreeNode637>();
        queue.add(tree);

        // track visited nodes
        Set<TreeNode637> visitedSet = new HashSet<TreeNode637>();

        int nodesAtCurrentLevel = 1;
        int countOfChildNodesAtNextLevel = 0;
        int totalLevelNodesCount = 1;

        List<Double> levelAverage = new ArrayList<Double>();
        double levelNodesSum = 0;

        // implement BFS
        while(!queue.isEmpty()){
            TreeNode637 popNode = queue.poll(); // get the current node

            levelNodesSum += popNode.val;

            if(popNode.left != null){ // insert left node in the queue
                queue.add(popNode.left);
                countOfChildNodesAtNextLevel++;
            }
            if(popNode.right != null){ // insert right node in the queue
                queue.add(popNode.right);
                countOfChildNodesAtNextLevel++;
            }

            nodesAtCurrentLevel--;

            if(nodesAtCurrentLevel == 0){
                levelAverage.add( (double)(levelNodesSum) / (double)(totalLevelNodesCount));
                nodesAtCurrentLevel = countOfChildNodesAtNextLevel;
                totalLevelNodesCount = countOfChildNodesAtNextLevel;
                countOfChildNodesAtNextLevel = 0;
                levelNodesSum = 0;
            }

            visitedSet.add(popNode); // update the visited set
        }

        return levelAverage;
    }

    private static void printBinaryTree(TreeNode637 tree) {
        if(tree == null){
            System.out.println(" null ");
            return ;
        }
        printBinaryTree(tree.left);
        System.out.println(tree.val);
        printBinaryTree(tree.right);
    }
}

class TreeNode637 {
    int val;
    TreeNode637 left;
    TreeNode637 right;
    TreeNode637() {}
    TreeNode637(int val) { this.val = val; }
    TreeNode637(int val, TreeNode637 left, TreeNode637 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
