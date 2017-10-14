public class StringCalculator {

    public static final String EMPTY_STRING = "";

    public int Add(String numbers) {
        if (isEmpty(numbers)) {
            return 0;
        }
        int sum = 0;
        for (String number : numbers.split("[,\\n]")) {
            sum += Integer.valueOf(number);
        }
        return sum;
    }

    private boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }
}
