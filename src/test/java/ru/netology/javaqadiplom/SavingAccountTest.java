package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {
    @Test
    public void whenNegativeInitialBalancePay() {        //отрицательный initialBalance V
        SavingAccount account = new SavingAccount(-1, 100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(10);
        });
    }

    @Test
    public void whenNegativeInitialBalanceAdd() {        //отрицательный initialBalance V
        SavingAccount account = new SavingAccount(-1, 100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(10);
        });
    }

    @Test
    public void boundaryValueInitialBalanceLessMinBalancePay() {        // initialBalance меньше  мин на 1 р VV
        SavingAccount account = new SavingAccount(99, 100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(10);
        });
    }

    @Test
    public void boundaryValueInitialBalanceLessMinBalanceAdd() {        // initialBalance меньше  мин на 1 р VV
        SavingAccount account = new SavingAccount(99, 100, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(10);
        });
    }

    @Test
    public void whenInitialBalanceEqualMinPlusAdd() {     // initialBalance = min + пополнение
        SavingAccount account = new SavingAccount(100, 100, 5_000, 1);
        account.add(200);
        Assertions.assertEquals(300, account.getBalance());
    }

    @Test
    public void boundaryValueInitialBalanceThanMinBalancePay() {        //initialBalance больше  мин на 1 р
        SavingAccount account = new SavingAccount(101, 100, 5_000, 1);
        account.pay(1);
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void whenMinBalanceNegativePay() {        // min < 0
        SavingAccount account = new SavingAccount(700, -1, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(200);
        });
    }

    @Test
    public void whenMinBalanceNegativeAdd() {        // min < 0
        SavingAccount account = new SavingAccount(700, -1, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(200);
        });
    }

    @Test
    public void saleInTheNegative() {
        SavingAccount account = new SavingAccount(0, 0, 5_000, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(200);
        });
    }

    @Test
    public void whenMinBalanceMoreMaxBalancePay() {        //минимальный баланс больше максимального
        SavingAccount account = new SavingAccount(5_000, 5_000, 3_000, 4);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(100);
        });
    }

    @Test
    public void whenMinBalanceMoreMaxBalanceAdd() {        //минимальный баланс больше максимального
        SavingAccount account = new SavingAccount(5_000, 5_000, 3_000, 4);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(100);
        });
    }

    @Test
    public void whenNegativeRatePay() {        //rate = -1
        SavingAccount account = new SavingAccount(800, 100, 3_000, -1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(200);
        });
    }

    @Test
    public void whenNegativeRateAdd() {        //rate = -1
        SavingAccount account = new SavingAccount(800, 100, 3_000, -1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.add(200);
        });
    }

    @Test
    public void whenPurchaseInTheNegative() {        // покупка в минус
        SavingAccount account = new SavingAccount(800, 100, 5_000, 1);
        account.pay(2000);
        Assertions.assertEquals(800, account.getBalance());
    }

    @Test
    public void shouldAddMoreMaxBalance() {     // пополнение больше максимального, не сумма больше, а само пополнение больше мах
        SavingAccount account = new SavingAccount(800, 100, 5_000, 4);
        account.add(4_900);
        Assertions.assertEquals(800, account.getBalance());
    }

    @Test
    public void whenLessThanAYearForOneDay() {
        SavingAccount account = new SavingAccount(200, 100, 5_000, 15);
        int daysWithoutChange = 364;
        int expected = 30;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void exactlyOneYear() {
        SavingAccount account = new SavingAccount(200, 100, 5_000, 15);
        int daysWithoutChange = 365;
        int expected = 30;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void moreForTheDay() {
        SavingAccount account = new SavingAccount(200, 100, 5_000, 15);
        int daysWithoutChange = 366;
        int expected = 30;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void whenBalanceVerySmall() {
        SavingAccount account = new SavingAccount(10, 100, 5_000, 15);
        int daysWithoutChange = 366;
        int expected = 1;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }
}