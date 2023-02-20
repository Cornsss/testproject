package com.test.effectivejava.Chapter4_类和接口;


import java.util.*;

/**
 * @author tracymc_zhu
 * 使用复合优先于继承
 * 1. 使用继承时问问自己是否是is-a的关系
 * 2. 使用复合解决扩展
 */
public class RuleNo18 {
    public static void main(String[] args) {
        // 统计HashSet添加元素的次数（其中删除后再新增也算新增）
        List<String> list = Arrays.asList("xxx", "aaa", "adfsd");
        InstrumentedHashSet instrumentedHashSet = new InstrumentedHashSet();
        instrumentedHashSet.addAll(Arrays.asList("xxx","aaa","adfsd"));
        System.out.println(instrumentedHashSet.getAddCount());

        InstrumentSet instrumentSet = new InstrumentSet(new HashSet());
        instrumentSet.addAll(list);
        System.out.println(instrumentSet.getAddCount());
    }
}

// 直接继承，使用继承时问问自己是否是is-a的关系
class InstrumentedHashSet extends HashSet {

    private int addCount = 0;

    public InstrumentedHashSet() {
    }

    @Override
    public boolean add(Object o) {
        addCount++;
        return super.add(o);
    }

    @Override
    public boolean addAll(Collection c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}

// 复合,这也是一种装饰者模式
class InstrumentSet extends ForwaringSet {

    private int addCount = 0;

    public InstrumentSet(Set set) {
        super(set);
    }

    @Override
    public boolean add(Object o) {
        addCount++;
        return super.add(o);
    }

    @Override
    public boolean addAll(Collection c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}

class ForwaringSet<E> implements Set<E> {

    private final Set<E> set;

    public ForwaringSet(Set<E> set) {
        this.set = set;
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return set.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return set.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    public void clear() {
        set.clear();
    }
}
