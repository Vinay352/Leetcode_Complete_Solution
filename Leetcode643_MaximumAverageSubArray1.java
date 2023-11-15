package com.company.Leetcode_Complete_Solution;

public class Leetcode643_MaximumAverageSubArray1 {
    public static void main(String[] args) {

        int[] nums = {-1};
        int k = 1;

        double maxAv = findMaxAverage(nums, k);
        System.out.println(maxAv);

    }

    public static double findMaxAverage(int[] nums, int k) {
        int length = nums.length;
        int[] sumDP = new int[length];

        sumDP[0] = nums[0];

        for(int i = 1; i < length; i++){
            sumDP[i] = sumDP[i - 1] + nums[i];
        }

        int start = 0;
        int end = start + k - 1;

        double maxAverage = -999999;

        for(int i = start; end < length; i++){
            int requiredSum = 0;

            if(i == 0){
                requiredSum = sumDP[end];
            }
            else{
                requiredSum = sumDP[end] - sumDP[i - 1];
            }

            double average = (double)requiredSum / k;
            if(average > maxAverage){
                maxAverage = average;
            }

            end++;
        }

        return maxAverage;
    }
}
