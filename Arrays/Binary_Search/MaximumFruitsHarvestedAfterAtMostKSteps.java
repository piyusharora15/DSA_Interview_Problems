// Problem Link: https://leetcode.com/problems/maximum-fruits-harvested-after-at-most-k-steps?envType=daily-question&envId=2025-08-03

// Approach : The approach involves calculating the maximum number of fruits that can be collected from trees represented by their positions and quantities in a given range. Using a prefix sum array, it efficiently tracks the total fruits available up to each index. The algorithm explores two movement scenarios: first, moving left a certain distance and then moving right, and vice versa. By employing binary search to identify relevant tree indices, the method calculates possible harvests and updates the maximum fruits collected based on the allowed movements, ensuring optimal performance.

class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int[] prefixSum = new int[n];
        int[] indices = new int[n];
        // Build prefix sum and extract indices
        for(int i=0;i<n;i++){
            indices[i] = fruits[i][0];
            prefixSum[i] = fruits[i][1] + (i>0 ? prefixSum[i-1] : 0);
        }
        int maxFruits = 0;
        for(int d=0;d<=k/2;d++){
            // First Case: Move left 'd' steps, then pick 'remain'steps
            int remain = k - 2 * d;
            int i = startPos - d;
            int j = startPos + remain;
            // Find the range[i,j] using binary search
            int left = lowerBound(indices,i);
            int right = upperBound(indices,j) - 1;
            if(left <= right){
                int total = prefixSum[right] - (left > 0 ? prefixSum[left-1] : 0);
                maxFruits = Math.max(maxFruits,total);
            }
            // Second case: Move right 'd' steps then pick 'remain' steps
            remain = k - 2 * d;
            i = startPos - remain;
            j = startPos + d;
            left = lowerBound(indices,i);
            right = upperBound(indices,j) - 1;
            if(left <= right){
                int total = prefixSum[right] - (left > 0 ? prefixSum[left-1] : 0);
                maxFruits = Math.max(maxFruits,total);
            }
        }
        return maxFruits;
    }
    private int lowerBound(int[] arr,int target){
        int left = 0, right = arr.length;
        while(left < right){
            int mid = (left + right) / 2;
            if(arr[mid] < target){
                left = mid+1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }
    private int upperBound(int[] arr,int target){
        int left = 0, right = arr.length;
        while(left < right){
            int mid = (left + right) / 2;
            if(arr[mid] <= target){
                left = mid+1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }
}

// Time Complexity: O(n log n) where n is the number of trees, due to the binary search operations.
// Space Complexity: O(n) for the prefix sum and indices arrays.