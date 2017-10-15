package bank;

public class Instrument {

    Currency currency = Currency.PLN;
    int amount;

    public Instrument(Currency currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }
}
