package bank;

/**
 * Tdd training on 15.10.17.
 */
public class PaymentService {
    public void transferMoney(Account accountFrom, Account accountTo, int amountToTransfer) {
        checkIfCurrencyIsValid(accountFrom,accountTo);
        checkIfAccountBalanceIsValid(accountFrom, amountToTransfer);
        accountFrom.withdraw(amountToTransfer);
        accountTo.deposit(amountToTransfer);

    }

    private void checkIfAccountBalanceIsValid(Account accountFrom, int amountToTransfer) {
        if (accountFrom.getBalance().getAmount() - amountToTransfer < -500) {
            throw new IllegalArgumentException("I'm very sorry but you don't have enough money...");
        }
    }

    private void checkIfCurrencyIsValid(Account accountFrom,Account accountTo){
        if (accountFrom.getBalance().getCurrency()!=accountTo.getBalance().getCurrency())
            throw new IllegalArgumentException();
    }
}
