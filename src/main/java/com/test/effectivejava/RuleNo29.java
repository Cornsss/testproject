package com.test.effectivejava;

import com.test.effectivejava.entity.PhoneNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author tracymc_zhu
 * 列表优于数组
 * 1、数组是协变的，列表是可变的; 例如下面的代码就可以看出，用数组，运行时才能看出错误 ArrayStoreException ；用集合编译就不通过
 * 2、数组是具体化的，而泛型是在编译时强化类型，在运行时擦除
 * 3、创建泛型数组是非法的，因为他不是类型安全的
 *    这里把之前定义的Stack类也泛型化
 */
public class RuleNo29 {
    public static void main(String[] args) {
//        Object[] obj = new Long[1];
//        obj[0] = "I am not fit in";
//        System.out.println(obj[0]);

//        List<Object> list = new ArrayList<Long>();
//        list.add("I am not fit in");
        Chooser chooser = new Chooser(Arrays.asList(new PhoneNumber(710, 867, 229), new PhoneNumber(709, 867, 122), new PhoneNumber(706, 867, 56), new PhoneNumber(707, 867, 12)
                , new PhoneNumber(34, 867, 5309), new PhoneNumber(68, 867, 43534), new PhoneNumber(211, 867, 31323), new PhoneNumber(707, 867, 5309)));
        System.out.println(chooser.choose());

        Chooser2<PhoneNumber> chooser2 = new Chooser2<>(Arrays.asList(new PhoneNumber(710, 867, 229), new PhoneNumber(709, 867, 122), new PhoneNumber(706, 867, 56), new PhoneNumber(707, 867, 12)
                , new PhoneNumber(34, 867, 5309), new PhoneNumber(68, 867, 43534), new PhoneNumber(211, 867, 31323), new PhoneNumber(707, 867, 5309)));
        System.out.println(chooser.choose());

    }
}

class Chooser {

    private final Object[] elements;

    public Chooser(Collection choice) {
        this.elements = choice.toArray();
    }

    public Object choose() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        return elements[current.nextInt(elements.length)];
    }
}

class Chooser2<T> {

//    private final T[] elements;
    private final List<T> elements;

    public Chooser2(Collection<T> choice) {
        this.elements = new ArrayList<>(); // 这样的写法会有警告，消除未受检的警告，必须使用集合
    }

    public Object choose() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
//        return elements[current.nextInt(elements.length)];
        return elements.get(current.nextInt(elements.size()));
    }
}
