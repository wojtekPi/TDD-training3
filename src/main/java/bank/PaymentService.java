package bank;

/**
 * Tdd training on 15.10.17.
 */
public class PaymentService {
    public void transferMoney(Account accountFrom,
                              Account accountTo,
                              int amountToTransfer) {
        if (isTransferPossible(accountFrom,amountToTransfer)) {
            accountFrom.withdraw(amountToTransfer);
            accountTo.deposit(amountToTransfer);
        }
    }

    public boolean isTransferPossible(Account accountFrom, int amountToTransfer) throws IllegalArgumentException {
        if ((accountFrom.getBalance()-amountToTransfer)>-501) {
            return true;
        } else throw new IllegalArgumentException("You no understand me, " +
                "your balance is exceeded to much beyond -500");
    }

}
