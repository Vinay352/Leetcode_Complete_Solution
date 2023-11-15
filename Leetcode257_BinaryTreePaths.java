package com.company.Leetcode_Complete_Solution;

import java.util.ArrayList;
import java.util.List;

public class Leetcode257_BinaryTreePaths {
    public static void main(String[] args) {
        int[] nums = {1,2,3,-1,5};

        TreeNode257 tree = createBinaryTree(nums, new TreeNode257(), 0);

        List<String> ans = binaryTreePaths(tree);
        for(int i = 0; i < ans.size(); i++){
            System.out.println(ans.get(i));
        }
    }

    public static List<String> binaryTreePaths(TreeNode257 root) {
        List<String> ans = new ArrayList<String>();
        String path = "";

        ans = binaryTreePathsUtil(root, path, ans);
        for(int i = 0; i < ans.size(); i++){
            String temp = ans.get(i);
            ans.set(i, temp.substring(2));
//            System.out.println( temp.substring(2) );
        }

        return ans;
    }

    private static List<String> binaryTreePathsUtil(TreeNode257 root, String path, List<String> ans) {
//        if(root == null){
//            path = path + "";
//            return ans;
//        }

        if(root.left == null && root.right == null){
            path = path + "->" + root.val;
            ans.add(path);
            return ans;
        }

        path = path + "->" + root.val;

        if(root.left != null){
//            path = path + "->" + root.val;
            ans = binaryTreePathsUtil(root.left, path, ans);
        }

        if(root.right != null){
//            path = path + "->" + root.val;
            ans = binaryTreePathsUtil(root.right, path, ans);
        }

        return ans;
    }

    private static TreeNode257 createBinaryTree(int[] arr, TreeNode257 root, int i) {

        if (i < arr.length && arr[i] != -1) {
            root = new TreeNode257(arr[i]);

            // Insert left child
            root.left = createBinaryTree(arr, root.left, 2 * i + 1);

            // Insert right child
            root.right = createBinaryTree(arr, root.right, 2 * i + 2);
        }
        return root;

    }
}

class TreeNode257 {
    int val;
    TreeNode257 left;
    TreeNode257 right;
    TreeNode257() {}
    TreeNode257(int val) { this.val = val; }
    TreeNode257(int val, TreeNode257 left, TreeNode257 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
