package bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Tdd training on 15.10.17.
 */
public class PaymentServiceTest {

    private PaymentService testedObject;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        testedObject = new PaymentService();
    }

    @Test
    public void shouldTransferMoneyWhenBalanceOnFirstAccountHasEnoughMoney() {
        Account accountFrom = new Account(1, 10);
        Account accountTo = new Account(2, 0);

        testedObject.transferMoney(accountFrom, accountTo, 3);

        assertThat(accountFrom.getBalance()).isEqualTo(7);
        assertThat(accountTo.getBalance()).isEqualTo(3);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenBalanceIsTooLow() {
        thrown.expect(IllegalArgumentException.class);
        Account accountFrom = new Account(1, 0);
        Account accountTo = new Account(2, 0);
        thrown.expectMessage("I'm very sorry, but you dont;t have enough money...");
        testedObject.transferMoney(accountFrom, accountTo, 600);
    }

    @Test
    public void shouldReturnTrueIfBalanceNotChange() {
        Account accountFrom = new Account(1, 0);
        int balanceBefore = accountFrom.getBalance();
        Account accountTo = new Account(2, 0);
        try {
            testedObject.transferMoney(accountFrom, accountTo, 600);
        } catch (IllegalArgumentException e) {
        }
        int balanceAfter = accountFrom.getBalance();
        assertThat(balanceBefore).isEqualTo(balanceAfter);

    }

}