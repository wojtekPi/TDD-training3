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
        Instrument validMoneyToTransfer = convertCurrency(accountFrom, accountTo, amountToTransfer);
        checkIfAccountBalanceIsValid(accountFrom, validMoneyToTransfer.getAmount());
        accountFrom.withdraw(amountToTransfer.getAmount());
        accountTo.deposit(validMoneyToTransfer.getAmount());

    }

    private void checkIfAccountBalanceIsValid(Account accountFrom, int amountToTransfer) {
        if (accountFrom.getBalance().getAmount() - amountToTransfer < -500) {
            throw new IllegalArgumentException("I'm very sorry but you don't have enough money...");
        }
    }

    private Instrument convertCurrency(Account accountFrom, Account accountTo, Instrument instrument) {
        Currency fromCurr = accountFrom.getBalance().getCurrency();
        Currency toCurr = accountTo.getBalance().getCurrency();
        Currency amountCurr = instrument.getCurrency();
        if (fromCurr != amountCurr) throw new IllegalArgumentException();
        else if (amountCurr == toCurr) return instrument;
        else {   // fromCurr == amountCurr && amountCurr != toCurr
            return moneyExchange(instrument, accountTo.getBalance().getCurrency());
        }
    }

    private Instrument moneyExchange(Instrument instrument, Currency currency) {
        return exchangeServiceMock.convertInstrument(instrument, currency);
    }
}
