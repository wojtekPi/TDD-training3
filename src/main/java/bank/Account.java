package bank;

/**
 * Tdd training on 15.10.17.
 */
public class Account {
    private int id;
    private Instrument instrument = new Instrument(Currency.PLN, 0);

    public Account(int id, Instrument instrument) {
        this.id = id;
        this.instrument = instrument;
    }

    public int getBalance() {
        return instrument.amount;
    }

    public void withdraw(int amount) {
        instrument.amount -= amount;
    }

    public void deposit(int amount) {
        instrument.amount += amount;
    }

    public Currency getCurrency() {
        return instrument.currency;
    }
}
