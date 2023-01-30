package com.test.effectivejava.entity;


import java.util.Comparator;

import static java.util.Comparator.comparingInt;

public class PhoneNumber implements Comparable<PhoneNumber> {
    private int areaCode;
    private int prefix;
    private int lineNum;

    // 静态比较器构造
    private static final Comparator COMPARATOR = comparingInt((PhoneNumber pn) -> pn.areaCode).thenComparingInt((PhoneNumber pn) -> pn.prefix).thenComparingInt((PhoneNumber pn) -> pn.lineNum);

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

    @Override
    public int compareTo(PhoneNumber o) {
        return COMPARATOR.compare(this, o);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "areaCode=" + areaCode +
                ", prefix=" + prefix +
                ", lineNum=" + lineNum +
                ", hashCode=" + hashCode +
                '}';
    }
}