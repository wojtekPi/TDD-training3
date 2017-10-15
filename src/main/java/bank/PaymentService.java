package bank;

/**
 * Tdd training on 15.10.17.
 */
public class PaymentService {
    public void transferMoney(Account accountFrom, Account accountTo, int amountToTransfer) {
        if (isEnouthMoney(accountFrom, amountToTransfer)) {
            accountFrom.withdraw(amountToTransfer);
            accountTo.deposit(amountToTransfer);
        } else throw new IllegalArgumentException("I'm very sorry, but you don't have enough money...");
    }

    private boolean isEnouthMoney(Account accountFrom, int amountToTransfer) {
        return accountFrom.getBalance() >= amountToTransfer;
    }
}
