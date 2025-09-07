// Problem Link : https://leetcode.com/problems/product-of-array-except-self

// Brute Force Approach:
// For each i, multiply every nums[j] where j != i

// Code:
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for(int i=0;i<n;i++){
            long prod = 1;
            for(int j=0;j<n;j++){
                if(i == j) continue;
                prod *= nums[j];
            }
            res[i] = (int)prod;
        }
        return res;
    }
}

// Time complexity: O(n²)
// Space complexity: O(1) (ignoring output array)

// Approach 2: Using Division:
// Compute product of all non-zero elements and count zeroes.
// If zeroCount > 1, all elements in result will be 0.
// If zeroCount == 1, only the position of zero in result will be product of all non-zero elements.
// If zeroCount == 0, each result[i] = totalProduct / nums[i]

// Code:

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int zeroCount = 0;
        long totalProduct = 1L;
        for(int num : nums){
            if(num == 0) {
                zeroCount++;
                if(zeroCount > 1) break;
            }
            else {
                totalProduct *= num;
            }
        }
        int[] res = new int[n];
        if(zeroCount > 1){
            return res;
        }
        for(int i=0;i<n;i++){
            if(zeroCount == 1){
                res[i] = (nums[i] == 0) ? (int) totalProduct : 0;
            }
            else{
                res[i] = (int)(totalProduct / nums[i]);
            }
        }
        return res;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1) (ignoring output array)

// Approach 3: Prefix and Suffix arrays.
// Build L[i] = product of all elements to the left of i
// Build R[i] = product of all elements to the right of i
// Result[i] = L[i] * R[i]

// Code:

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        long[] left = new long[n];
        long[] right = new long[n];
        int[] res = new int[n];
        left[0] = 1;
        for(int i=1;i<n;i++){
            left[i] = left[i-1] * nums[i-1];
        }
        right[n-1] = 1;
        for(int i=n-2;i>=0;i--){
            right[i] = right[i+1] * nums[i+1];
        }
        for(int i=0;i<n;i++){
            res[i] = (int) (left[i] * right[i]);
        }
        return res;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n)

// Approach 4: Optimized Prefix and Suffix arrays.
// First pass left to right: fill res[i] with product of all elements to the left of i
// Second pass right to left: maintain a running product of elements to the right and multiply with res[i]

// Code:

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for(int i=1;i<n;i++){
            res[i] = res[i-1] * nums[i-1];
        }
        long right = 1L;
        for(int i=n-1;i>=0;i--){
            res[i] = (int) (res[i] * right);
            right *= nums[i];
        }
        return res;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1) (ignoring output array)