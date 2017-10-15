package bank;

/**
 * Tdd training on 15.10.17.
 */
public class PaymentService {
    public void transferMoney(Account accountFrom, Account accountTo, Instrument instrument) {
        checkIfAccountBalanceIsValid(accountFrom, instrument.getAmount());
        checkIfSameCurrency(accountFrom, accountTo);
        accountFrom.withdraw(instrument.getAmount());
        accountTo.deposit(instrument.getAmount());
    }

    private void checkIfSameCurrency(Account accountFrom, Account accountTo) {
        if (!accountFrom.getInstrument().getCurrencies().equals(accountTo.getInstrument().getCurrencies())){
            throw new IllegalArgumentException("Wrong currency!");
        }
    }

    private void checkIfAccountBalanceIsValid(Account accountFrom, int amountToTransfer) {
        if (accountFrom.getBalance() - amountToTransfer < -500) {
            throw new IllegalArgumentException("I'm very sorry but you don't have enough money...");
        }
    }
}
