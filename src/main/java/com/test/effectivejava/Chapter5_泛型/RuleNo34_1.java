package com.test.effectivejava.Chapter5_泛型;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * 用enum代替int
 * 1. Operation.values()方法会将常量的名字转为常量本身，即PLUS -> +
 * 2. 如果需要将定制的字符串转为对应的枚举，
 */
public class RuleNo34_1 {

    private static final Map<String, Operation> stringToEnum = Stream.of(Operation.values()).collect(toMap(Object::toString, e->e));

    public static void main(String[] args) {

        double x = 1.2;
        double y = 3.8;
        Operation[] values = Operation.values();

        for (Operation value : values) {
            System.out.printf("%f %s %f = %f%n", x, value, y, value.apply(x,y));
        }

        Optional<Operation> operation = fromString("+");
        System.out.println(operation.get());
    }

    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }
}

// 在枚举类中声明抽象方法
enum Operation {

    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x+y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x-y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x*y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x/y;
        }
    };

    private String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public abstract double apply(double x, double y);

    @Override
    public String toString() {
        return symbol;
    }

    public int numberOfEnum() {
        return ordinal()+1;
    }
}


