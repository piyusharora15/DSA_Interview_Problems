// Problem Link: https://leetcode.com/problems/top-k-frequent-words

// Approach 1: HashMap + sort entries.
// Count frequencies with a HashMap, collect unique words into a list, then sort the list with a comparator that (1) compares frequency descending, (2) for ties compares words lexicographically ascending. Return the first k.

// Code:
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> freq = new HashMap<>();
        for(String w : words) freq.put(w,freq.getOrDefault(w,0) + 1);
        List<String> unique = new ArrayList<>(freq.keySet());
        Collections.sort(unique,(a,b) -> {
            int f1 = freq.get(a), f2 = freq.get(b);
            if(f1 != f2) return Integer.compare(f2,f1);
            return a.compareTo(b);
        });
        return unique.subList(0,k);
    }
}

// Time Complexity: O(N log N) in worst case, where N is number of words. This occurs when all words are unique and we have to sort them all.
// Space Complexity: O(N) for the frequency map and unique list.

// Approach 2: HashMap + Min-Heap (Priority Queue).
// Count frequencies. Use a min-heap (priority queue) of at most k elements where the comparator orders by: frequency ascending (so smallest freq at top), and for equal frequency: lexicographically descending (so lexicographically larger word is considered “smaller” and evicted first). Process each unique word: push into heap, if heap size > k then poll() (remove worst of current top-k). At the end, the heap contains the top k words; pop to build answer in reverse order.

// Code:
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> freq = new HashMap<>();
        for(String w : words) freq.put(w,freq.getOrDefault(w,0) + 1);
        PriorityQueue<Map.Entry<String,Integer>> pq = new PriorityQueue<>((a,b) -> {
            if(!a.getValue().equals(b.getValue()))
            return a.getValue() - b.getValue();
            else
            return b.getKey().compareTo(a.getKey());
        });
        for(Map.Entry<String,Integer> e : freq.entrySet()){
            pq.offer(e);
            if(pq.size() > k) pq.poll();
        }
        LinkedList<String> res = new LinkedList<>();
        while(!pq.isEmpty()) res.addFirst(pq.poll().getKey());
        return res;
    }
}

// Time Complexity: O(N log k) where N is number of words, since we do O(log k) operations for each of the N unique words.
// Space Complexity: O(N) for frequency map, O(k) for heap.

// Optimal Approach: HashMap + Bucket Sort.
// Count frequencies, then use an array of buckets List<String>[] buckets of size n+1 where index f holds all words with frequency f. Walk buckets from high frequency to low; for each bucket sort words lexicographically ascending (because ties must be lexicographically smaller first) and take words until you have k.

// Code:
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
      int n = words.length;
      Map<String,Integer> freq = new HashMap<>();
      for(String w : words) freq.put(w,freq.getOrDefault(w,0) + 1);
      List<String>[] buckets = new List[n+1];
      for(String w : freq.keySet()){
        int f = freq.get(w);
        if(buckets[f] == null) buckets[f] = new ArrayList<>();
        buckets[f].add(w);
      }  
      List<String> res = new ArrayList<>();
      for(int f=n;f>=1 && res.size() < k;f--){
        if(buckets[f] == null) continue;
        List<String> bucket = buckets[f];
        Collections.sort(bucket);
        for(String w: bucket){
            res.add(w);
            if(res.size() == k) break;
        }
      }
      return res;
    }
}

// Time Complexity: O(N) to count frequencies + O(N) to fill buckets + O(N log M) to sort words in buckets where M is max number of words in any bucket. In worst case M can be N (all words same frequency), so O(N log N) worst case.
// Space Complexity: O(N) for frequency map and buckets.