// Problem Link: https://leetcode.com/problems/add-strings

// Approach: Add digits like you would by hand: start from the least significant digits (end of strings), keep a carry, add corresponding digits, append digit result, move left.

/*  
-> Initialize i = num1.length()-1, j = num2.length()-1, carry = 0, and StringBuilder sb = new StringBuilder().
-> While i >= 0 || j >= 0 || carry != 0:
-> sum = carry
-> if i >= 0 then sum += num1.charAt(i) - '0', i--
-> if j >= 0 then sum += num2.charAt(j) - '0', j--
-> append (char)('0' + (sum % 10)) to sb
-> carry = sum / 10
-> At the end reverse sb and return sb.toString().
*/

// Code:
class Solution {
    public String addStrings(String num1, String num2) {
        int i = num1.length()-1;
        int j = num2.length()-1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while(i>=0 || j>=0 || carry!=0){
            int sum = carry;
            if(i >= 0) sum += num1.charAt(i) - '0'; i--;
            if(j >= 0) sum += num2.charAt(j) - '0'; j--;
            sb.append((char) ('0' + (sum % 10)));
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }
}

// Time Complexity: O(max(n, m)) where n and m are lengths of num1 and num2 respectively.
// Space Complexity: O(max(n, m)) for the output string.