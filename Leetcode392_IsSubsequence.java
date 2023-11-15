package com.company.Leetcode_Complete_Solution;

public class Leetcode392_IsSubsequence {
    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";

        boolean ans = isSubsequence(s, t);
        System.out.println(ans);
    }

    public static boolean isSubsequence(String s, String t) {
        int sPointer = 0;
        int tPointer = 0;

        int targetLength = s.length();
        int counter = 0;

        while(sPointer < s.length() && tPointer < t.length()){
            if( s.charAt(sPointer) == t.charAt(tPointer) ){
                counter++;
                sPointer++;
            }
            tPointer++;
        }

        if(counter == targetLength){
            return true;
        }

        return false;
    }
}
