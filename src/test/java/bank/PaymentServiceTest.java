package bank;

import org.assertj.core.api.Assertions;
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
        Instrument instrument = new Instrument(Currencies.EU, 3);
        Account accountFrom = new Account(1, 10, new Instrument(Currencies.EU));
        Account accountTo = new Account(2, 0, new Instrument(Currencies.EU));

        testedObject.transferMoney(accountFrom, accountTo, instrument);

        assertThat(accountFrom.getBalance()).isEqualTo(7);
        assertThat(accountTo.getBalance()).isEqualTo(3);
    }

    @Test
    public void shouldThrowNewIllegalArgumentExceptionWhenCurrenciesAreDifferent() throws Exception {
        Instrument instrument = new Instrument(Currencies.EU, 3);
        Account accountFrom = new Account(1, 10, new Instrument(Currencies.GBP));
        Account accountTo = new Account(2, 0, new Instrument(Currencies.EU));
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> testedObject.transferMoney(accountFrom, accountTo,
                instrument)).withMessage("Wrong currency!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenBalanceIs() throws Exception {
            Instrument instrument = new Instrument(Currencies.EU, 520);
            Account accountFrom = new Account(1, 10, new Instrument(Currencies.EU));
            Account accountTo = new Account(2, 0, new Instrument(Currencies.EU));
        testedObject.transferMoney(accountFrom, accountTo, instrument);
    }

    @Test
    public void shouldNotChangeBalanceAfterExceptionIsThrown() throws Exception {
        Instrument instrument = new Instrument(Currencies.EU, 520);
        Account accountFrom = new Account(1, 10, new Instrument(Currencies.EU));
        Account accountTo = new Account(2, 0, new Instrument(Currencies.EU));
        try {
            testedObject.transferMoney(accountFrom, accountTo, instrument);
        } catch (IllegalArgumentException ex) {
            assertThat(accountFrom.getBalance()).isEqualTo(10);
            assertThat(accountTo.getBalance()).isEqualTo(0);
        }
    }

    @Test
    public void shouldNotChangeBalanceAfterExceptionIsThrownUsingAssertJ() {
        Instrument instrument = new Instrument(Currencies.EU, 520);
        Account accountFrom = new Account(1, 10, new Instrument(Currencies.EU));
        Account accountTo = new Account(2, 0, new Instrument(Currencies.EU));
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> testedObject.transferMoney(accountFrom, accountTo, instrument))
                .withMessage("I'm very sorry but you don't have enough money...");
        assertThat(accountFrom.getBalance()).isEqualTo(10);
        assertThat(accountTo.getBalance()).isEqualTo(0);
    }

    @Test
    public void shouldReturnCorrectExceptionMessage() throws Exception {
        Instrument instrument = new Instrument(Currencies.EU, 520);
        Account accountFrom = new Account(1, 10, new Instrument(Currencies.EU));
        Account accountTo = new Account(2, 0, new Instrument(Currencies.EU));
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("I'm very sorry but you don't have enough money...");
        testedObject.transferMoney(accountFrom, accountTo, instrument);
    }
}