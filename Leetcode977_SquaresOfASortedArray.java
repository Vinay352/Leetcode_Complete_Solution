package com.company.Leetcode_Complete_Solution;

public class Leetcode977_SquaresOfASortedArray {
    public static void main(String[] args) {
        int[] nums = {-5,-3,-2,-1};

        int[] squares = sortedSquares(nums);

        for(int i = 0; i < squares.length; i++){
            System.out.print(squares[i] + " ");
        }
        System.out.println();
    }

    // idea is to use 2 pointers
    // one for -ve values => -ve arrays
    // one for non -ve values => non-negative arrays
    // note that we have to work with just magnitudes of all the numbers
    // since squaring of -ve numbers also result in a positive number
    // the first occurrence of non-negative value in the array will indicate that
        // every element after this is non-negative
    // just before the 'first occurrence of non-negative value', from there and
        // all values before it will be -ve in increasing order of magnitude
    public static int[] sortedSquares(int[] nums) {
        int length = nums.length;

        int firstNonZeroIndex = length;
        // loop to figure out the index of the first element that is >= 0
        for(int i = 0; i < length; i++){
            if(nums[i] >= 0){
                firstNonZeroIndex = i;
                break;
            }
        }
        // if firstNonZeroIndex == length i.e. there is no >= 0 element in the array
        // above condition being true => assigns negativeElementsPointer to last element index of the array
            // since there are no non-negative elements and all are negative
        // above condition being false => if there are no negative elements (firstNonZeroIndex == 0),
                                            // negativeElementsPointer = -1
                                        // else just before firstNonZeroIndex
        int negativeElementsPointer = (firstNonZeroIndex == length) ? length - 1 : ((firstNonZeroIndex == 0) ? -1 : firstNonZeroIndex - 1);

        int[] squares = new int[length]; // our required array
        int squaresIndex = 0; // index for the above array

        // starting the merge sort of negative arrays and non-negative arrays
        while(negativeElementsPointer >= 0 && firstNonZeroIndex < length){
            int negativeValue = Math.abs( nums[negativeElementsPointer] ); // absolute value of negative array element
            int nonZeroValue = nums[firstNonZeroIndex]; // non-negative value
            if( negativeValue < nonZeroValue ){
                squares[squaresIndex] = negativeValue * negativeValue;
                negativeElementsPointer--;
                squaresIndex++;
            }
            else if( negativeValue > nonZeroValue ){
                squares[squaresIndex] = nonZeroValue * nonZeroValue;
                firstNonZeroIndex++;
                squaresIndex++;
            }
            else if(negativeValue == nonZeroValue){
                squares[squaresIndex] = nonZeroValue * nonZeroValue;
                firstNonZeroIndex++;
                squaresIndex++;

                squares[squaresIndex] = negativeValue * negativeValue;
                negativeElementsPointer--;
                squaresIndex++;
            }
        }

        // if there are still -ve elements left
        while(negativeElementsPointer >= 0){
            squares[squaresIndex] = nums[negativeElementsPointer] * nums[negativeElementsPointer];
            squaresIndex++;
            negativeElementsPointer--;
        }

        // if there are still non -ve elements left
        while(firstNonZeroIndex < length){
            squares[squaresIndex] = nums[firstNonZeroIndex] * nums[firstNonZeroIndex];
            squaresIndex++;
            firstNonZeroIndex++;
        }

        return squares;
    }
}