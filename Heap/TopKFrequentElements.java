// Problem Link : https://leetcode.com/problems/top-k-frequent-elements

// Approach 1: HashMap + Sort entries:

// Count frequencies, convert map entries to a list, sort entries by frequency descending, take first k.

// Code:
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> freq = new HashMap<>();
        for(int x : nums) freq.put(x,freq.getOrDefault(x,0) + 1);
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort((a,b) -> b.getValue() - a.getValue());
        int[] res = new int[k];
        for(int i=0;i<k;i++) res[i] = list.get(i).getKey();
        return res;
    }
}

// Time complexity: O(n log n) due to sorting
// Space complexity: O(n)

// Approach 2: HashMap + Min-Heap (Priority Queue):
// Count frequencies. Maintain a min-heap of size k keyed by frequency. Iterate entries; push each into heap and if heap size > k, pop the smallest frequency. After processing, heap contains k most frequent; root is the kth.

// Code:
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> freq = new HashMap<>();
        for(int x : nums) freq.put(x,freq.getOrDefault(x,0) + 1);
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for(Map.Entry<Integer,Integer> entry: freq.entrySet()){
            pq.offer(entry);
            if(pq.size() > k) pq.poll();
        }
        int[] res = new int[k];
        for(int i=k-1;i>=0;i--){
            res[i] = pq.poll().getKey();
        }
        return res;
    }
}

// Time complexity: O(n log k) due to heap operations
// Space complexity: O(n)

// Optimal Approach: HashMap + Bucket Sort:
// Count frequencies with a HashMap. Then use a bucket array where index = frequency and bucket[index] stores all numbers with that frequency. Since max frequency ≤ n, bucket array size = n+1. Scan buckets from high freq → low freq and collect top k.

// Code:
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        Map<Integer,Integer> freq = new HashMap<>();
        for(int x : nums) freq.put(x,freq.getOrDefault(x,0) + 1);
        List<Integer>[] buckets = new List[n+1];
        for(int key : freq.keySet()){
            int f = freq.get(key);
            if(buckets[f] == null) buckets[f] = new ArrayList<>();
            buckets[f].add(key);
        }
        int[] res = new int[k];
        int idx = 0;
        for(int i=n;i>=0 && idx<k;i--){
            if(buckets[i] != null){
                for(int num : buckets[i]){
                    res[idx++] = num;
                    if(idx == k) break;
                }
            }
        }
        return res;
    }
}

// Time complexity: O(n)
// Space complexity: O(n)