// Problem Link: https://leetcode.com/problems/count-and-say?envType=problem-list-v2&envId=string

// Approach: Iterative String Construction.
// We start with the base case "1" and iteratively build the next sequence by counting consecutive digits in the current sequence. We use a StringBuilder to efficiently construct the next sequence. Then, we repeat this process until we reach the nth sequence.

// Code:
class CountAndSay {
    public String countAndSay(int n) {
        String term = "1";
        for(int i=2;i<=n;i++){
            term = getNextTerm(term);
        }
        return term;
    }
    private String getNextTerm(String s){
        StringBuilder result = new StringBuilder();
        int count = 1;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i) == s.charAt(i-1)){
                count++;
            }
            else{
                result.append(count);
                result.append(s.charAt(i-1));
                count = 1;
            }
        }
        result.append(count);
        result.append(s.charAt(s.length()-1));
        return result.toString();
    }
}

// Time Complexity: O(m * n), where m is the average length of the sequence and n is the number of terms.
// Space Complexity: O(m), where m is the length of the sequence being constructed.