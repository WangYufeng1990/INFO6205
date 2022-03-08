package Assignment6;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

    }

    //1.Path Sum III
    public int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, 1);
        int curSum = 0;
        return helper(root, prefix, curSum, targetSum);
    }

    public int helper(TreeNode root, Map<Integer, Integer> prefix, int curSum, int targetSum) {
        if (root == null) return 0;
        curSum += root.val;
        int res = prefix.getOrDefault(curSum - targetSum, 0);
        prefix.put(curSum, prefix.getOrDefault(curSum, 0) + 1);

        res += (helper(root.left, prefix, curSum, targetSum) + helper(root.right, prefix, curSum, targetSum));
        prefix.put(curSum, prefix.get(curSum) - 1);
        return res;
    }

    //2.Lowest Common Ancestor of a Binary Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        if (root.val == p.val || root.val == q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        else if (left == null) return right;
        else if (right == null) return left;
        return null;
    }

    //3.Longest Univalue Path
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        int[] count = new int[1];
        helper(root, count, root.val);
        return count[0];

    }

    private int helper(TreeNode root, int[] count, int val) {
        if (root == null) return 0;
        int left = helper(root.left, count, root.val);
        int right = helper(root.right, count, root.val);
        count[0] = Math.max(count[0], left + right);
        if (root.val == val) return Math.max(left, right) + 1;
        return 0;
    }

    //4.Serialize and Deserialize Binary Tree
    public String serialize(TreeNode root) {
        return serializeHelper(root, new StringBuilder()).toString();
    }

    private StringBuilder serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) sb.append("#").append(",");
        else {
            sb.append(root.val).append(",");
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);
        }
        return sb;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return deserializeHelper(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    private TreeNode deserializeHelper(Queue<String> string) {
        String temp = string.poll();
        if (temp.equals("#")) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(temp));
            node.left = deserializeHelper(string);
            node.right = deserializeHelper(string);
            return node;
        }
    }

    //5.Vertical Order Traversal of a Binary Tree
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<int[]> list = new ArrayList<>();
        helper(root, 0, 0, list);
        Collections.sort(list, new Comparator<int[]>() {
            public int compare(int[] list1, int[] list2) {
                if (list1[1] != list2[1]) return list1[1] - list2[1];
                else if (list1[0] != list2[0]) return list1[0] - list2[0];
                else return list1[2] - list2[2];
            }
        });

        List<List<Integer>> res = new ArrayList<>();
        int size = 0;
        int lastCol = Integer.MIN_VALUE;
        for (int[] l : list) {
            int row = l[0], col = l[1], val = l[2];
            if (col != lastCol) {
                lastCol = col;
                res.add(new ArrayList<>());
                size++;
            }
            res.get(size - 1).add(val);
        }
        return res;
    }

    private void helper(TreeNode root, int row, int col, List<int[]> list) {
        if (root == null) return;
        list.add(new int[]{row, col, root.val});
        helper(root.left, row + 1, col - 1, list);
        helper(root.right, row + 1, col + 1, list);
    }

    //6.Count Complete Tree Nodes
    public int countNodes(TreeNode root) {
        int left = leftDepth(root);
        int right = rightDepth(root);
        if (left == right) {
            return (1 << left) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    private int leftDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            depth++;
            root = root.left;
        }
        return depth;
    }

    private int rightDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            depth++;
            root = root.right;
        }
        return depth;
    }

    //7.Sum Root to Leaf Numbers
    public int sumNumbers(TreeNode root) {
        int[] res = new int[1];
        helper(root, 0, res);
        return res[0];
    }

    private void helper(TreeNode root, int curSum, int[] res) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res[0] += curSum * 10 + root.val;
            return;
        }
        ;
        curSum = curSum * 10 + root.val;
        helper(root.left, curSum, res);
        helper(root.right, curSum, res);
    }

    //8.Delete Leaves With a Given Value
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return root;
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null && root.val == target) {
            root = null;
        }
        return root;
    }
}
