package Assignment1;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

    }

    //1.Sort Colors
    public void sortColors(int[] nums) {
        if (nums.length < 2) return;
        int point_0 = 0, point_2 = nums.length, cur = 0;
        while (cur < point_2) {
            if (nums[cur] == 0) {
                swap(nums, point_0, cur);
                cur++;
                point_0++;
            } else if (nums[cur] == 1) {
                cur++;
            } else {
                point_2--;
                swap(nums, cur, point_2);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //2.Majority Element II
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int first = 0, second = 0;
        int count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == first) count1++;
            else if (nums[i] == second) count2++;
            else if (count1 == 0) {
                first = nums[i];
                count1++;
            } else if (count2 == 0) {
                second = nums[i];
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i : nums) {
            if (i == first) count1++;
            else if (i == second) count2++;
        }
        if (count1 * 3 > nums.length) res.add(first);
        if (count2 * 3 > nums.length) res.add(second);
        return res;
    }

    //3.H-Index
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, len = citations.length;
        for (int i = 0; i < len; i++) {
            if (citations[len - i - 1] > h) h++;
        }
        return h;
    }

    //4.Intersection of Two Arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> temp = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        for (int num : nums2) {
            if (set.contains(num)) {
                temp.add(num);
                set.remove(num);
            }
        }
        int[] res = new int[temp.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = temp.get(i);
        }
        return res;
    }

    //5. Find K Closest Elements
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length;
        int left = 0, right = len - 1;
        int remove = len - k;
        while (remove > 0) {
            if (x - arr[left] > arr[right] - x) left++;
            else right--;
            remove--;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    //6.Reorganize String
    //new problem for me
    public String reorganizeString(String S) {
        int[] hash = new int[26];
        for (int i = 0; i < S.length(); i++) {
            hash[S.charAt(i) - 'a']++;
        }
        int max = 0, letter = 0;
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > max) {
                max = hash[i];
                letter = i;
            }
        }
        if (max > (S.length() + 1) / 2) {
            return "";
        }
        char[] res = new char[S.length()];
        int index = 0;
        while (hash[letter] > 0) {
            res[index] = (char) (letter + 'a');
            index += 2;
            hash[letter]--;
        }
        for (int i = 0; i < hash.length; i++) {
            while (hash[i] > 0) {
                if (index >= res.length) {
                    index = 1;
                }
                res[index] = (char) (i + 'a');
                index += 2;
                hash[i]--;
            }
        }
        return String.valueOf(res);
    }

    //7. Custom Sort String
    public String customSortString(String order, String s) {

        int[] count = new int[26];
        for (char c : s.toCharArray())
            count[c - 'a']++;

        StringBuilder ans = new StringBuilder();

        for (char c : order.toCharArray()) {
            for (int i = 0; i < count[c - 'a']; ++i)
                ans.append(c);
            count[c - 'a'] = 0;
        }

        for (char c = 'a'; c <= 'z'; ++c)
            for (int i = 0; i < count[c - 'a']; ++i)
                ans.append(c);

        return ans.toString();
    }

    //8.Pancake Sorting
    public List<Integer> pancakeSort(int[] arr) {
        int len = arr.length;
        List<Integer> ans = new ArrayList<>();
        int R = len - 1;
        int target = len;
        while (R >= 0) {
            while (R >= 0 && arr[R] == target) {
                R--;
                target--;
            }
            if (R < 0) break;
            int index = find(arr, target);
            if (index == 0) index = R;
            reverse(arr, 0, index);
            ans.add(index + 1);
        }
        return ans;
    }

    private int find(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    private void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    //9. Sort Array by Increasing Frequency
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else {
                list.add(num);
                map.put(num, 1);
            }
        }

        Collections.sort(list, (a, b) -> map.get(a) == map.get(b) ? (b - a) : map.get(a) - map.get(b));
        int[] arr = new int[nums.length];
        int k = 0;
        for (int num : list) {
            int count = map.get(num);
            while (count > 0) {
                arr[k++] = num;
                count--;
            }
        }
        return arr;
    }

    //10.Top K Frequent Words
    public List<String> topKFrequent(String[] words, int k) {

        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        List<String> res = new ArrayList<>(count.keySet());
        res.sort((a, b) -> {
            if (count.get(a).equals(count.get(b))) {
                return a.compareTo(b);
            } else {
                return count.get(b) - count.get(a);
            }
        });
        return res.subList(0, k);
    }

}
