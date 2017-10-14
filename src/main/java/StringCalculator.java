import java.util.Arrays;

public class StringCalculator {

    public static final String EMPTY_STRING = "";

    public int Add(String numbers) throws NumberFormatException {
        if(numbers.chars().anyMatch(Character::isLetter)){
            throw new NumberFormatException("How about NO!");
        }
        String[] arrayOfNumbers = numbers.split("[,\\n]");
        if (isEmpty(numbers)) {
            return 0;
        }
        return iterateAndSum(arrayOfNumbers);
    }

    private int iterateAndSum(String[] arrayOfNumbers) {
        int result = Arrays.stream(arrayOfNumbers).mapToInt(s -> Integer.parseInt(s)).sum();
        return result;
    }

    private boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }
}
