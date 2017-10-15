package bank;

/**
 * Tdd training on 15.10.17.
 */
public class PaymentService {
    private final ExchangeService exchangeServiceMock;

    public PaymentService(ExchangeService exchangeServiceMock) {
        this.exchangeServiceMock = exchangeServiceMock;
    }

    public void transferMoney(Account accountFrom, Account accountTo, Instrument amountToTransfer) {
        checkIfCurrencyIsValid(accountFrom, accountTo, amountToTransfer);
        checkIfAccountBalanceIsValid(accountFrom, amountToTransfer.getAmount());
        Instrument exchangedAmountToTransfer = amountToTransfer;
        if (accountTo.getBalance().getCurrency()!=amountToTransfer.getCurrency()){
            ExchangeService exchangeService = exchangeServiceMock;
            exchangedAmountToTransfer = exchangeService.convertInstrument(amountToTransfer, accountTo.getBalance().getCurrency());
        }
        accountFrom.withdraw(amountToTransfer.getAmount());
        accountTo.deposit(exchangedAmountToTransfer.getAmount());

    }

    private void checkIfAccountBalanceIsValid(Account accountFrom, int amountToTransfer) {
        if (accountFrom.getBalance().getAmount() - amountToTransfer < -500) {
            throw new IllegalArgumentException("I'm very sorry but you don't have enough money...");
        }
    }

    private void checkIfCurrencyIsValid(Account accountFrom, Account accountTo, Instrument instrument) {
        if ( accountFrom.getBalance().getCurrency() != instrument.getCurrency())
            throw new IllegalArgumentException();
    }
}
