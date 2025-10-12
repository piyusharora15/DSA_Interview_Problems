// Problem Link: https://www.geeksforgeeks.org/problems/add-1-to-a-number-represented-as-linked-list/1?page=1&category=Linked%20List&sortBy=submissions

// Approach 1: Using Recursion.
// The idea is to recursively traverse to the end of the linked list and add 1 to the last node. If there is a carry, propagate it back through the recursion stack.
// If there is still a carry after processing the head node, we need to create a new node at the front of the list.
// Time Complexity: O(N)
// Space Complexity: O(N) due to recursion stack.

// Code:
class Solution {
    public Node addOne(Node head) {
        int carry = addWithCarry(head);
        if(carry > 0){
            Node newNode = new Node(carry);
            newNode.next = head;
            return newNode;
        }
        return head;
    }
    public int addWithCarry(Node head){
        if(head == null){
            return 1;
        }
        int res = head.data + addWithCarry(head.next);
        head.data = res % 10;
        return res/10;
    }
}

// Approach 2: Reversing the Linked List.
// The idea is to reverse the linked list, add 1 to the head node, and handle any carry that results. After processing, reverse the list back to its original order.
// Time Complexity: O(N)
// Space Complexity: O(1)

// Code:
class Solution {
    public Node addOne(Node head) {
        head = reverse(head);
        head = addOne1(head);
        return reverse(head);
    }
    public Node reverse(Node head){
        Node curr = head, prev = null, next;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public Node addOne1(Node head){
        Node res = head;
        Node curr = head;
        Node last = null;
        int carry = 1;
        int sum;
        while(curr != null){
            sum = carry + curr.data;
            carry = (sum >= 10) ? 1 : 0;
            curr.data = sum % 10;
            last = curr;
            curr = curr.next;
        }
        if(carry > 0){
            last.next = new Node(carry);
        }
        return res;
    }
}