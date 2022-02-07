package Assignment2;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

    }

    //1.Search Insert Position
    public int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        if (nums[start] > target) return 0;
        else if (nums[end] < target) return end+1;
        while (start+1 < end) {
            int mid = (end - start)/2 + start;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) start = mid;
            else end = mid;
        }
        if (nums[start] == target) return start;
        else return end;
    }

    //2.Single Element in a Sorted Array
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) return nums[0];
        int len = nums.length;
        int start = 0, end = len-1;
        while (start < end) {
            int mid = (end - start)/2 + start;
            if (mid % 2 == 0) {
                if (nums[mid] == nums[mid+1]) start = mid+2;
                else end = mid;
            }
            else {
                if (nums[mid] == nums[mid-1]) start = mid+1;
                else end = mid-1;
            }
        }
        return nums[start];
    }

    //3.Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int len = nums.length;
        int start = 0, end = len-1;
        if (nums[start] < nums[end]) return nums[start];
        while (start+1 < end) {
            int mid = (end - start)/2 + start;
            if (nums[mid] > nums[start]) start = mid;
            else end = mid;
        }

        if (nums[start] > nums[end]) return nums[end];
        else return nums[start];
    }

    //4.Meeting Rooms II
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.sort(intervals, (a1, a2) -> (a1[0] - a2[0]));
        pq.offer(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (pq.peek() <= intervals[i][0]) {
                pq.poll();
            }
            pq.offer(intervals[i][1]);
        }
        return pq.size();
    }

    //5.Top K Frequent Elements
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a1, int[] a2) -> a1[1] - a2[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (pq.size() == k) {
                if (pq.peek()[1] < count) {
                    pq.poll();
                    pq.offer(new int[]{num, count});
                }
            }
            else {
                pq.offer(new int[]{num, count});
            }
        }
        int[] res = new int[k];
        int index = 0;
        while (pq.size() > 0) {
            res[index++] = pq.poll()[0];
        }
        return res;
    }

    //6.3Sum Closest
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int start = i+1, end = nums.length-1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (Math.abs(target - sum) < Math.abs(target - res)) res = sum;
                if (sum == target) return sum;
                else if (sum > target) end--;
                else start++;
            }
        }
        return res;
    }

    //7.Insert Interval
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0, len = intervals.length;
        ArrayList<int[]> res = new ArrayList<>();
        while (i < len && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i++]);
        }

        int[] temp = newInterval.clone();
        while (i < len && intervals[i][0] <= newInterval[1]) {
            temp[0] = Math.min(intervals[i][0], temp[0]);
            temp[1] = Math.max(intervals[i][1], temp[1]);
            i++;
        }
        res.add(temp);

        while (i < len && intervals[i][0] > newInterval[1]) {
            res.add(intervals[i++]);
        }
        return res.toArray(new int[1][]);
    }

    //8.Non-overlapping Intervals
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 1) return 0;
        Arrays.sort(intervals, (int[] a1, int[] a2) -> a1[1] - a2[1]);

        int end = intervals[0][1], count = 1;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
        }
        return intervals.length - count;
    }

    //9.Interval List Intersections
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        ArrayList<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length) {
            int left = Math.max(firstList[i][0], secondList[j][0]);
            int right = Math.min(firstList[i][1], secondList[j][1]);
            if (left <= right) res.add(new int[]{left, right});
            if (firstList[i][1] > secondList[j][1]) j++;
            else i++;
        }
        return res.toArray(new int[res.size()][]);
    }

    //10.4Sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i-1]) continue;

            for (int j = i+1; j < nums.length; j++) {
                if (j != i+1 && nums[j] == nums[j-1]) continue;

                int left = j+1, right = nums.length-1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        res.add(temp);
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left-1]) left++;
                        while (left < right && nums[right] == nums[right+1]) right--;
                    }
                    else if (sum < target) left++;
                    else right--;
                }
            }
        }
        return res;
    }
}
