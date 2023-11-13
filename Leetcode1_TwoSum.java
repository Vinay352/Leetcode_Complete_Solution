package com.company.Leetcode_Complete_Solution;

import java.util.*;

public class Leetcode1_TwoSum {
    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;

        int[] indices = twoSum(nums, target);
        for(int i = 0; i < 2; i++){
            System.out.print(indices[i] + " ");
        }
        System.out.println();
    }

    // using a different collection = List
    // Time complexity = O(nlogn)
    // Space Complexity = O(n)
    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;

        // use array list to store values with their indices
        List<PairValueIndex> newList = new ArrayList<PairValueIndex>();
        for(int i = 0; i < length; i++){
            newList.add(new PairValueIndex(nums[i], i));
        }
        // sort the Collections as well as the indices of the nums array
        // after sorting and this collection need to match
        // Why? => if both are sorted based on the array values,
        // every index in nums will refer to same array object/value in nums as well as
        // in the collection. Therefore, when we try to access the elements at
        // firstPointer and secondPointer indices, they will refer to the same element in the nums
        // array as well in the collection.
        Collections.sort(newList, new Comparator<PairValueIndex>() {
            @Override
            public int compare(PairValueIndex o1, PairValueIndex o2) {
                return (o1.value - o2.value);
            }
        });

        Arrays.sort(nums); // sort the input array

        int firstPointer = 0; // pointer to the first value
        int secondPointer = length - 1; // pointer to the second value

        while (firstPointer < secondPointer) { // iterate as long as firstPointer doesn't cross secondPointer
            int firstValue = nums[firstPointer]; // corresponding first value
            int secondValue = nums[secondPointer]; // corresponding second value
            int sum = firstValue + secondValue; // what is the sum of both of them

            if(sum < target){ // if the sum is less
                firstPointer++; // move firstPointer ahead
            }
            else if(sum > target){ // if sum is more
                secondPointer--; // move secondPointer down
            }
            else if(sum == target){ // if there is match, stop
                break;
            }
        }

        int firstIndex = newList.get(firstPointer).index;
        int secondIndex = newList.get(secondPointer).index;

        int[] answerIndices = {firstIndex, secondIndex};
        return answerIndices;
    }
}

class PairValueIndex{
    int value;
    int index;
    PairValueIndex(int value, int index){
        this.value = value;
        this.index = index;
    }
}
