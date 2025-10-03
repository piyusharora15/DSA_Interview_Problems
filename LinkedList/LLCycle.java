// Problem Link: https://leetcode.com/problems/linked-list-cycle

// Brute Force Approach: We can use a HashMap to store the visited nodes. If we encounter a node that is already in the HashMap, then there is a cycle in the linked list. The time complexity of this approach is O(n) and the space complexity is O(n).

// Code:
public class LLCycle {
    public boolean hasCycle(ListNode head) {
        ListNode temp = head;
        Map<ListNode,Integer> nodeMap = new HashMap<>();
        while(temp != null){
            if(nodeMap.containsKey(temp)){
                return true;
            }
            nodeMap.put(temp,1);
            temp = temp.next;
        }
        return false;
    }
}

// Optimal Approach: We can use two pointers, one slow and one fast. The slow pointer moves one step at a time while the fast pointer moves two steps at a time. If there is a cycle in the linked list, then the fast pointer will eventually meet the slow pointer. The time complexity of this approach is O(n) and the space complexity is O(1).

// Code:
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)