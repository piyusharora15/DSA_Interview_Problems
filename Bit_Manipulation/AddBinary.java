// Problem Link: https://leetcode.com/problems/add-binary?envType=problem-list-v2&envId=bit-manipulation

// Approach: We will use two pointers to traverse the binary strings from the end to the beginning. We will maintain a carry variable to handle the sum of bits. We will add corresponding bits from both strings along with the carry, and update the carry for the next iteration. Finally, we will reverse the result string to get the final binary sum.

// Code:

class Solution {
    public String addBinary(String a, String b) {
         a = trimLeadingZeros(a);
            b = trimLeadingZeros(b);
            int n = a.length();
            int m = b.length();
            if(n<m)
            {
                return addBinary(b,a);
            }
            int j = m-1;
            int carry = 0;
            StringBuilder result = new StringBuilder();
            for(int i=n-1;i>=0;i--)
            {
                int bit1 = a.charAt(i) - '0';
                int sum = bit1 + carry;
                if(j>=0)
                {
                    int bit2 = b.charAt(j) - '0';
                    sum+=bit2;
                    j--;
                }
                int bit = sum%2;
                carry = sum/2;
                result.append((char) (bit + '0'));
            }
            if(carry>0)
            result.append('1');
            return result.reverse().toString(); 
        }
        static String trimLeadingZeros(String s)
        {
            int firstOne = s.indexOf('1');
            return (firstOne==-1)?"0":s.substring(firstOne);
        } 
    }

    // Time Complexity: O(max(n, m)), where n and m are the lengths of the two binary strings.
    // Space Complexity: O(max(n, m)), for the result string.