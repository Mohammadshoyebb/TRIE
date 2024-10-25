/**
 * 1233. Remove Sub-Folders from the Filesystem
 * 
 * Given a list of folders, return the folders after removing all sub-folders in those folders.
 * A folder[i] is considered a sub-folder of folder[j] if it starts with folder[j], followed by a "/".
 * 
 * Example 1:
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * 
 * Example 2:
 * Input: folder = ["/a","/a/b/c","/a/b/d"]
 * Output: ["/a"]
 * 
 * Example 3:
 * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 * 
 * Constraints:
 * 1 <= folder.length <= 4 * 10^4
 * 2 <= folder[i].length <= 100
 * folder[i] contains only lowercase letters and '/'.
 * folder[i] always starts with the character '/'.
 * Each folder name is unique.
 */

 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.List;
 
 public class RemoveNestedFolders {
     public List<String> removeNestedSubfolders(String[] directories) {
         FolderTrie folderTrie = new FolderTrie();
         Arrays.sort(directories, (a, b) -> a.length() - b.length());
         List<String> filteredDirectories = new ArrayList<>();
         
         for (String directory : directories) {
             if (!folderTrie.existsInTrie(directory)) {
                 filteredDirectories.add(directory);
                 folderTrie.addPath(directory);
             }
         }
         return filteredDirectories;
     }
 
     // Trie structure to detect sub-folders
     class FolderTrie {
         FolderNode root;
         
         public FolderTrie() {
             root = new FolderNode();
         }
         
         public void addPath(String path) {
             FolderNode currentNode = root;
             for (String section : path.split("/")) {
                 if (section.isEmpty()) continue;
                 currentNode.children.putIfAbsent(section, new FolderNode());
                 currentNode = currentNode.children.get(section);
             }
             currentNode.isCompleteFolder = true;
         }
         
         public boolean existsInTrie(String path) {
             FolderNode currentNode = root;
             for (String section : path.split("/")) {
                 if (section.isEmpty()) continue;
                 currentNode = currentNode.children.get(section);
                 if (currentNode == null) return false;
                 if (currentNode.isCompleteFolder) return true;
             }
             return false;
         }
     }
 
     // Node class for each folder segment
     class FolderNode {
         HashMap<String, FolderNode> children;
         boolean isCompleteFolder;
 
         public FolderNode() {
             children = new HashMap<>();
             isCompleteFolder = false;
         }
     }
 
     public static void main(String[] args) {
         RemoveNestedFolders solution = new RemoveNestedFolders();
         String[] directories = {"/a","/a/b","/c/d","/c/d/e","/c/f"};
         List<String> result = solution.removeNestedSubfolders(directories);
         System.out.println(result);  // Output: ["/a","/c/d","/c/f"]
     }
 }
 
 
//==============================================================================================================
 /*
  

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        // Sort the folders lexicographically so parent folders come before their subfolders
        Arrays.sort(folder);
        
        // Initialize result list with the first folder
        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);
        
        // Iterate through remaining folders starting from index 1
        for (int i = 1; i < folder.length; i++) {
            // Get the last added folder path and add a trailing slash
            String lastFolder = ans.get(ans.size() - 1) + "/";
            
            // Check if current folder starts with lastFolder
            // If it doesn't start with lastFolder, then it's not a subfolder
            if (!folder[i].startsWith(lastFolder)) {
                ans.add(folder[i]);
            }
        }
        
        return ans;
    }
}


  */




//==========================================================================================================



/*
 

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        // In ASCII, '/' is before 'a': e.g., '/a', '/a/b', '/aa'
        // After sorting the folder array, we only need to consider if the current folder is a subfolder of the previous one or not.
        Arrays.sort(folder);
        
        List<String> result = new ArrayList<>();
        
        for (String dir : folder)
            if (result.isEmpty() || !dir.startsWith(result.get(result.size()-1) + "/"))
                result.add(dir);
                
        return result;
    }
}


 */