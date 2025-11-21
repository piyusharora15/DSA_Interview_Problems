// Problem Link: https://leetcode.com/problems/letter-combinations-of-a-phone-number

// Approach: Backtracking
// We use a backtracking approach to generate all possible letter combinations for the given digit string. The mapping of digits to letters is stored in a HashMap. We recursively build combinations by appending letters corresponding to each digit and backtrack when we reach the end of the digit string.

// Code:
class Solution {
    List<String> result = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0)
        return result;
        Map<Character,String> mpp = new HashMap<>();
        mpp.put('2',"abc");
        mpp.put('3',"def");
        mpp.put('4',"ghi");
        mpp.put('5',"jkl");
        mpp.put('6',"mno");
        mpp.put('7',"pqrs");
        mpp.put('8',"tuv");
        mpp.put('9',"wxyz");
        solve(0,digits,new StringBuilder(),mpp);
        return result;
    }
    public void solve(int idx,String digits,StringBuilder temp,Map<Character,String> mpp){
        if(idx >= digits.length()){
            result.add(temp.toString());
            return;
        }
        char ch = digits.charAt(idx);
        String str = mpp.get(ch);
        for(int i=0;i<str.length();i++){
            temp.append(str.charAt(i));
            solve(idx+1,digits,temp,mpp);
            temp.deleteCharAt(temp.length()-1);
        }
    }
}

// Time Complexity: O(3^N * 4^M) where N is the number of digits that map to 3 letters and M is the number of digits that map to 4 letters.
// Space Complexity: O(N) for the recursion stack and temporary string storage.