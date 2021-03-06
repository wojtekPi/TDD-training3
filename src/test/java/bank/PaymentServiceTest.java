package bank;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Tdd training on 15.10.17.
 */
//@RunWith(MockitoJUnitRunner.class) //Enabling initialization mocks annotated by @Mock (2)
public class PaymentServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule(); //Enabling initialization mocks annotated by @Mock (3) recommended (avaliable from version 2)

    private PaymentService testedObject;

    @Mock
    private ExchangeService exchangeServiceMock;

    @Before
    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);  //Enabling initialization mocks annotated by @Mock (1)
//        exchangeServiceMock = mock(ExchangeService.class);  //creating Mock
        testedObject = new PaymentService(exchangeServiceMock);
    }

    @Test
    public void shouldTransferMoneyWhenBalanceOnFirstAccountHasEnoughMoney() {
        Account accountFrom = new Account(1, Currency.USD, 10);
        Account accountTo = new Account(2, Currency.USD, 0);

        testedObject.transferMoney(accountFrom, accountTo, new Instrument(3, Currency.USD));

        assertThat(accountFrom.getBalance().getAmount()).isEqualTo(7);
        assertThat(accountTo.getBalance().getAmount()).isEqualTo(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenBalanceIs() throws Exception {
        Account accountFrom = new Account(1, Currency.USD, 100);
        Account accountTo = new Account(2, Currency.USD, 0);
        testedObject.transferMoney(accountFrom, accountTo, new Instrument(1000, Currency.USD));
    }

    @Test
    public void shouldNotChangeBalanceAfterExceptionIsThrown() throws Exception {
        Account accountFrom = new Account(1, Currency.USD, 100);
        Account accountTo = new Account(1, Currency.USD, 0);
        try {
            testedObject.transferMoney(accountFrom, accountTo, new Instrument(1000, Currency.USD));
        } catch (IllegalArgumentException ex) {
            assertThat(accountFrom.getBalance().getAmount()).isEqualTo(100);
            assertThat(accountTo.getBalance().getAmount()).isEqualTo(0);
        }
    }

    @Test
    public void shouldNotChangeBalanceAfterExceptionIsThrownUsingAssertJ() {
        Account accountFrom = new Account(1, Currency.USD, 100);
        Account accountTo = new Account(2, Currency.USD, 0);
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> testedObject.transferMoney(accountFrom, accountTo, new Instrument(1000, Currency.USD)))
                .withMessage("I'm very sorry but you don't have enough money...");
        assertThat(accountFrom.getBalance().getAmount()).isEqualTo(100);
        assertThat(accountTo.getBalance().getAmount()).isEqualTo(0);
    }

    @Test
    public void shouldReturnCorrectExceptionMessage() throws Exception {
        Account accountFrom = new Account(1, Currency.USD, 100);
        Account accountTo = new Account(2, Currency.USD, 0);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("I'm very sorry but you don't have enough money...");
        testedObject.transferMoney(accountFrom, accountTo, new Instrument(1000, Currency.USD));
    }

    @Test
    public void shouldThrowExeptionWhenAmmountIsInDifferentCurrency() throws Exception {
        Account accountFrom = new Account(1, Currency.USD, 100);
        Account accountTo = new Account(2, Currency.USD, 0);
        thrown.expect(IllegalArgumentException.class);
        testedObject.transferMoney(accountFrom, accountTo, new Instrument(50, Currency.PLN));
    }

    @Test
    public void shouldCallExchangeService() throws Exception {
        Account accountFrom = new Account(1, Currency.USD, 100);
        Account accountTo = new Account(2, Currency.EUR, 0);

        Instrument amountToTransfer = new Instrument(40, Currency.USD);
        when(exchangeServiceMock.convertInstrument(amountToTransfer, Currency.EUR))
                .thenReturn(new Instrument(35, Currency.EUR));

        testedObject.transferMoney(accountFrom, accountTo, amountToTransfer);

        Mockito.verify(exchangeServiceMock, times(1)).convertInstrument(amountToTransfer, Currency.EUR);
        assertThat(accountTo.getBalance().getAmount()).isEqualTo(35);
        assertThat(accountTo.getBalance().getCurrency()).isEqualTo(Currency.EUR);
        assertThat(accountFrom.getBalance().getAmount()).isEqualTo(60);
        assertThat(accountFrom.getBalance().getCurrency()).isEqualTo(Currency.USD);
    }
}