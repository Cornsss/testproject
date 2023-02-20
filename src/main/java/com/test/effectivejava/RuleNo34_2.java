package com.test.effectivejava;

/**
 * 策略枚举
 *
 */
public class RuleNo34_2 {
    public static void main(String[] args) {
        int pay = PayrollDay.PayType.WeekDay.pay(9, 250);
        System.out.println(pay);
    }
}

/**
 * 策略枚举类
 */
enum PayrollDay {

    Monday(PayType.WeekDay), Tuesday(PayType.WeekDay), Wednesday(PayType.WeekDay), Thurday(PayType.WeekDay), Friday(PayType.WeekDay),
    Sunday(PayType.Weekend), Saturday(PayType.Weekend);

    private PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    PayrollDay() {
    }

    public int pay(int minHours, int payRate) {
        return PayType.WeekDay.pay(minHours, payRate);
    }

    enum PayType {
        WeekDay {
            @Override
            public int overtimePay(int minWorkHours, int payRate) {
                return minWorkHours <= MINS_PER_SHIFT ? 0 : (minWorkHours-MINS_PER_SHIFT)*payRate/2;
            }
        },

        Weekend {
            @Override
            public int overtimePay(int minWorkHours, int payRate) {
                return minWorkHours*payRate/2;
            }
        };

        // 超出最低工作时长需要支付的金额
        public abstract int overtimePay(int minWorkHours, int payRate);

        // 每天最低工作时长
        private static final int MINS_PER_SHIFT = 8*60;

        // 最终支付的金额
        public int pay(int minWorked, int payRate) {
            return minWorked*payRate + overtimePay(minWorked, payRate);
        }
    }
}
