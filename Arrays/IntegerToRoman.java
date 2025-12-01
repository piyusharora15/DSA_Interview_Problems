// Problem Link: https://leetcode.com/problems/integer-to-roman

// Approach: We create two arrays, one for the integer values and another for their corresponding Roman numeral symbols. We iterate through the integer values from largest to smallest, appending the corresponding Roman numeral symbols to the result string while subtracting the integer values from the input number until it reaches zero.

// Code:
class Solution {
    public String intToRoman(int num) {
         int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<values.length;i++){
            int count = num/values[i];
            num = num % values[i];
            for(int j=0;j<count;j++) sb.append(symbols[i]);
        }
        return sb.toString();
    }
}

// Time Complexity: O(1) - The number of iterations is constant since the number of Roman numeral symbols is fixed.
// Space Complexity: O(1) - The space used for the result string is proportional to the output size, which is limited by the maximum value of the input number (3999).