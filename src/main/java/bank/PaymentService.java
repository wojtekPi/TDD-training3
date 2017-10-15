package bank;

/**
 * Tdd training on 15.10.17.
 */
public class PaymentService {
    public void transferMoney(Account accountFrom, Account accountTo, Instrument instrument) {
        checkIfAccountBalanceIsValid(accountFrom, instrument.amount);
        checkIfAccountCurrencyIsValid(accountFrom, accountTo, instrument);
        accountFrom.withdraw(instrument.amount);
        accountTo.deposit(instrument.amount);

    }

    private void checkIfAccountBalanceIsValid(Account accountFrom, int amountToTransfer) {
        if (accountFrom.getBalance() - amountToTransfer < -500) {
            throw new IllegalArgumentException("I'm very sorry but you don't have enough money...");
        }
    }

    private void checkIfAccountCurrencyIsValid(Account accountFrom, Account accountTo, Instrument instrument) {
        if ((accountFrom.getCurrency() != accountTo.getCurrency()) || (instrument.currency != accountFrom.getCurrency()))
            throw new IllegalArgumentException("Currencies do not match!");
    }
}
