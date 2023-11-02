package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SavingAccountTest {
    @Test
    public void shouldAddLessThanMaxBalance() {            //должен добавить меньше Максимального Баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void whenNegativeInitialBalancePay() {        //отрицательный initialBalance
        SavingAccount account = new SavingAccount(-1, 100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(0);
        });
    }

    @Test
    public void whenNegativeInitialBalanceAdd() {        //отрицательный initialBalance
        SavingAccount account = new SavingAccount(-1, 100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(0);
        });
    }

    @Test
    public void whenInitialBalanceEqualZeroPay() {        // initialBalance = 0
        SavingAccount account = new SavingAccount(0, 100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(0);
        });
    }

    @Test
    public void whenInitialBalanceEqualZeroAdd() {        // initialBalance = 0
        SavingAccount account = new SavingAccount(0, 100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(0);
        });
    }

    @Test
    public void whenInitialBalanceEqualOnePay() {        // initialBalance = 1
        SavingAccount account = new SavingAccount(1, 100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(0);
        });
    }

    @Test
    public void whenInitialBalanceEqualOneAdd() {        // initialBalance = 1
        SavingAccount account = new SavingAccount(1, 100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(0);
        });
    }

    @Test
    public void whenInitialBalanceEqualMorePay() {        // initialBalance > max
        SavingAccount account = new SavingAccount(7000, 100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(200);
        });
    }

    @Test
    public void whenInitialBalanceEqualMoreMaxAdd() {        // initialBalance > max
        SavingAccount account = new SavingAccount(7000, 100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(200);
        });
    }

    @Test
    public void whenInitialBalanceEqualMin() {     // initialBalance = min
        SavingAccount account = new SavingAccount(100, 100, 5_000, 1);
        account.pay(0);
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void whenInitialBalanceEqualMinPlusPay() {     // initialBalance = min + продажа
        SavingAccount account = new SavingAccount(100, 100, 5_000, 1);
        account.pay(50);
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void whenInitialBalanceMoreMin() {     // initialBalance > min
        SavingAccount account = new SavingAccount(700, 100, 5_000, 1);
        account.pay(200);
        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void whenInitialBalanceEqualMax() {     // initialBalance = max
        SavingAccount account = new SavingAccount(5_000, 100, 5_000, 1);
        account.pay(200);
        Assertions.assertEquals(4800, account.getBalance());
    }

    @Test
    public void whenInitialBalanceEqualMaxPlusAdd() {     // initialBalance = max
        SavingAccount account = new SavingAccount(5_000, 100, 5_000, 1);
        account.add(200);
        Assertions.assertEquals(5000, account.getBalance());
    }

    @Test
    public void whenMinBalanceNegative() {        // min < 0
        SavingAccount account = new SavingAccount(700, -100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(200);
        });
    }

    @Test
    public void whenMinBalanceEqualMinusOne() {        // min = -1
        SavingAccount account = new SavingAccount(700, -1, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(200);
        });
    }

    @Test
    public void whenMinBalanceEqualZero() {        // min = 0
        SavingAccount account = new SavingAccount(700, 0, 5_000, 1);
        account.pay(200);
        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void whenMinBalanceEqualOne() {        // min = 1
        SavingAccount account = new SavingAccount(700, 1, 5_000, 1);
        account.pay(200);
        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void whenPayMinBalancePositiveMinBalanceLessMaxBalance() {        // min положительный min < max Pay
        SavingAccount account = new SavingAccount(700, 100, 5_000, 1);
        account.pay(200);
        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void whenAddMinBalancePositiveMinBalanceLessMaxBalance() {        // min положительный min < max add
        SavingAccount account = new SavingAccount(700, 100, 5_000, 1);
        account.add(200);
        Assertions.assertEquals(900, account.getBalance());
    }

    @Test
    public void whenMinBalanceMoreMaxBalance() {        //минимальный баланс больше максимального
        SavingAccount account = new SavingAccount(1_000, 5_000, 3_000, 4);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(100);
        });
    }

    @Test
    public void whenNegativeRateAdd() {        //rate = -1
        SavingAccount account = new SavingAccount(1_000, 5_000, 3_000, -1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(200);
        });
    }

    @Test
    public void whenNegativeRatePay() {        //rate = -1
        SavingAccount account = new SavingAccount(1_000, 5_000, 3_000, -1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(200);
        });
    }

    @Test
    public void whenRateEqualZeroAdd() {        // rate = 0
        SavingAccount account = new SavingAccount(800, 100, 5_000, 0);
        account.add(200);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void whenRateEqualZeroPay() {        // rate = 0
        SavingAccount account = new SavingAccount(800, 100, 5_000, 0);
        account.pay(200);
        Assertions.assertEquals(600, account.getBalance());
    }

    @Test
    public void whenRateEqualOnePay() {        // rate = 1
        SavingAccount account = new SavingAccount(800, 100, 5_000, 1);
        account.pay(200);
        Assertions.assertEquals(600, account.getBalance());
    }

    @Test
    public void whenRateEqualOneAdd() {        // rate = 1
        SavingAccount account = new SavingAccount(800, 100, 5_000, 1);
        account.add(200);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void whenAmountNegativeAdd() {        // amount отриц
        SavingAccount account = new SavingAccount(800, 100, 5_000, 1);
        account.add(-100);
        Assertions.assertEquals(800, account.getBalance());
    }

    @Test
    public void whenAmountNegativePay() {        // amount отрий
        SavingAccount account = new SavingAccount(800, 100, 5_000, 1);
        account.pay(-100);
        Assertions.assertEquals(800, account.getBalance());
    }

    @Test
    public void whenPurchaseInTheNegative() {        // покупка в минус
        SavingAccount account = new SavingAccount(800, 100, 5_000, 1);
        account.pay(2000);
        Assertions.assertEquals(800, account.getBalance());
    }

    @Test
    public void whenRemainderLessMin() {        // остаток меньше минимума
        SavingAccount account = new SavingAccount(800, 100, 5_000, 1);
        account.pay(750);
        Assertions.assertEquals(800, account.getBalance());
    }

    @Test
    public void shouldAddMoreMaxBalance() {     // пополнение больше максимального, не сумма больше, а само пополнение больше мах
        SavingAccount account = new SavingAccount(800, 100, 5_000, 4);
        account.add(5_500);
        Assertions.assertEquals(800, account.getBalance());
    }

    @Test
    public void shouldAddMoreMaxBalanceWhenAmountLessMaxBalance() {     // пополнение больше максимального, не сумма больше, а само пополнение больше мах
        SavingAccount account = new SavingAccount(800, 100, 5_000, 4);
        account.add(4_500);
        Assertions.assertEquals(800, account.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "30, 365, 200, 15",   /* ровно год*/
            "0, 364, 3000, 4",     /* меньне на один день*/
            "30, 366, 3000, 15",    /* больше  на один день*/
            "52, 365, 350, 15",    /* тут должно быть округление в меньшую сторону*/
    })
    public void bonusCalculationIfChangesWereMadeLessThanAYearAgo(int expected, int days, int balance, int rate) {     // расчет бонуса если изменения вносились меньше года назад
        SavingAccount account = new SavingAccount(3000, 100, 5_000, 20);
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }
}
