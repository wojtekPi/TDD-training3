public class StringCalculator {

    public static final String EMPTY_STRING = "";

    public int Add(String numbers) {
        if (isEmpty(numbers)) {
            return 0;
        }
        return Integer.valueOf(numbers);
    }

    private boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }
}
