package FinalTerm;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String a = "aabcccccaaaZZZ";
        Solution test = new Solution();
        System.out.println(test.compressString(a));

        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(test.numIslands(grid));
        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        System.out.println(test.groupAnagrams(strs));



    }

    //Question1
    public String compressString(String str) {
        if (str == null || str.length() == 0) return str;
        StringBuilder sb = new StringBuilder();
        char[] array = str.toCharArray();
        sb.append(array[0]);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (array[i] == array[i-1]) {
                count++;
            }
            else {
                sb.append(count);
                sb.append(array[i]);
                count = 1;
            }
        }
        sb.append(count);
        String res = sb.toString();
        if (str.length() <= res.length()) {
            return str;
        }
        else return res;
    }

    //Question2
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    helper(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void helper(char[][] grid, int r, int c) {
        int row = grid.length, col = grid[0].length;
        if (r < 0 || r >= row || c < 0 || c >= col || grid[r][c] == '0' || grid[r][c] == 'A') {
            return;
        }
        grid[r][c] = 'A';
        helper(grid, r-1, c);
        helper(grid, r+1, c);
        helper(grid, r, c-1);
        helper(grid, r, c+1);
    }

    //Question3
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());

    };

    //Question4
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        if (root.val == p.val || root.val == q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        else if (left == null) return right;
        else return left;
    }
}
