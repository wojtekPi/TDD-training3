import java.util.Arrays;

public class StringCalculator {

    public static final String EMPTY_STRING = "";

    public int Add(String numbers) throws NumberFormatException {
        checkIfContrainsOnlyNumbers(numbers);
        String[] arrayOfNumbers = numbers.split("[,\\n]");
        if (isEmpty(numbers)) {
            return 0;
        }
        return iterateAndSum(arrayOfNumbers);
    }

    private void checkIfContrainsOnlyNumbers(String numbers) {
        if (!numbers.contains("[0-9]+")) {
            throw new NumberFormatException("How about NO!");
        }
    }

    private int iterateAndSum(String[] arrayOfNumbers) {
        int result = Arrays.stream(arrayOfNumbers).mapToInt(s -> Integer.parseInt(s)).sum();
        return result;
    }

    private boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }
}
