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
        Instrument instrumentFrom  = new Instrument (Currency.PLN, 10);
        Instrument instrumentTo  = new Instrument (Currency.PLN, 0);
        Account accountFrom = new Account(1, instrumentFrom);
        Account accountTo = new Account(2, instrumentTo);;
        Instrument instrument = new Instrument(Currency.PLN, 3);

        testedObject.transferMoney(accountFrom, accountTo, instrument);

        assertThat(accountFrom.getBalance()).isEqualTo(7);
        assertThat(accountTo.getBalance()).isEqualTo(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenBalanceIs() throws Exception {
        Instrument instrument = new Instrument(Currency.PLN, 1000);
        Instrument instrumentFrom  = new Instrument (Currency.PLN, 100);
        Instrument instrumentTo  = new Instrument (Currency.PLN, 0);
        Account accountFrom = new Account(1, instrumentFrom);
        Account accountTo = new Account(2, instrumentTo);
        testedObject.transferMoney(accountFrom, accountTo, instrument);
    }

    @Test
    public void shouldNotChangeBalanceAfterExceptionIsThrown() throws Exception {
        Instrument instrumentFrom  = new Instrument (Currency.PLN, 100);
        Instrument instrumentTo  = new Instrument (Currency.PLN, 0);
        Account accountFrom = new Account(1, instrumentFrom);
        Account accountTo = new Account(2, instrumentTo);
        Instrument instrument = new Instrument(Currency.PLN, 1000);
        try {
            testedObject.transferMoney(accountFrom, accountTo, instrument);
        } catch (IllegalArgumentException ex) {
            assertThat(accountFrom.getBalance()).isEqualTo(100);
            assertThat(accountTo.getBalance()).isEqualTo(0);
        }
    }

    @Test
    public void shouldNotChangeBalanceAfterExceptionIsThrownUsingAssertJ() {
        Instrument instrumentFrom  = new Instrument (Currency.PLN, 100);
        Instrument instrumentTo  = new Instrument (Currency.PLN, 0);
        Account accountFrom = new Account(1, instrumentFrom);
        Account accountTo = new Account(2, instrumentTo);
        Instrument instrument = new Instrument(Currency.PLN, 1000);
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> testedObject.transferMoney(accountFrom, accountTo, instrument))
                .withMessage("I'm very sorry but you don't have enough money...");
        assertThat(accountFrom.getBalance()).isEqualTo(100);
        assertThat(accountTo.getBalance()).isEqualTo(0);
    }

    @Test
    public void shouldReturnCorrectExceptionMessage() throws Exception {
        Instrument instrumentFrom  = new Instrument (Currency.PLN, 100);
        Instrument instrumentTo  = new Instrument (Currency.PLN, 0);
        Account accountFrom = new Account(1, instrumentFrom);
        Account accountTo = new Account(2, instrumentTo);
        Instrument instrument = new Instrument(Currency.PLN, 1000);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("I'm very sorry but you don't have enough money...");
        testedObject.transferMoney(accountFrom, accountTo, instrument);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenCurrencyOfAccountToDoesNotMatch() throws Exception {
        Instrument instrument = new Instrument(Currency.PLN, 100);
        Instrument instrumentFrom  = new Instrument (Currency.PLN, 100);
        Instrument instrumentTo  = new Instrument (Currency.USD, 0);
        Account accountFrom = new Account(1, instrumentFrom);
        Account accountTo = new Account(2, instrumentTo);
        testedObject.transferMoney(accountFrom, accountTo, instrument);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenCurrencyOfAccountFromDoesNotMatch() throws Exception {
        Instrument instrument = new Instrument(Currency.USD, 100);
        Instrument instrumentFrom  = new Instrument (Currency.PLN, 100);
        Instrument instrumentTo  = new Instrument (Currency.USD, 0);
        Account accountFrom = new Account(1, instrumentFrom);
        Account accountTo = new Account(2, instrumentTo);
        testedObject.transferMoney(accountFrom, accountTo, instrument);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenCurrencyOfInstrumentDoesNotMatch() throws Exception {
        Instrument instrument = new Instrument(Currency.PLN, 100);
        Instrument instrumentFrom  = new Instrument (Currency.GBP, 100);
        Instrument instrumentTo  = new Instrument (Currency.GBP, 0);
        Account accountFrom = new Account(1, instrumentFrom);
        Account accountTo = new Account(2, instrumentTo);
        testedObject.transferMoney(accountFrom, accountTo, instrument);
    }
}