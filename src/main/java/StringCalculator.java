public class StringCalculator {

    public static final String EMPTY_STRING = "";

    public int Add(String numbers) throws NumberFormatException {

        int result = 0;
        if (isEmpty(numbers)) {
            return 0;
        }

        try {
            String[] arrayOfNumbers = numbers.split("[,\\n]");
            for (int i = 0; i < arrayOfNumbers.length; i++)
                result += Integer.valueOf(arrayOfNumbers[i]);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Only numbers with commas are allowed!");
        }
        return result;
    }

    private boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }
}
