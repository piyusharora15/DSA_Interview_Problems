// Problem Link -> https://leetcode.com/problems/remove-sub-folders-from-the-filesystem

// Approach 1: Set based Subfolder Removal.
/*
-> Store all folders in a Set.For each folder,check if any parent directory is in the set, if it exists then the current folder is considered a sub-folder. If no parent is found, the folder is added to the result list. 
*/
// Time Complexity: O(n * l^2) ; where n is the number of folders and l is the length of the folder path.
// Space Complexity: O(n) ; where n is the size of the Set.

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Set<String> folderSet = new HashSet<>(Arrays.asList(folder));
        List<String> result = new ArrayList<>();
        for(String currFolder : folder){
            boolean isSubFolder = false;
            String tempFolder = currFolder;
            while(!currFolder.isEmpty()){
                int position = currFolder.lastIndexOf('/');
                if(position == -1)
                break;
                currFolder = currFolder.substring(0,position);
                if(folderSet.contains(currFolder)){
                    isSubFolder = true;
                    break;
                }
            }
            if(!isSubFolder){
                result.add(tempFolder);
            }
        }
        return result;
    }
}

// Approach 2: Sorting-Based Subfolder Removal
/*
-> This approach sorts the folders lexicographically, ensuring that sub-folders come right after their parent directories. It then checks each folder to see if it starts with the last added folder (plus a / to differentiate between folders and sub-folders).

-> Sort the folders lexicographically.
-> Compare each folder with the last added folder to see if it is a sub-folder (using startsWith()).
-> Only add folders to the result list if they are not sub-folders.
*/

// Time Complexity : O(n*(log n) + n * l) ; where n is the number of folders and l is the folder path length. n*log(n) is needed for sorting and (n*l) is needed for traversing through each folder.
// Space Complexity : O(n) ; needed for the result list.

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder); //Sort the folder array lexicographically.
        List<String> result = new ArrayList<>();
        result.add(folder[0]); // The first folder can never be a sub-folder after sorting.
        for(int i=1;i<folder.length;i++){
            String currFolder = folder[i];
            String lastFolder = result.get(result.size() - 1);
            lastFolder += "/";  //Add '/' to the last folder to check for sub-folder.
            //If the current folder does not start with the last folder, it is not a sub-folder.
            if(!currFolder.startsWith(lastFolder)){
                result.add(currFolder);
            }
        }
        return result;
    }
}

// Approach 3: Trie based Subset Removal:
/*
We insert all the folder paths into a Trie. While inserting, if we find that a folder already exists as a parent, we stop and mark it as terminal. This helps us avoid inserting sub-folders under it. Then, we collect all terminal nodes (non-subfolders) from the Trie.
*/
// Time Complexity : Insertion: O(n * l), where n = number of folders, l = avg path length ; DFS Traversal: O(n * l) for collecting final result -> Total: O(n * l)

// Space Complexity : Trie stores all characters from each path: O(n * l) ; Result list: O(n)

class Solution {
    // TrieNode definition
    static class TrieNode {
        Map<String, TrieNode> children = new HashMap<>(); // Map for child folders
        boolean isEnd = false; // Marks the end of a complete valid folder
    }
    public List<String> removeSubfolders(String[] folder) {
         // Step 1: Build the Trie
        TrieNode root = new TrieNode();
        for (String path : folder) {
            String[] parts = path.split("/"); // Split path into parts (e.g. "/a/b" => ["", "a", "b"])
            TrieNode node = root;
            boolean stopInsert = false;

            for (int i = 1; i < parts.length; i++) { // start from 1 because parts[0] is empty due to leading '/'
                if (node.isEnd) {
                    // If we already hit a folder that is marked as a valid folder (parent folder), stop inserting further
                    stopInsert = true;
                    break;
                }
                node.children.putIfAbsent(parts[i], new TrieNode());
                node = node.children.get(parts[i]);
            }
            if (!stopInsert) {
                node.isEnd = true; // Mark this path as a valid folder (not a sub-folder)
                node.children.clear(); // Clear children to avoid sub-folders under it
            }
        }

        // Step 2: DFS traversal to collect the valid folders
        List<String> result = new ArrayList<>();
        dfs(root, new StringBuilder(), result);
        return result;
    }

    // DFS function to collect paths from Trie
    private void dfs(TrieNode node, StringBuilder path, List<String> result) {
        if (node.isEnd) {
            result.add(path.toString()); // If it's an end folder, add the path to result
            return; // Don't go deeper into sub-folders
        }

        for (String folder : node.children.keySet()) {
            int len = path.length();
            path.append("/").append(folder); // Add folder name to the path
            dfs(node.children.get(folder), path, result); // Recursive call to go deeper
            path.setLength(len); // Backtrack to previous path
        }
    }
}