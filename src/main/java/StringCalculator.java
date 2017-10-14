public class StringCalculator {

    public static final String EMPTY_STRING = "";

    public int Add(String numbers) {

        int result = 0;
        if (isEmpty(numbers)) {
            return 0;
        }
        
        String[] arrayOfNumbers = numbers.split(",");
        for (int i = 0; i < arrayOfNumbers.length; i++) {
            result += Integer.valueOf(arrayOfNumbers[i]);
        }
        return result;
    }

    private boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }
}
