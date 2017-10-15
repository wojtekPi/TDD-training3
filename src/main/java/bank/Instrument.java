package bank;

public class Instrument {
    private Currencies currencies;
    private int amount;

    public Currencies getCurrencies() {
        return currencies;
    }

    public Instrument(Currencies currencies) {
        this.currencies = currencies;
    }

    public int getAmount() {
        return amount;
    }

    public Instrument(Currencies currencies, int amount) {
        this.currencies = currencies;
        this.amount = amount;
    }
}
