// Problem Link : https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c

// Approach : Visiting all bits of a, b and c and checking how many bits need to be flipped to make a OR b equal to c.
// Case 1: If c's bit is 0, then both a and b's bits must be 0. If either is 1, we need to flip it.
// Case 2: If c's bit is 1, then at least one of a or b's bits must be 1. If both are 0, we need to flip one of them.

// Time Complexity: O(1) since we are only iterating through 32 bits.
// Space Complexity: O(1) since we are using a constant amount of space.

class Solution {
    public int minFlips(int a, int b, int c) {
        int flips = 0;
        while(a != 0 || b != 0 || c != 0){
            if((c & 1) == 1){
                if((a & 1) == 0 && (b & 1) == 0){
                    flips++;
                }
            }
            else{
                flips += (a & 1) + (b & 1);
            }
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
        return flips;
    }
}