package bank;

/**
 * Tdd training on 15.10.17.
 */
public class Account {
    private int id;
    private int balance;
    private Instrument instrument;

    public Account(int id, int balance,Instrument instrument) {
        this.id = id;
        this.balance = balance;
        this.instrument=instrument;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance-= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public Instrument getInstrument() {
        return instrument;
    }
}
