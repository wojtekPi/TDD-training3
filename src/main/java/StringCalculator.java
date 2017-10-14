public class StringCalculator {

    public static final String EMPTY_STRING = "";

    public int Add(String numbers) {
        if (isEmpty(numbers)) {
            return 0;
        } else if (numbers.length() != 1) {
            return calculation(numbers);
        } else
            return Integer.valueOf(numbers);
    }

    private int calculation(String numbers) {
        String[] eachNumber = numbers.split(",");
        int sum = 0;
        for (int i = 0; i < eachNumber.length; i++) {
            sum += Integer.valueOf(eachNumber[i]);
        }
        return sum;
    }

    private boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }
}
