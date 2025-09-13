// Problem Link: https://leetcode.com/problems/multiply-strings?envType=problem-list-v2&envId=math

// Approach: Simulation of multiplication as done by hand
/* 
-> Initialize int[] res = new int[m + n].
->For i = m-1 .. 0:
-> For j = n-1 .. 0:
-> mul = (num1[i]-'0') * (num2[j]-'0')
-> sum = mul + res[i + j + 1]
-> res[i + j + 1] = sum % 10
-> res[i + j] += sum / 10
-> Convert res to string skipping leading zeros.
*/

// Code:
class Solution {
    public String multiply(String num1, String num2) {
        if(num1 == null || num2 == null) return null;
        if(num1 == "0" || num2 == "0") return "0";
        int m = num1.length();
        int n = num2.length();
        int[] res = new int[m+n];
        for(int i=m-1;i>=0;i--){
            int a = num1.charAt(i) - '0';
            for(int j=n-1;j>=0;j--){
                int b = num2.charAt(j) - '0';
                int mul = a * b;
                int sum = mul + res[i+j+1];
                res[i+j+1] = sum % 10;
                res[i+j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int digit : res){
            if(!(sb.length() == 0 && digit == 0)) sb.append(digit);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

// TC: O(m*n) where m and n are lengths of num1 and num2 respectively
// SC: O(m+n) for the result array