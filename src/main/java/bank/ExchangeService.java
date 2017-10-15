package bank;

/**
 * Tdd training on 15.10.17.
 */
public interface ExchangeService {

    Instrument convertInstrument(Instrument moneyToConvert,Currency currency);
}
