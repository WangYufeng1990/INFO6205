package Assignment5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {

    }

    //1.Binary Tree Level Order Traversal II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> ans = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
                list.add(temp.val);
            }
            ans.add(0, list);
        }
        return ans;
    }

    //2.Find Leaves of Binary Tree
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    private int helper(TreeNode root, List<List<Integer>> res){
        if (root == null) return -1;
        int left = helper(root.left, res);
        int right = helper(root.right, res);
        int height = Math.max(left, right) + 1;
        if (res.size() == height) res.add(new ArrayList<>());
        res.get(height).add(root.val);
        root.left = null;
        root.right = null;
        return height;
    }

    //3.Maximum Width of Binary Tree
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        q1.offer(root);
        q2.offer(0);
        while (!q1.isEmpty()) {
            int start = q2.peek();
            int size = q1.size();
            int pos = 0;
            for (int i = 0; i < size; i++) {
                TreeNode temp = q1.poll();
                pos = q2.poll();
                if (temp.left != null) {
                    q1.offer(temp.left);
                    q2.offer(pos * 2);
                }
                if (temp.right != null) {
                    q1.offer(temp.right);
                    q2.offer(pos * 2 + 1);
                }
            }
            res = Math.max(res, pos - start + 1);
        }
        return res;
    }

    //4.Find Largest Value in Each Tree Row
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                max = Math.max(max, temp.val);
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
            res.add(max);
        }
        return res;
    }
}
