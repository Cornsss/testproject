package com.test.leekcode;

import java.util.HashMap;
import java.util.Map;

public class Test001_SumTwoNums {

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    public static int[] twoSum2(int[] nums, int target) {
        // 接收结果的map，key=value；value=index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i]))
                return new int[]{i, map.get(target-nums[i])};
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution!");
    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        CheckUtils01.startRecording();
        int[] ints = twoSum(nums, target);
        int[] ints1 = twoSum2(nums, target);
        CheckUtils01.stopRecording();
        if (ints.length == 2)
            System.out.println(ints1[0] + "-" + ints1[1]);
    }
}
