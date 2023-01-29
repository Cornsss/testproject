package com.test.effectivejava;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author tracymc_zhu
 * 覆盖equals方法时必须覆盖hashcode方法
 * Object类的规范：
 *  1. 未被修改equals方法的对象，无论多少次调用hashCode方法都相同
 *  2. 两个对象的equals返回相同，则hashCode方法也返回相同
 *  3. 两个对象的equals返回不相同，则hashCode方法可以相同，也可以不相同；但不相同的情况散列性能更高
 */
public class RuleNo11 {
    public static void main(String[] args) {
//        Map<PhoneNumber, String> map = new HashMap<>();
//        map.put(new PhoneNumber(707, 867, 5309), "Jenny");
//        System.out.println(map.get(new PhoneNumber(707, 867, 5309)));

        System.out.println(new PhoneNumber(707, 867, 5309).hashCode());
        System.out.println(new PhoneNumber(708, 867, 5309).hashCode());
        System.out.println(new PhoneNumber(709, 867, 5309).hashCode());
        System.out.println(new PhoneNumber(710, 867, 5309).hashCode());
    }
}

class PhoneNumber {
    private int areaCode;
    private int prefix;
    private int lineNum;

    // 懒加载hashCde
    private int hashCode;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNum = lineNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return areaCode == that.areaCode && prefix == that.prefix && lineNum == that.lineNum;
    }

    @Override
    public int hashCode() {
        // 不怎么考虑性能的情况下：(会引发数组的创建、装箱及拆箱)
//        return Objects.hash(areaCode, prefix, lineNum);
        // 计算散列码的开销较大、懒加载hashCode
        int resultCode = hashCode;
        if (resultCode == 0) {
            resultCode = Integer.hashCode(areaCode);
            resultCode += resultCode * 31 + Integer.hashCode(prefix);
            resultCode += resultCode * 31 + Integer.hashCode(lineNum);
            hashCode = resultCode;
        }
        return resultCode;
    }
}
