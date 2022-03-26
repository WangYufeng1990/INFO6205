package MidTerm;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{0,1,3,50,75};
        Main main = new Main();
        System.out.println(main.missingNumber(nums, 0, 99));


    }

    //Question1
    public List<String> missingNumber(int[] nums, int lower, int upper) {
        int index = 0;
        List<String> res = new ArrayList<>();
        for (int i = lower; i < upper; i++) {
            if (nums[index] != i) {
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append("->");
                while (i+1 != nums[index+1]) {
                    i++;
                }
                sb.append(i);
                res.add(sb.toString());
            }
            else {
                index++;
            }
        }
        return res;
    }


    //Question2
    public ListNode addNode(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        int carry = 0;

        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            ListNode temp = new ListNode(sum % 10);
            head.next = temp;
            carry = sum/10;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int sum = l1.val + carry;
            ListNode temp = new ListNode(sum % 10);
            carry = sum / 10;
            head.next = temp;
            l1 = l1.next;
        }

        while (l2 != null) {
            int sum = l2.val + carry;
            ListNode temp = new ListNode(sum % 10);
            carry = sum / 10;
            head.next = temp;
            l2 = l2.next;
        }
        return dummy.next;
    }

    //Question3
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, preorder.length-1, 0);
    }



    public TreeNode helper(int[] preorder, int[] inorder, int start, int end, int preIndex) {
        if (start > end || preIndex > preorder.length) return null;
        TreeNode root = new TreeNode(preorder[preIndex]);
        int inorderIndex = map.get(preorder[preIndex]);
        TreeNode left = helper(preorder, inorder, start, inorderIndex-1, preIndex+1);
        TreeNode right = helper(preorder, inorder, inorderIndex+1, end, preIndex+1);
        return root;
    }

    //Question4
    public int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (v1, v2) -> (v1[0] - v2[0]));
        int index = -1;
        int[][] res = new int[intervals.length][2];
        for (int[] interval : intervals) {
            if (index == -1 || interval[0] > res[index][1]) res[++index] = interval;
            else res[index][1] = Math.max(res[index][1], interval[1]);
        }
        return Arrays.copyOf(res, index+1);

    }
}
 /*
 *
 *  public Node<T> getTreeFromPreOrderAndInOrder(T[] preOrder, T[] inOrder){
        Box<Integer> preIndex = new Box<>(0);

        return getTreeFromPreOrderAndInOrder(preOrder, inOrder, preIndex, 0, preOrder.length -1);
    }

    private Node<T> getTreeFromPreOrderAndInOrder(T[] preOrder, T[] inOrder, Box<Integer> preIndex, int start, int end){
        if(start > end || preIndex.data >= preOrder.length){
            return null;
        }

        Node<T> node = new Node<>(preOrder[preIndex.data]);
        preIndex.data ++;
       // System.out.println(node.data);
        int inOrderIndex = findIndex(inOrder,start, end , node.data);

        if(inOrderIndex == -1){
            node.left = null;
        }else{
            node.left = getTreeFromPreOrderAndInOrder(preOrder, inOrder, preIndex, start,inOrderIndex -1 );
        }

        if(inOrderIndex == -1){
            node.right = null;
        }else{
            node.right   = getTreeFromPreOrderAndInOrder(preOrder, inOrder, preIndex, inOrderIndex +1, end );
        }
        return node;
    }
 * */

