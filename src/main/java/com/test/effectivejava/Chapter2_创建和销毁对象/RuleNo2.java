package com.test.effectivejava.Chapter2_创建和销毁对象;

/**
 * @author tracymc_zhu
 * 有多个构造器参数时要记得使用构建器
 */
public class RuleNo2 {
    public static void main(String[] args) {
        NutritionFacts build = new NutritionFacts.Builder(240, 1).sodium(1).calories(200).carbohydrate(50).build();
        System.out.println();
    }
}

class NutritionFacts{
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int carbohydrate;
    private final int sodium;

    public static class Builder{
        // 必需的参数
        private final int servingSize;
        private final int servings;

        // 可选的参数
        private int calories = 0;
        private int carbohydrate = 0;
        private int sodium = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    public NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        carbohydrate = builder.carbohydrate;
        sodium = builder.sodium;
    }
}
