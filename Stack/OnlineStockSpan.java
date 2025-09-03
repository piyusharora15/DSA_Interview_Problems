// Problem Link : https://leetcode.com/problems/online-stock-span

// Brute Force Approach : Store all previous prices. For each new price, look backward one day at a time, until we encounter a price greater than today's price.

// Code : 
class StockSpanner {
    private List<Integer> prices;
    public StockSpanner() {
        prices = new ArrayList<>();
    }
    
    public int next(int price) {
        prices.add(price);
        int span = 1;
        for(int i=prices.size()-2;i>=0;i--){
            if(prices.get(i) <= price) span++;
            else break;
        }
        return span;
    }
}

// Time Complexity: O(N) per call in the worst case, O(N^2) overall.
// Space Complexity: O(N) for storing prices.

// Optimal Approach : Monotonic Stack.
// Use a stack of (price, span) pairs, maintaining monotonically decreasing prices. When a new price comes in, pop all prices ≤ it and accumulate their spans. The sum plus 1 (today’s count) gets pushed.

// Code :
class StockSpanner {
    private Deque<int[]> stack;
    public StockSpanner() {
        stack = new ArrayDeque<>();
    }
    
    public int next(int price) {
        int span = 1;
        while(!stack.isEmpty() && stack.peek()[0] <= price){
            span += stack.pop()[1];
        }
        stack.push(new int[]{price,span});
        return span;
    }
}

// Time Complexity: O(N) per call in the worst case, O(N) overall.
// Space Complexity: O(N) for storing (price, span) pairs.