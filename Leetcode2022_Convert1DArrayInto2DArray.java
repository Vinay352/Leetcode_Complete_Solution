package com.company.Leetcode_Complete_Solution;

public class Leetcode2022_Convert1DArrayInto2DArray {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1};
        int m = 4;
        int n = 1;

        int[][] newArray = construct2DArray(nums, m, n);

        if(newArray != null){
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    System.out.print(newArray[i][j]);
                }
                System.out.println();
            }
        }
        else{
            System.out.println("Not possible");
        }
    }

    public static int[][] construct2DArray(int[] original, int m, int n) {
        int length = original.length;
        if(length != m * n){
            return new int[0][0];
        }

        int[][] answer = new int[m][n];

        int row = 0;
        int col = 0;

        int nextMultipleToCheck = n;
        int increment = 0;
        for(int i = 0; i < length; i++){
            answer[col][row] = original[i];
            row++;

            if(i == nextMultipleToCheck - 1){
                increment = 1;
            }

            if(increment == 1){
                row = 0;
                col++;
                nextMultipleToCheck = nextMultipleToCheck + n;
                increment = 0;
            }
        }
        return answer;
    }
}
