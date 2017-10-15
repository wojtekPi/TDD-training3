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
        if(checkIfThereIsNeedToExchangeCurrency(accountFrom, accountTo, amountToTransfer))
        {
            TransferMoneyWithChangingCurrency(accountFrom, accountTo, amountToTransfer);
        } else {
            accountFrom.withdraw(amountToTransfer.getAmount());
            accountTo.deposit(amountToTransfer.getAmount());
        }

    }

    private void TransferMoneyWithChangingCurrency(Account accountFrom, Account accountTo, Instrument amountToTransfer) {
        accountFrom.withdraw(amountToTransfer.getAmount());
        amountToTransfer = exchangeServiceMock.convertInstrument(amountToTransfer, accountTo.getBalance().getCurrency());
        accountTo.deposit(amountToTransfer.getAmount());
    }

    private void checkIfAccountBalanceIsValid(Account accountFrom, int amountToTransfer) {
        if (accountFrom.getBalance().getAmount() - amountToTransfer < -500) {
            throw new IllegalArgumentException("I'm very sorry but you don't have enough money...");
        }
    }

    private void checkIfCurrencyIsValid(Account accountFrom, Account accountTo, Instrument instrument) {
        if (accountFrom.getBalance().getCurrency() != instrument.getCurrency()
                || ((accountFrom.getBalance().getCurrency() != accountTo.getBalance().getCurrency()) && accountFrom.getBalance().getCurrency() != instrument.getCurrency()))
            throw new IllegalArgumentException();
    }

    private boolean checkIfThereIsNeedToExchangeCurrency(Account accountFrom, Account accountTo, Instrument instrument) {
        if (accountFrom.getBalance().getCurrency() != accountTo.getBalance().getCurrency()) {
            return true;
        } else return false;
    }
}
