// Problem Link : https://leetcode.com/problems/maximum-score-from-removing-substrings?envType=daily-question&envId=2025-07-23

// Approach 1 : Stack + Greedy.
/* 
Decide which substring gives higher score: Let’s say x > y, then we remove "ab" first. Else we remove "ba" first.
Remove substrings using stack: Traverse the string once.
-> If a match is found with the target substring, pop it from the stack and increase the score.
-> Else, push the character onto the stack.
After the first pass, you’ll be left with a reduced string.
Run again on this string to remove the second substring and add to score.
*/

class Solution {
    public int maximumGain(String s, int x, int y) {
        int n = s.length();
        int score = 0;
        String maxStr = (x > y) ? "ab" : "ba";
        String minStr;
        if(maxStr.equals("ab")){
            minStr = "ba";
        }
        else{
            minStr = "ab";
        }
        String tempFirst = removeSubstring(s,maxStr);  // First Pass
        int removedPairsCount = (n-tempFirst.length()) / 2;
        score += removedPairsCount * Math.max(x,y);
        String tempSecond = removeSubstring(tempFirst,minStr);  // Second pass
        removedPairsCount = (tempFirst.length() - tempSecond.length()) / 2;
        score += removedPairsCount * Math.min(x,y);
        return score;
    }
    private String removeSubstring(String s,String matchStr){
        Stack<Character> stack = new Stack<>();
        for(char ch: s.toCharArray()){
            if(!stack.isEmpty() && ch == matchStr.charAt(1) && stack.peek() == matchStr.charAt(0)){
                stack.pop();
            }
            else{
                stack.push(ch);
            }
        }
        StringBuilder remainStr = new StringBuilder();
        while(!stack.isEmpty()){
            remainStr.append(stack.pop());
        }
        return remainStr.reverse().toString();
    }
}

// Time Complexity : O(n) for each pass, where n is the length of the string.
// Space Complexity: O(n) for the stack used to store characters.