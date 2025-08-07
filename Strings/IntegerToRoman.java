// Problem Link : https://leetcode.com/problems/integer-to-roman

// Brute Force Approach:
//Keep subtracting the largest Roman value until the input’s zero. Define parallel arrays of values = [1000, 900, ..., 1] and symbols = ["M", "CM", ..., "I"]. For each index i, while num >= values[i], subtract and append symbol.

class Solution {
    public String intToRoman(int num) {
        int[] val = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] sym = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        String result = "";
        int i = 0;
        while(num > 0){
            while(num >= val[i]){
                num -= val[i];
                result += sym[i];
            }
            i++;
        }
        return result;
    }
}

// Time Complexity: O(1) - The number of Roman numeral symbols is constant.
// Space Complexity: O(1) - The space used is constant, as we are using a fixed-size array for values and symbols.
// Note: The while loop runs a constant number of times since the maximum value of num is 3999, which can be represented with a fixed number of Roman numeral symbols. Thus, the overall complexity remains constant.
// Edge Cases: The input is guaranteed to be between 1 and 3999, so we do not need to handle cases outside this range. The algorithm will always produce a valid Roman numeral for any input within this range.

// Optimized Approach 1:
// The brute force approach is already quite efficient for this problem, as it directly maps integer values to Roman numeral symbols. However, we can make a slight optimization by using StringBuilder instead of string concatenation for better performance in terms of memory and speed.

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

// Time Complexity: O(1) - The number of Roman numeral symbols is constant.
// Space Complexity: O(1) - The space used is constant, as we are using a fixed-size array for values and symbols, and StringBuilder for the result.

// Optimized Approach 2: We leverage arrays for thousands, hundreds, tens, ones—direct lookup.

class Solution {
    public String intToRoman(int num) {
        String[] M = {"","M","MM","MMM"};
        String[] C = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        String[] X = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String[] I = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
        return M[num/1000] + C[num%1000/100] + X[num%100/10] + I[num%10];
    }
}

// Time Complexity: O(1) - The number of Roman numeral symbols is constant.
// Space Complexity: O(1) - The space used is constant, as we are using fixed-size arrays for thousands, hundreds, tens, and ones.