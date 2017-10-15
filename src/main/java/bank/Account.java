package bank;

/**
 * Tdd training on 15.10.17.
 */
public class Account {
    private int id;
    private Instrument balance;


    public Account(int id, Currency currency, int ammount) {
        this.id = id;
        this.balance = new Instrument(ammount,currency);
    }

    public Instrument getBalance() {
        return balance;
    }

    public void withdraw(int amount) {

        balance.setAmount(balance.getAmount()-amount);
    }

    public void deposit(int amount) {
        balance.setAmount(balance.getAmount()+amount);
    }
}
