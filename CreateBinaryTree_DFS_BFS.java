package com.company.Leetcode_Complete_Solution;

import java.util.*;

public class CreateBinaryTree_DFS_BFS {
    public static void main(String[] args) {
        int[] root = {3,9,20, -1, -1,15,7};
        int[] root2 = {3,9,20,-1,-1,15,7};

        TreeNode tree = createBinaryTree(root, new TreeNode(), 0);
        TreeNode tree2 = createBinaryTree(root2, new TreeNode(), 0);

//        printBinaryTree(tree);

        boolean bfsFound = breadthFirstSearch(tree, 7);
        System.out.println(bfsFound);

        boolean dfsFoundUsingStack = depthFirstSearchUsingStack(tree, 7);
        System.out.println(dfsFoundUsingStack);

        boolean dfsFoundUsingRecursion = depthFirstSearchUsingRecursion(tree, 7);
        System.out.println(dfsFoundUsingRecursion);

        // Section below deals with General tree traversals
        System.out.println("\n------GENREAL TREE (every node could have 1, 2 or more than 2 child nodes)-----------\n");

        TreeMultiChildNode rootOfGeneralTree = createGeneralTreeHardcoded();

        //This is a method to do Breadth First Traversal for a general tree.
        System.out.println("Breadth First Traversal Order for General Tree: ");
        breadthFirstTraversalForGeneralTree(rootOfGeneralTree);

        // This is a method to do Breadth First Search for a general tree.
        System.out.println("\nBreadth First Search for target for General Tree: D");
        boolean bfsFoundGeneralTree = breadthFirstSearchForGeneralTree(rootOfGeneralTree, 'D');
        System.out.println(bfsFoundGeneralTree);

        System.out.println("\nDepth First Traversal Order for General Tree: ");
        depthFirstTraversalUsingStackForGeneralTree(rootOfGeneralTree, 'D');

        System.out.println("\nDepth First Search for target for General Tree: D");
        boolean dfsFoundUsingStackForGeneralTree = depthFirstSearchUsingStackForGeneralTree(rootOfGeneralTree, 'D');
        System.out.println(dfsFoundUsingStackForGeneralTree);

        System.out.println("\nDepth First Search (using Recursion for General Tree) for target: D");
        Set<TreeMultiChildNode> visitedSet = new HashSet<TreeMultiChildNode>();
        visitedSet.add(rootOfGeneralTree);
        boolean dfsFoundUsingRecursionForGeneralTree = depthFirstSearchUsingRecursionForGeneralTree(rootOfGeneralTree, 'D', visitedSet);
        System.out.println(dfsFoundUsingRecursionForGeneralTree);

    }

    private static boolean depthFirstSearchUsingRecursionForGeneralTree(TreeMultiChildNode rootOfGeneralTree, char target, Set<TreeMultiChildNode> visitedSet) {
        if(rootOfGeneralTree == null){
            return false;
        }

        if(rootOfGeneralTree.val == target){
            return true;
        }

        // prints the depth first traversal order
        System.out.println(rootOfGeneralTree.val);

        // set to keep track of child responses for current nodes
        Set<Boolean> childResponsesForCurrentNode = new HashSet<Boolean>();

        // for every childNode of popNode
        for(int i = 0; i < rootOfGeneralTree.childNodes.size(); i++){
            TreeMultiChildNode tempChildNode = rootOfGeneralTree.childNodes.get(i);

            // if the tempChildNode is in the visited set
            // true = successful addition = was not in the set
            // false = otherwise = was in the set
            boolean notPresentInVisitedSet = visitedSet.add(tempChildNode);

            // successful addition of tempChildNode to visitedSet = node was not in the visited set
            if(notPresentInVisitedSet == true){
                // get the child node's response
                boolean childResponse = depthFirstSearchUsingRecursionForGeneralTree(tempChildNode, target, visitedSet);
                childResponsesForCurrentNode.add(childResponse);

                // if any child has responded = true
                // we have found the target
                if(childResponsesForCurrentNode.contains(true)){
                    return true;
                }
            }
        }

        return false;

    }

    private static boolean depthFirstSearchUsingStackForGeneralTree(TreeMultiChildNode rootOfGeneralTree, int target) {
        if(rootOfGeneralTree == null){
            return false;
        }

        // stack to keep track of incoming nodes of the tree
        // stack is used to imitate traverse the tree depth wise
        Stack<TreeMultiChildNode> stack = new Stack<>();
        stack.push(rootOfGeneralTree);

        // track visited nodes
        Set<TreeMultiChildNode> visitedSet = new HashSet<>();
        visitedSet.add(rootOfGeneralTree);

        while(!stack.isEmpty()){
            TreeMultiChildNode popNode = stack.pop();

            if(popNode.val == target){ // check for a match
                return true;
            }

            // for every childNode of popNode
            for(int i = 0; i < popNode.childNodes.size(); i++){
                TreeMultiChildNode tempChildNode = popNode.childNodes.get(i);

                // prints the depth first traversal order
//                System.out.println(popNode.val);

                // if the tempChildNode is in the visited set
                // true = successful addition = was not in the set
                // false = otherwise = was in the set
                boolean notPresentInVisitedSet = visitedSet.add(tempChildNode);

                // successful addition of tempChildNode to visitedSet = node was not in the visited set
                if(notPresentInVisitedSet == true){
                    stack.push(tempChildNode);
                }
            }
        }

        // still not found
        return false;
    }

    private static boolean depthFirstTraversalUsingStackForGeneralTree(TreeMultiChildNode rootOfGeneralTree, int target) {
        if(rootOfGeneralTree == null){
            return false;
        }

        // stack to keep track of incoming nodes of the tree
        // stack is used to imitate traverse the tree depth wise
        Stack<TreeMultiChildNode> stack = new Stack<>();
        stack.push(rootOfGeneralTree);

        // track visited nodes
        Set<TreeMultiChildNode> visitedSet = new HashSet<>();
        visitedSet.add(rootOfGeneralTree);

        while(!stack.isEmpty()){
            TreeMultiChildNode popNode = stack.pop();

            // prints the depth first traversal order
            System.out.println(popNode.val);

            if(popNode.val == target){ // check for a match
                return true;
            }

            // for every childNode of popNode
            for(int i = 0; i < popNode.childNodes.size(); i++){
                TreeMultiChildNode tempChildNode = popNode.childNodes.get(i);

                // if the tempChildNode is in the visited set
                // true = successful addition = was not in the set
                // false = otherwise = was in the set
                boolean notPresentInVisitedSet = visitedSet.add(tempChildNode);

                // successful addition of tempChildNode to visitedSet = node was not in the visited set
                if(notPresentInVisitedSet == true){
                    stack.push(tempChildNode);
                }
            }
        }

        // still not found
        return false;
    }

    private static boolean breadthFirstSearchForGeneralTree(TreeMultiChildNode rootOfGeneralTree, char target) {
        if(rootOfGeneralTree == null){
            return false;
        }

        // queue to keep track of incoming nodes of the tree in level-order fashion
        Queue<TreeMultiChildNode> queue = new LinkedList<TreeMultiChildNode>();

        // track visited nodes
        Set<TreeMultiChildNode> visitedSet = new HashSet<TreeMultiChildNode>();

        // add root node to visitedSet and queue
        visitedSet.add(rootOfGeneralTree);
        queue.add(rootOfGeneralTree);

        // implement Breadth first search
        while(!queue.isEmpty()){
            TreeMultiChildNode popNode = queue.poll(); // get the top node

            if(popNode.val == target){
                return true;
            }

            // for every childNode of popNode
            for(int i = 0; i < popNode.childNodes.size(); i++){
                TreeMultiChildNode tempChildNode = popNode.childNodes.get(i);

                // if the tempChildNode is in the visited set
                // true = successful addition = was not in the set
                // false = otherwise = was in the set
                boolean notPresentInVisitedSet = visitedSet.add(tempChildNode);

                // successful addition of tempChildNode to visitedSet = node was not in the visited set
                if(notPresentInVisitedSet == true){
                    queue.add(tempChildNode);
                }
            }
        }
        // still not found
        return false;
    }

    private static void breadthFirstTraversalForGeneralTree(TreeMultiChildNode rootOfGeneralTree) {
        if(rootOfGeneralTree == null){
            return ;
        }

        // queue to keep track of incoming nodes of the tree in level-order fashion
        Queue<TreeMultiChildNode> queue = new LinkedList<TreeMultiChildNode>();

        // track visited nodes
        Set<TreeMultiChildNode> visitedSet = new HashSet<TreeMultiChildNode>();

        // add root node to visitedSet and queue
        visitedSet.add(rootOfGeneralTree);
        queue.add(rootOfGeneralTree);

        // implement Breadth first traversal
        while(!queue.isEmpty()){
            TreeMultiChildNode popNode = queue.poll(); // get the top node

            // prints the breadth first traversal order
            System.out.println(popNode.val);

            // for every childNode of popNode
            for(int i = 0; i < popNode.childNodes.size(); i++){
                TreeMultiChildNode tempChildNode = popNode.childNodes.get(i);

                // if the tempChildNode is in the visited set
                // true = successful addition = was not in the set
                // false = otherwise = was in the set
                boolean notPresentInVisitedSet = visitedSet.add(tempChildNode);

                // successful addition of tempChildNode to visitedSet = node was not in the visited set
                if(notPresentInVisitedSet == true){
                    queue.add(tempChildNode);
                }
            }
        }

        return ;
    }

    /**
     * A method to create a general tree in which nodes can 1 or 2 or more than 2 child nodes.
     * This function creates a hardcoded tree.
     *
     *                  A
     *                /   \
     *               B --- C
     *                \   /|
     *                  D  |
     *                 /   |
     *                E   F
     *                \  /
     *                 G
     *
     *   Vertices are = A, B, C, D, E, F, G
     *   Rest all lines are edges
     *   A child = B, C
     *   B child = A, C, D
     *   C child = A, B, D, F
     *   D child = B, C, E
     *   E child = D, G
     *   F child = C, G
     *   G child = E, F
     *
     * */
    private static TreeMultiChildNode createGeneralTreeHardcoded() {
        TreeMultiChildNode root = new TreeMultiChildNode('A', new ArrayList<TreeMultiChildNode>());
        TreeMultiChildNode bNode = new TreeMultiChildNode('B', new ArrayList<TreeMultiChildNode>());
        TreeMultiChildNode cNode = new TreeMultiChildNode('C', new ArrayList<TreeMultiChildNode>());
        TreeMultiChildNode dNode = new TreeMultiChildNode('D', new ArrayList<TreeMultiChildNode>());
        TreeMultiChildNode eNode = new TreeMultiChildNode('E', new ArrayList<TreeMultiChildNode>());
        TreeMultiChildNode fNode = new TreeMultiChildNode('F', new ArrayList<TreeMultiChildNode>());
        TreeMultiChildNode gNode = new TreeMultiChildNode('G', new ArrayList<TreeMultiChildNode>());

        root.childNodes.add(bNode);
        root.childNodes.add(cNode);

        bNode.childNodes.add(root);
        bNode.childNodes.add(cNode);
        bNode.childNodes.add(dNode);

        cNode.childNodes.add(root);
        cNode.childNodes.add(bNode);
        cNode.childNodes.add(dNode);
        cNode.childNodes.add(fNode);

        dNode.childNodes.add(bNode);
        dNode.childNodes.add(cNode);
        dNode.childNodes.add(eNode);

        eNode.childNodes.add(dNode);
        eNode.childNodes.add(gNode);

        fNode.childNodes.add(cNode);
        fNode.childNodes.add(gNode);

        gNode.childNodes.add(eNode);
        gNode.childNodes.add(fNode);

        return root;
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
/**
 * A class to represent nodes of a general tree
 * (1,2 or more than 2 child nodes - not binary tree)
 * */
class TreeMultiChildNode {
    char val;
    List<TreeMultiChildNode> childNodes;
    TreeMultiChildNode() {}
    TreeMultiChildNode(char val) { this.val = val; }
    TreeMultiChildNode(char val, List<TreeMultiChildNode> childNodes) {
        this.val = val;
        this.childNodes = childNodes;
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


