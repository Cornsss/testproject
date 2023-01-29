package com.test.effectivejava;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author tracymc_zhu
 * 覆盖equals方法时要遵守相关规定
 * 覆盖equals方法后应该问自己：是否满足对称性、传递性、一致性？（写测试方法测试一下）
 */
public class RuleNo10 {
    public static void main(String[] args) {
        CaseSensitiveString polish1 = new CaseSensitiveString("Polish");
        CaseSensitiveString polish2 = new CaseSensitiveString("polish");
        System.out.println(polish1.equals(polish2));
        System.out.println(polish1.hashCode()==polish2.hashCode());

        // 把对象放到集合中
        List<CaseSensitiveString> list = new ArrayList<>();
        list.add(polish1);
        System.out.println(list.contains(polish2));
//
        System.out.println("=======演示违反对称性==========");
        ColorPoint point1 = new ColorPoint(1, 2, "yellow");
        Point point2 = new Point(1, 2);
        System.out.println(point2.equals(point1));
        System.out.println(point1.equals(point2));

        System.out.println("=======演示违反传递性==========");
        Point yellow = new ColorPoint(1, 2, "yellow");
        Point point = new Point(1, 2);
        Point blue = new ColorPoint(1, 2, "blue");
        System.out.println(yellow.equals(point));
        System.out.println(point.equals(blue));
        System.out.println(yellow.equals(blue));

//        System.out.println("=======演示TimeStamp和DateUtil违反对称性=======");
//        Timestamp timestamp = new Timestamp(Long.valueOf(1000));
//        Date timestamp1 = new Date(Long.valueOf(1000));
//        System.out.println(timestamp.equals(timestamp1));
//        System.out.println(timestamp1.equals(timestamp));
    }
}

final class CaseSensitiveString {
    private final String s;
    public CaseSensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    @Override
    public boolean equals(Object o) {
//        if (o instanceof CaseSensitiveString) {
//            return s.equalsIgnoreCase(((CaseSensitiveString) o).s);
//        } else if (o instanceof String) {
//            s.equalsIgnoreCase((String) o); // String没有覆盖equals方法，所以他不知道区分大小写
//        }

        return o instanceof CaseSensitiveString && ((CaseSensitiveString) o).s.equalsIgnoreCase(this.s);
//        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(s);
    }
}

class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Point && ((Point) o).x == ((Point) o).x && ((Point) o).y == ((Point) o).y;
    }
}


class ColorPoint extends Point {
    private String colorName;
    public ColorPoint(int x, int y, String colorName) {
        super(x, y);
        this.colorName = colorName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPoint)) {
            return false;
        }
        return super.equals(o) && ((ColorPoint) o).colorName.equals(colorName);
    }


}