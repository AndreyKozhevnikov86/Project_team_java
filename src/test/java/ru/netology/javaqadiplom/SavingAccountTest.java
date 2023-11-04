package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {
    @Test
    public void whenNegativeInitialBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(-1, 100, 5_000, 1);
        });
    }

    @Test
    public void whenMinBalanceNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(700, -1, 5_000, 1);
        });
    }

    @Test
    public void whenMinBalanceMoreMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(5_000, 5_000, 3_000, 4);
        });
    }

    @Test
    public void whenNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(800, 100, 3_000, -1);
        });
    }

    @Test
    public void whenPurchaseInTheNegative() {
        SavingAccount account = new SavingAccount(800, 100, 5_000, 1);
        account.pay(2000);
        Assertions.assertEquals(800, account.getBalance());
    }

    @Test
    public void shouldAddMoreMaxBalance() {
        SavingAccount account = new SavingAccount(800, 100, 5_000, 4);
        account.add(4_900);
        Assertions.assertEquals(800, account.getBalance());
    }

    @Test
    public void whenInitialBalanceLessMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(99, 100, 5_000, 1);
        });
    }

    @Test
    public void addSavingsAccount() {
        SavingAccount account = new SavingAccount(100, 100, 5_000, 1);
        account.add(200);
        Assertions.assertEquals(300, account.getBalance());
    }

    @Test
    public void boundaryValueInitialBalanceThanMinBalancePay() {
        SavingAccount account = new SavingAccount(101, 100, 5_000, 1);
        account.pay(1);
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void whenLessThanAYearForOneDay() {
        SavingAccount account = new SavingAccount(200, 100, 5_000, 15);
        int daysWithoutChange = 364;
        int expected = 0;
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
        SavingAccount account = new SavingAccount(10, 0, 5_000, 15);
        int daysWithoutChange = 366;
        int expected = 1;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }
}