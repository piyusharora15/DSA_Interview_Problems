// Problem Link : https://leetcode.com/problems/max-points-on-a-line

// Brute Force Approach:
/*
Iterate through all pairs of points and calculate the slope between them. If the slope is the same for a third point, it lies on the same line. Count the maximum number of points that share the same slope with respect to a base point.
 */
class Solution {

    public int maxPoints(int[][] points) {
        int n = points.length;
        int result = 0;
        if (n == 1) {
            return 1;
        }
        for (int i = 0; i < n; i++) {                // P1(x1,y1)
            for (int j = i + 1; j < n; j++) {          // P2(x2,y2)
                int count = 2;
                int dx = points[j][0] - points[i][0];  // (x2 - x1)
                int dy = points[j][1] - points[i][1];  // (y2 - y1)
                /*
                dy/dx == dy_/dx_
                dx * dy_ == dx_ * dy
                 */
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) {
                        int dx_ = points[k][0] - points[i][0];  // (x3 - x1)
                        int dy_ = points[k][1] - points[i][1];  // (y3 - y1)
                        if (dx_ * dy == dy_ * dx) {
                            count++;
                        }
                    }
                }
                result = Math.max(result, count);
            }
        }
        return result;
    }
}
// Time Complexity: O(n^3)  ; TLE for large inputs (e.g., n = 10⁴)
// Space Complexity: O(1)

// Optimal Approach: Using HashMap to Count Slopes.
// This approach uses a HashMap to count the number of points that share the same slope with respect to a base point. It calculates the slope as a reduced fraction to avoid precision issues with floating-point arithmetic.

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if(n <= 2)
        return n;
        int result = 0;
        for(int i=0;i<n;i++){
            Map<String,Integer> mp = new HashMap<>();
            int overlap = 0;  // To count points that are same as i
            int vertical = 0; // For vertical lines
            int localMax = 0;
            for(int j=0;j<n;j++){
                if(i == j) continue;
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                if(dx == 0 && dy == 0){
                    overlap++;   // Same point
                }
                else if(dx == 0){
                    vertical++;   // Vertical Line(infinite slope)
                }
                else{
                    int gcd = gcd(dy,dx);  //General slope, normalized by GCD
                    dy = dy/gcd;
                    dx = dx/gcd;
                    if(dx < 0){  //handle negative signs uniformly
                        dx = -dx;
                        dy = -dy;
                    }
                    String key = dy + "_" + dx;
                    mp.put(key,mp.getOrDefault(key,0) + 1);
                    localMax = Math.max(localMax,mp.get(key));
                }
                localMax = Math.max(localMax,vertical);
            }
            result = Math.max(result,localMax+overlap+1);
        }
        return result;
    }
    private int gcd(int a,int b){
        if(b == 0) return a;
        return gcd(b,a%b);
    }
}

// Time Complexity: O(n^2)  ;
// Space Complexity: O(n) for the HashMap used to store slopes.