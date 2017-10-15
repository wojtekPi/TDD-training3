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

    @Test
    public void shouldThrowIllegalArgumentException() {
        Account accountFrom = new Account(1, 3);
        Account accountTo = new Account(2, 10);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("I'm very sorry, but you don't have enough money...");

        testedObject.transferMoney(accountFrom, accountTo, 4);
    }

}