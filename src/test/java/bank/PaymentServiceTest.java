package bank;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Tdd training on 15.10.17.
 */
public class PaymentServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private PaymentService testedObject;

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

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNegativeBalanceExceededToMuch() {
        int amount = 10;
        Account accountFrom = new Account(1, -495);
        Account accountTo = new Account(2, 0);

        testedObject.transferMoney(accountFrom, accountTo, amount);
        thrown.expect(IllegalArgumentException.class);

    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionMessageWhenNegativeBalanceExceededToMuch() {
        int amount = 10;
        Account accountFrom = new Account(1, -495);
        Account accountTo = new Account(2, 0);

        testedObject.transferMoney(accountFrom, accountTo, amount);
        thrown.expectMessage("You no understand me, " +
                "your balance is exceeded to much beyond -500");
    }




}