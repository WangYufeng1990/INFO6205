package Assignment4;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {

    }

    //1.Merge in between LinkedList
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        dummy.next = list1;
        ListNode list2Temp = list2;
        while (list2Temp.next != null) {
            list2Temp = list2Temp.next;
        }

        ListNode list1Temp = list1;
        ListNode nodeA = null;
        ListNode nodeB = null;
        while (list1Temp != null && b >= 0) {
            if (a == 1) {
                nodeA = list1Temp;
            }
            if (b == 0) {
                nodeB = list1Temp;
            }
            list1Temp = list1Temp.next;
            a--;
            b--;
        }
        nodeA.next = list2;
        list2Temp.next = nodeB.next;
        return dummy.next;
    }

    //2.Partition List
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;

        ListNode nodeA = new ListNode(-1);
        ListNode nodeB = new ListNode(1);
        ListNode before = nodeA;
        ListNode after = nodeB;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                before.next = cur;
                cur = cur.next;
                before = before.next;
            }
            else {
                after.next = cur;
                cur = cur.next;
                after = after.next;
            }
        }
        before.next = nodeB.next;
        after.next = null;
        return nodeA.next;
    }

    //3.Reverse Nodes in even Length Groups
    public ListNode reverseEvenLengthGroups(ListNode head) {
        if (head == null || head.next == null) return head;

        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        cur = head;
        ListNode pre = null;
        int lenOfNodes = 1;
        while (cur != null) {
            lenOfNodes = Math.min(lenOfNodes, len);
            len -= lenOfNodes;
            int count = 0;
            if (lenOfNodes % 2 == 1) {
                while (cur != null && count < lenOfNodes) {
                    pre = cur;
                    cur = cur.next;
                    count++;
                }
            }
            else {
                ListNode[] temp = reverse(cur, lenOfNodes);
                pre.next = temp[0];
                pre = cur;
                cur = temp[1];
            }
            lenOfNodes++;
        }
        return head;
    }

    private ListNode[] reverse(ListNode head, int k) {
        ListNode pre = null, cur = head, post = null;
        while (cur != null && k > 0) {
            post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
            head.next = cur;
            k--;
        }
        return new ListNode[]{pre, post};
    }

    //4.Find the Minimum and Maximum Number of Nodes Between Critical Points
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head.next.next == null) return new int[]{-1, -1};
        ListNode pre = head, cur = head.next, post = head.next.next;
        int index = 1, minLen = Integer.MAX_VALUE;
        int minIndex = 0, curIndex = 0, preIndex = 0;
        while (post != null) {
            if ((cur.val < pre.val && cur.val < post.val)
                    || (cur.val > pre.val && cur.val > post.val)) {
                if (minIndex == 0) {
                    minIndex = index;
                    curIndex = index;
                }
                else {
                    preIndex = curIndex;
                    curIndex = index;
                    minLen = Math.min(minLen, curIndex - preIndex);
                }
            }
            index++;
            pre = pre.next;
            cur = cur.next;
            post = post.next;
        }
        if (curIndex == minIndex) return new int[]{-1, -1};
        return new int[]{minLen, curIndex - minIndex};
    }

    //5.Sort List
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int len = getLength(head);

        for (int step = 1; step < len; step *= 2) {
            ListNode pre = dummy;
            ListNode cur = dummy.next;
            while (cur != null) {
                ListNode list1 = cur;
                ListNode list2 = split(list1, step);
                cur = split(list2, step);
                ListNode temp = mergeList(list1, list2);
                pre.next = temp;
                while (pre.next != null) {
                    pre = pre.next;
                }

            }
        }
        return dummy.next;

    }

    public int getLength(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    public ListNode split(ListNode head, int step) {
        if (head == null) return null;
        ListNode cur = head;
        for (int i = 1; i < step && cur.next != null; i++) {
            cur = cur.next;
        }

        ListNode rightHead = cur.next;
        cur.next = null;
        return rightHead;
    }

    public ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                res.next = l1;
                l1 = l1.next;
            } else {
                res.next = l2;
                l2 = l2.next;
            }
            res = res.next;
        }

        if (l1 == null) res.next = l2;
        if (l2 == null) res.next = l1;

        return dummy.next;
    }

    //6.Linked List Random Node
    private List<Integer> res = new ArrayList<>();

    public Solution(ListNode head) {
        while (head != null) {
            res.add(head.val);
            head = head.next;
        }
    }

    public int getRandom() {
        int index = (int)(Math.random() * res.size());
        return res.get(index);
    }

    //7.Reverse Linked List II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        for (int i = 1; i < left; i++) {
            cur = cur.next;
        }
        ListNode pre = cur;
        for (int i = left; i <= right; i++) {
            cur = cur.next;
        }
        ListNode post = cur.next;
        cur.next = null;
        ListNode temp = reverse(pre.next);
        pre.next.next = post;
        pre.next = temp;
        return dummy.next;
    }

    ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode cur = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    //8.Split Linked List in Parts
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        int len = getLength(head);
        ListNode cur = head;
        int width = len / k;
        int remain = len % k;

        for (int i = 0; i < k; i++) {
            ListNode newHead = cur;
            for (int j = 0; j < width + (i < remain ? 1 :0) - 1; j++) {
                if (cur != null) {
                    cur = cur.next;
                }
            }
            res[i] = newHead;
            if (cur != null) {
                ListNode pre = cur;
                cur = cur.next;
                pre.next = null;
            }

        }
        return res;
    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    //9.Linked List Components
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int count = 0;

        while (head != null) {
            if (set.contains(head.val) && (head.next == null || !set.contains(head.next.val))) {
                count++;
            }
            head = head.next;
        }
        return count;
    }

    //10.Maximum Twin Sum of a Linked List
    public int pairSum(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode newHead = reverse(slow);
        ListNode cur = head;
        int res = Integer.MIN_VALUE;
        while (newHead != null) {
            res = Math.max(res, cur.val + newHead.val);
            cur = cur.next;
            newHead = newHead.next;
        }
        return res;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
