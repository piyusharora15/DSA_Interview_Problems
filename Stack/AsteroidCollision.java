// Problem Link : https://leetcode.com/problems/asteroid-collision

// Brute Force Approach: Simulate collisions directly by resolving adjacent opposite-direction pairs repeatedly until no collisions remain. This is straightforward but inefficient in the worst case because each resolution may force scanning/backtracking across survivors multiple times.

// Code:
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> survivors = new ArrayList<>();
        for(int a : asteroids){
            boolean alive = true;
            while(alive && !survivors.isEmpty() && survivors.get(survivors.size()-1) > 0 && a < 0){
                int last = survivors.get(survivors.size()-1);
                if(Math.abs(a) > last){
                    survivors.remove(survivors.size()-1);
                }
                else if(Math.abs(a) == last){
                    survivors.remove(survivors.size()-1);
                    alive = false;
                }
                else {
                    alive = false;
                }
            }
            if(alive) survivors.add(a);
        }
        int[] res = new int[survivors.size()];
        for(int i=0;i<res.length;i++) res[i] = survivors.get(i);
        return res;
    }
}

// Time Complexity: O(n^2) in the worst case where each asteroid causes a chain reaction of collisions requiring multiple passes through the survivors list.
// Space Complexity: O(n) for the survivors list in the worst case where no collisions occur.

// Optimal Approach: 

/*
Traverse left→right and keep a stack of surviving asteroids. For each asteroid a:

If stack is empty or a moves right (positive) — push a.

If a moves left (negative) and stack.top > 0 (i.e., there's a right-moving asteroid that could collide), then simulate collisions:

While top is positive and |a| > top: pop top (top explodes) and keep checking.

If top == |a|: pop top and a is also destroyed (both explode).

If top > |a|: incoming a is destroyed; stop.

If after popping no blocking positive asteroid remains, push a (it survives).

This simulates chain reactions: a single left-moving asteroid may destroy multiple prior right-moving asteroids. Because every asteroid is pushed at most once and popped at most once, the total work is linear (amortized).
*/

// Code:
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
       Deque<Integer> st = new ArrayDeque<>();
       for(int a : asteroids){
        boolean alive = true;
        while(alive && a<0 && !st.isEmpty() && st.peekLast() > 0){
            int top = st.peekLast();
            if(Math.abs(a) > top){
                st.pollLast();
                continue;
            }
            else if(Math.abs(a) == top){
                st.pollLast();
                alive = false;
                break;
            }
            else {
                alive = false;
                break;
            }
        }
        if(alive) st.addLast(a);
       } 
       int n = st.size();
       int[] res = new int[n];
       for(int i=n-1;i>=0;i--) res[i] = st.pollLast();
       return res;
    }
}

// Time Complexity: O(n) because each asteroid is pushed and popped at most once.
// Space Complexity: O(n) for the stack in the worst case where no collisions occur.