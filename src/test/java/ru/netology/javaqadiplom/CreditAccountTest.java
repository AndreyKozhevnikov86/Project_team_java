package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(5_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }
    @Test
    public void negativeInitialBalance(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            new CreditAccount(-1_000, 5_000,15);
        });
    }
    @Test
    public void negativeCreditLimit(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(1_000,-5_000,15);
        });
    }
    @Test
    public void zeroRate(){
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                0
        );
        Assertions.assertEquals(0, account.getRate());
    }
    @Test
    public void shouldChangeIfAmountPositive(){
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(500);
        Assertions.assertEquals(500, account.getBalance());
    }
    @Test
    public void shouldZeroIfEqualBalance(){
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(1_000);
        Assertions.assertEquals(0,account.getBalance());
    }
    @Test
    public void shouldNegativeBalance(){
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(2_000);
        Assertions.assertEquals(-1_000, account.getBalance());
    }
    @Test
    public void IfAmountIsMoreThanCreditLimitPlusBalance(){
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(10_000);
        Assertions.assertEquals(1_000, account.getBalance());
    }
    @Test
    public void debtIfBalanceNegative(){
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(3_000);
        Assertions.assertEquals(-300, account.yearChange());
    }
    @Test
    public void debtIfBalanceZero(){
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(1_000);
        Assertions.assertEquals(0, account.yearChange());
    }
    @Test
    public void debtIfBalancePositive(){
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(500);
        Assertions.assertEquals(0, account.yearChange());
    }

}