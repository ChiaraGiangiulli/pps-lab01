import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private AccountHolder accountHolder;
    private BankAccount bankAccount;
    private static final int INITIAL_BALANCE = 0;
    private static final int INITIAL_DEPOSIT = 100;
    private static final int ACCOUNT_ID = 1;
    private static final int WRONG_ID = 2;
    private static final int WITHDRAW = 70;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", ACCOUNT_ID);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_DEPOSIT);
        assertEquals(INITIAL_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testUnauthorizedDeposit() {
        final int unauthorizedDepositValue = 50;
        bankAccount.deposit(accountHolder.getId(), INITIAL_DEPOSIT);
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> bankAccount.deposit(WRONG_ID, unauthorizedDepositValue)
                ),
                () -> assertEquals(INITIAL_DEPOSIT, bankAccount.getBalance())
        );
    }

    @Test
    void testWithdraw() {
        final int expectedBalance = 30 - SimpleBankAccount.WITHDRAWAL_FEE;
        bankAccount.deposit(accountHolder.getId(), INITIAL_DEPOSIT);
        bankAccount.withdraw(accountHolder.getId(), WITHDRAW);
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    void testUnauthorizedWithdraw() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_DEPOSIT);
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> bankAccount.withdraw(WRONG_ID, WITHDRAW)
                ),
                () -> assertEquals(INITIAL_DEPOSIT, bankAccount.getBalance())
        );
    }

    @Test
    void testWithdrawGreaterThanOrEqualToBalance() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_DEPOSIT);
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> bankAccount.withdraw(ACCOUNT_ID, INITIAL_DEPOSIT + WITHDRAW)
                ),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> bankAccount.withdraw(ACCOUNT_ID, INITIAL_DEPOSIT)
                ),
                () -> assertEquals(INITIAL_DEPOSIT, bankAccount.getBalance())
        );
    }
}
