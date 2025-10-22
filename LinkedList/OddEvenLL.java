// Problem Link: https://leetcode.com/problems/odd-even-linked-list?envType=problem-list-v2&envId=linked-list

// Approach 1: Data Rearrangement.
// Initialize a pointer 'temp' to the head of the LL.Move it by 2 places in each iteration until it reaches null.(Odd positioned nodes). Now place 'temp' at head.next and move it by 2 places in each iteration until it reaches null.(Even positioned nodes).Simultaneously keep placing the data in a list in the same order. Now place 'temp' at head and keep another pointer 'i' at 0th index of the list.Now replace the values in the LL by the values in the list one by one until 'i' reaches the size of the list.Return head of the modified LL.

// Code:
import java.util.ArrayList;

public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        ArrayList<Integer> list = new ArrayList<>();
        ListNode temp = head;

        // Traverse odd positioned nodes
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
            if (temp != null) {
                temp = temp.next;
            }
        }

        // Traverse even positioned nodes
        temp = head.next;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
            if (temp != null) {
                temp = temp.next;
            }
        }

        // Replace values in the original list
        temp = head;
        int i = 0;
        while (temp != null) {
            temp.val = list.get(i++);
            temp = temp.next;
        }

        return head;
    }
}
// Time Complexity: O(N) where N is the number of nodes in the linked list.
// Space Complexity: O(N) for storing the values in the list.

// Approach 2: Pointer Manipulation.
// If head is null or head.next is null, return head.Initialize two pointers 'odd' and 'even' to head and head.next respectively.Initialize another pointer 'evenHead' to head.next to keep track of the start of even positioned nodes.Traverse the LL until 'even' and 'even.next' are not null.In each iteration, link 'odd.next' to 'even.next', move 'odd' to 'odd.next', link 'even.next' to 'odd.next', and move 'even' to 'even.next'.After the loop, link 'odd.next' to 'evenHead'.Return head of the modified LL.

// Code:
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode ehead = even;
        while(even != null && even.next != null){
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = ehead;
        return head;
    }
}

// Time Complexity: O(N) where N is the number of nodes in the linked list.
// Space Complexity: O(1) as we are rearranging the pointers without using extra space.