package com.company.Leetcode_Complete_Solution;

public class Leetcode844_BackspaceStringComparison {
    public static void main(String[] args) {
        String s = "ab#c";
        String t = "ad#c";

        boolean sameOrNot = backspaceCompare(s, t);
        System.out.println(sameOrNot);
    }

    public static boolean backspaceCompare(String s, String t) {
        int firstLength = s.length();
        int secondLength = t.length();

        int firstPointer = firstLength - 1; // set the pointer for the 1st string to the last char
        int secondPointer = secondLength - 1; // set the pointer for the 2nd string to the last char

        int countOfCharsToBackspaceString1 = 0; // to track the number of consecutive # encountered in string1
        int countOfCharsToBackspaceString2 = 0; // to track the number of consecutive # encountered in string2

        // work with both strings from reverse
        while(firstPointer >= 0 || secondPointer >= 0){
            // process consecutive #s in string 1
            while(firstPointer >= 0){
                char firstStringChar = s.charAt(firstPointer);
                if(firstStringChar == '#'){ // if the current char = #
                    countOfCharsToBackspaceString1++;
                    firstPointer--;
                }
                else if(countOfCharsToBackspaceString1 > 0){ // skip chars #s no. of times
                    countOfCharsToBackspaceString1--;
                    firstPointer--;
                }
                else{ // break out of the loop since current char != # and no more chars to skip
                    break;
                }
            }

            // process consecutive #s in string 2
            while(secondPointer >= 0){
                char secondStringChar = t.charAt(secondPointer);
                if(secondStringChar == '#'){ // if the current char = #
                    countOfCharsToBackspaceString2++;
                    secondPointer--;
                }
                else if(countOfCharsToBackspaceString2 > 0){ // skip chars #s no. of times
                    countOfCharsToBackspaceString2--;
                    secondPointer--;
                }
                else{ // break out of the loop since current char != # and no more chars to skip
                    break;
                }
            }

            //  if (valid positions/indices) and no match at the pointers in both string
            if( (firstPointer >= 0 && secondPointer >= 0) && (s.charAt(firstPointer) != t.charAt(secondPointer)) ){
                return false;
            }

            // if one string is finished processing, but the other one is not
            // i.e. comparing empty/null char with a proper char
            if( (firstPointer < 0 && secondPointer >= 0) || (firstPointer >= 0 && secondPointer < 0) ){
                return false;
            }

            // move to the next pointer
            firstPointer--;
            secondPointer--;
        }
        return true;
    }
}
