// Problem Link: https://www.geeksforgeeks.org/problems/multiply-two-linked-lists/1?page=3&category=Linked%20List&sortBy=submissions

// Approach: To multiply two numbers represented by linked lists, you need to traverse each linked list from the head node to the end. For each linked list, initialize a variable to zero, which will be used to store the numerical value of the linked list.  By processing each node, start by adding the value of the first node to this variable. For each subsequent node, multiply the variable by 10 and then add the node's value to it. This approach effectively reconstructs the number represented by the linked list. To handle potential overflow and ensure that the computations remain within manageable bounds, use the modulo operation with 1e9 + 7 after each arithmetic operation. This will keep the intermediate results within a safe range and prevent overflow during multiplication.

// Code:
public class MultiplyTwoNumbersAsLL {
    static final int MOD = 1000000007;

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    static long multiplyTwoLists(Node l1, Node l2) {
        long num1 = 0, num2 = 0;

        // Convert first linked list to number
        while (l1 != null) {
            num1 = (num1 * 10 + l1.data) % MOD;
            l1 = l1.next;
        }

        // Convert second linked list to number
        while (l2 != null) {
            num2 = (num2 * 10 + l2.data) % MOD;
            l2 = l2.next;
        }

        // Return the product modulo MOD
        return (num1 * num2) % MOD;
    }

    public static void main(String[] args) {
        Node l1 = new Node(3);
        l1.next = new Node(4);
        l1.next.next = new Node(2); // Represents the number 342

        Node l2 = new Node(4);
        l2.next = new Node(6);
        l2.next.next = new Node(5); // Represents the number 465

        System.out.println("Product of two linked lists is: " + multiplyTwoLists(l1, l2));
    }
}
// Time Complexity: O(n + m), where n and m are the lengths of the two linked lists.
// Space Complexity: O(1)