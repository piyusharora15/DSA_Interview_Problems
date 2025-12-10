// Problem Link: https://leetcode.com/problems/zigzag-conversion?envType=problem-list-v2&envId=string

// Approach: Simulation of the zigzag pattern by iterating through the characters and placing them in the appropriate rows.
// We use a StringBuilder array to store characters for each row and then concatenate them at the end. We maintain a direction flag to determine whether we are moving down or up the rows. If numRows is 1, we simply return the original string as there is no zigzag pattern. If numRows is greater than or equal to the length of the string, we also return the original string since it will fit in a single row. Iterates through each character of the string, appending it to the current row's StringBuilder. When we reach the top or bottom row, we reverse the direction. Finally, we concatenate all rows to form the final result.

// Code:
class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1 || numRows >= s.length()) return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for(int i=0;i<numRows;i++){
            rows[i] = new StringBuilder();
        }
        int currRow = 0;
        boolean goingDown = false;
        for(char c:s.toCharArray()){
            rows[currRow].append(c);
            if(currRow == 0 || currRow == numRows - 1){
                goingDown = !goingDown;
            }
            currRow += goingDown ? 1 : -1;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows){
            res.append(row);
        }
        return res.toString();
    }
}

// Time Complexity: O(n), where n is the length of the input string s. We iterate through each character of the string once.
// Space Complexity: O(n), as we are using additional space to store the characters in the StringBuilder array for each row.