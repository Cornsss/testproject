package com.test.effectivejava;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

/**
 * 使用EnumMap代替序数索引
 */
public class RuleNo37 {
    public static void main(String[] args) {

        // 将一整个花园的植物进行分类
        Set<Plant>[] set = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];
        Plant.LifeCycle[] values = Plant.LifeCycle.values();
        for (int i = 0; i < values.length; i++) {
            set[i] = new HashSet<>();
        }

        // notice：这里数组和集合一起使用会有问题，具体参考前面Rule28
        List<Plant> garden = Arrays.asList();
        for (Plant plant : garden) {
            set[plant.getLifeCycle().ordinal()].add(plant);

        }

        // 使用EnumMap代替序数索引：采用键类型的Class对象：这是一个有限制的类型令牌，提供运行时的泛型信息（Rule33）
        Map<Plant.LifeCycle, Set<Plant>> map = new EnumMap<>(Plant.LifeCycle.class);
        for (Plant.LifeCycle value : Plant.LifeCycle.values()) {
            map.put(value, new HashSet<>());
        }
        for (Plant plant : garden) {
            map.get(plant.getLifeCycle()).add(plant);
        }

        // 使用lambada表达式
        garden.stream().collect(groupingBy(x -> x.getLifeCycle(), () -> new EnumMap<>(Plant.LifeCycle.class), toSet()));
    }
}

/**
 * 花园里的植物枚举
 */
class Plant {

    //  一年生、多年生、两年生
    enum LifeCycle {ANNUAL, PERENIAL, BIENIAL}

    private String name;
    private LifeCycle lifeCycle;

    public Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }
}
