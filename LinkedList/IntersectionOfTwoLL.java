// Problem Link: https://leetcode.com/problems/intersection-of-two-linked-lists?envType=problem-list-v2&envId=linked-list

// Brute Force Approach: We can use two nested loops to compare each node of the first linked list with every node of the second linked list. If we find a match, we return that node as the intersection point. This approach has a time complexity of O(m*n), where m and n are the lengths of the two linked lists.

// Code:
public class IntersectionOfTwoLL {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        while(headB != null){
            ListNode temp = headA;
            while(temp != null){
                if(temp == headB) return headB;
                temp = temp.next;
            }
            headB = headB.next;
        }
        return null;
    }
}

// Approach 2: Using HashSet.
// We can use a HashSet to store the nodes of the first linked list. Then, we traverse the second linked list and check if any node is present in the HashSet. If we find a match, we return that node as the intersection point. This approach has a time complexity of O(m+n) and a space complexity of O(m), where m and n are the lengths of the two linked lists.

// Code:
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> st = new HashSet<>();
        while(headA != null){
            st.add(headA);
            headA = headA.next;
        }
        while(headB != null){
            if(st.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }
}

// Optimal Approach: Take two pointers, one for each linked list. Move both pointers one step at a time. When a pointer reaches the end of its linked list, redirect it to the head of the other linked list. If there is an intersection, the pointers will meet at the intersection node after at most 2 passes through the lists. If there is no intersection, both pointers will eventually become null at the same time.

// Code:
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        if(headA == null || headB == null) return null;
        while(temp1 != temp2){
            temp1 = (temp1 == null) ? headB : temp1.next;
            temp2 = (temp2 == null) ? headA : temp2.next;
        }
        return temp1;
    }
}

// Time Complexity: O(m+n), where m and n are the lengths of the two linked lists.
// Space Complexity: O(1)