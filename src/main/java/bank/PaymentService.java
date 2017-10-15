package bank;

/**
 * Tdd training on 15.10.17.
 */
public class PaymentService {
    private final ExchangeService exchangeService;

    public PaymentService(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    public void transferMoney(Account accountFrom, Account accountTo, Instrument amountToTransfer) {
        checkIfCurrencyIsValid(accountFrom, accountTo, amountToTransfer);
        checkIfAccountBalanceIsValid(accountFrom, amountToTransfer.getAmount());
        Instrument exchangedAmountToTransfer = amountToTransfer;
        if (isExchangeRequired(accountTo, amountToTransfer)) {
            exchangedAmountToTransfer = exchangeService.convertInstrument(amountToTransfer, accountTo.getBalance().getCurrency());
        }
        accountFrom.withdraw(amountToTransfer.getAmount());
        accountTo.deposit(exchangedAmountToTransfer.getAmount());

    }

    private boolean isExchangeRequired(Account accountTo, Instrument amountToTransfer) {
        return accountTo.getBalance().getCurrency() != amountToTransfer.getCurrency();
    }

    private void checkIfAccountBalanceIsValid(Account accountFrom, int amountToTransfer) {
        if (accountFrom.getBalance().getAmount() - amountToTransfer < -500) {
            throw new IllegalArgumentException("I'm very sorry but you don't have enough money...");
        }
    }

    private void checkIfCurrencyIsValid(Account accountFrom, Account accountTo, Instrument instrument) {
        if (accountFrom.getBalance().getCurrency() != instrument.getCurrency())
            throw new IllegalArgumentException();
    }
}
