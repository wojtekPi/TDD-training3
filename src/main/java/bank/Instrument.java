package bank;

public class Instrument {
   private int amount;
   private Currency currency;

    public Instrument(int amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
