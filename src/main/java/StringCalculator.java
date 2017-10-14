import java.util.StringTokenizer;

public class StringCalculator {

    public static final String EMPTY_STRING = "";

    public int Add(String numbers)  {
        if (isEmpty(numbers)) {
            return 0;
        }
        int result = 0;

        if(checkIfAnyLettersContain(numbers))
            throw new NumberFormatException("Not allowed");



        String[] tabString = numbers.split("[\n,;//]");

        for(int i = 0; i < tabString.length; i++) {
                result += Integer.valueOf(tabString[i]);

        }
        return result;
    }

    private boolean checkIfAnyLettersContain(String anyLetter) {
        if(anyLetter.chars().anyMatch(Character::isLetter))
            return true;
        else return false;

    }

    private boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }

}
