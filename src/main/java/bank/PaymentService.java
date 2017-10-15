package bank;

/**
 * Tdd training on 15.10.17.
 */
public class PaymentService {

    public void transferMoney(Account accountFrom, Account accountTo, int amountToTransfer) throws IllegalArgumentException {
        if (isBalanceToLow(accountFrom, amountToTransfer)) {
            accountFrom.withdraw(amountToTransfer);
            accountTo.deposit(amountToTransfer);
        } else
            throw new IllegalArgumentException("I'm very sorry, but you dont;t have enough money...");
    }

    private boolean isBalanceToLow(Account account, int amountOfMoney) {
        if ((account.getBalance() - amountOfMoney) < -500)
            return false;
        else
            return true;
    }
}
