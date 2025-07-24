// Problem Link : https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up

// Approach : If the same card appears more than once, we can calculate the number of cards (including both occurrences) between those duplicates. To find the minimum such distance, we track the last seen index of each card.

/* 
-> Use a HashMap<Integer, Integer> to store the last index where each card was seen.
-> Iterate through the cards array:
-> If the current card has been seen before, calculate the distance between this index and the previous index (i - prevIndex + 1).
-> Update the minimum distance accordingly.
-> If no duplicate is found, return -1.
*/

// Time Complexity : O(n) ; each card is processed once.
// Space Complexity : O(n) ; for storing last seen positions in the map.

class Solution {
    public int minimumCardPickup(int[] cards) {
        int minlen = Integer.MAX_VALUE;
        HashMap<Integer,Integer> mpp = new HashMap<>();
        for(int i=0;i<cards.length;i++){
            if(mpp.containsKey(cards[i])){
                int len = i - mpp.get(cards[i]) + 1;
                minlen = Math.min(minlen,len);
            }
            mpp.put(cards[i],i);
        }
        return minlen == Integer.MAX_VALUE ? -1 : minlen;
    }
}