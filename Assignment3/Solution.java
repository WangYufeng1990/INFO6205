package Assignment3;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

    }

    //1.Add Two Numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int a = 0, b = 0, carry = 0, sum = 0;
        while (l1 != null || l2 != null) {
            a = l1 != null ? l1.val : 0;
            b = l2 != null ? l2.val : 0;
            sum = a + b + carry;
            carry = sum/10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry == 1) cur.next = new ListNode(1);
        return dummy.next;
    }

    //2.Copy List with Random Pointer
    public Node copyRandomList(Node head) {
        Node dummy =new Node(-1);
        Node cur = dummy;
        Node headCur = head;
        Map<Node, Node> map = new HashMap<>();
        while (headCur != null) {
            Node temp = new Node(headCur.val);
            map.put(headCur, temp);
            cur.next = temp;
            cur = cur.next;
            headCur = headCur.next;
        }
        headCur = head;
        cur = dummy.next;
        for (int i = 0; i < map.size(); i++) {
            cur.random = map.get(headCur.random);
            cur = cur.next;
            headCur = headCur.next;
        }
        return dummy.next;
    }

    //3.Merge k Sorted Lists
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (ListNode node : lists) {
            while (node != null) {
                minHeap.add(node.val);
                node = node.next;
            }
        }

        while (!minHeap.isEmpty()) {
            cur.next = new ListNode(minHeap.poll());
            cur = cur.next;
        }
        return dummy.next;
    }

    //4.Reorder List
    public void reorderList(ListNode head) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        cur = head;
        int index = len/2;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        ListNode reversed = reverse(cur.next);
        cur.next = null;
        cur = head;
        ListNode tempCur = null, tempReversed = null;
        while (reversed != null) {
            tempCur = cur.next;
            tempReversed = reversed.next;
            cur.next = reversed;
            reversed.next = tempCur;
            cur = tempCur;
            reversed = tempReversed;
        }
    }

    private ListNode reverse(ListNode head) {
        if (head == null) return head;
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    //5.Palindrome Linked List
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        slow = reverse(slow);
        while (slow != null) {
            if (fast.val != slow.val) return false;
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    //6.Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy, cur = head, post = head.next;
        pre.next = head;
        while (n > 0) {
            cur = cur.next;
            n--;
        }
        ListNode pointer = cur;
        cur = head;
        while (pointer != null) {
            pre = pre.next;
            cur = cur.next;
            post = post.next;
            pointer = pointer.next;
        }
        pre.next = post;
        return dummy.next;
    }

    //7.Odd Even Linked List
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    //8.Insert into a Sorted Circular Linked List
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node insert =  new Node(insertVal);
            insert.next = insert;
            return insert;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != head) {
            if (insertVal >= pre.val && insertVal <= cur.val) break;
            if (pre.val > cur.val && (insertVal >= pre.val || insertVal <= cur.val)) break;
            pre = cur;
            cur = cur.next;
        }
        pre.next = new Node(insertVal, cur);
        return head;
    }

    //9.Next Greater Node In Linked List
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int[] res = new int[list.size()];
        Stack<Integer> stack = new Stack<>();
        for (int i = res.length-1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= list.get(i)) stack.pop();
            res[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(list.get(i));
        }
        return res;
    }

    //10.Remove Duplicates from Sorted List II
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                pre.next = cur.next;
            }
            else {
                pre = pre.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
