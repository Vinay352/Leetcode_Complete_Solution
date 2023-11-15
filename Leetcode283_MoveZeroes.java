package com.company.Leetcode_Complete_Solution;

public class Leetcode283_MoveZeroes {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};

        moveZeroes(nums);
        for(int i = 0; i < nums.length; i++){
            System.out.println(nums[i]);
        }
    }

    public static void moveZeroes(int[] nums) {
        int totalZeroes = 0;
        int startPointer = 0;
        int nonZeroPointer = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[nonZeroPointer] == 0){
                totalZeroes++;
            }
            else if(nums[nonZeroPointer] != 0){
                nums[startPointer] = nums[nonZeroPointer];
                startPointer++;
            }
            nonZeroPointer++;
        }

        if(nonZeroPointer == nums.length){
            for(int i = startPointer; i < nums.length; i++){
                nums[i] = 0;
            }
        }
    }
}
