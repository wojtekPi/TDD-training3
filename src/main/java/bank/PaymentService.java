package bank;

/**
 * Tdd training on 15.10.17.
 */
public class PaymentService {
    public void transferMoney(Account accountFrom, Account accountTo, int amountToTransfer) {
        accountFrom.withdraw(amountToTransfer);
        accountTo.deposit(amountToTransfer);
    }
}
