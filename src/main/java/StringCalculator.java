public class StringCalculator {

    public static final String EMPTY_STRING = "";

    public int Add(String numbers) {
        if (isEmpty(numbers)) {
            return 0;
        } else if (isValid(numbers) && numbers.length() != 1) {
            return calculation(numbers);
        } else
            return Integer.valueOf(numbers);
    }

    private int calculation(String numbers) {
        int sum = 0;

        for (String number : numbers.split("[,\n]")) {
            sum += Integer.parseInt(number);
        }

        return sum;
    }

    private boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }

    private boolean isValid (String numbers) {
        for (String number: numbers.split("[,\n]")) {
          try {
              Integer.parseInt(number);
          } catch (NumberFormatException e) {
              throw new NumberFormatException("Only numbers and comas expected!");
          }
        }
        return true;
    }

}
