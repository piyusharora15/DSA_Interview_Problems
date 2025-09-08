// Problem Link: https://leetcode.com/problems/kth-largest-element-in-an-array

// Brute Force Approach: Sorting.
// Sort the array in ascending order and return the element at index n-k.

// Code:
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
}
 
// Time complexity: O(n log n)
// Space complexity: O(1)

// Approach 2: Using Min Heap:
// Maintain a min-heap of size k.
// Add elements to the heap. If the size exceeds k, remove the smallest element.

// Code:
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int x : nums){
            pq.offer(x);
            if(pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
}

// Time complexity: O(n log k)
// Space complexity: O(k)

// Optimal Approach: Quickselect Algorithm
// Similar to QuickSort, we partition the array and recursively search in the relevant partition.
// Code:

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int L = 0;
        int R = n - 1;
        int pivot_idx = 0;
        while (true) {
            pivot_idx = partition(nums, L, R);
            if (pivot_idx == k - 1) {
                break;
            } else if (pivot_idx > k - 1) {
                R = pivot_idx - 1;
            } else {
                L = pivot_idx + 1;
            }
        }
        return nums[pivot_idx];
    }

    public int partition(int[] nums, int L, int R) {
        int P = nums[L];
        int i = L + 1;
        int j = R;
        while (i <= j) {
            if (nums[i] < P && nums[j] > P) {
                swap(nums, i, j);
                i++;
                j--;
            }
            if (nums[i] >= P) {
                i++;
            }
            if (nums[j] <= P) {
                j--;
            }
        }
        swap(nums, L, j);
        return j;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}

// Time complexity: O(n) on average, O(n^2) in the worst case
// Space complexity: O(1)


