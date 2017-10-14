import java.util.StringTokenizer;

public class StringCalculator {

    public static final String EMPTY_STRING = "";

    public int Add(String numbers) {
        if (isEmpty(numbers)) {
            return 0;
        }
        int result = 0;
        String[] tabString = numbers.split("\n");

        for(int i = 0; i < tabString.length; i++) {
            result += calculate(tabString[i], ",");
        }

        return result;
    }

    private boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }

    private int calculate(String numbers, String regex) {
        String[] tab = numbers.split(regex);
        int result = 0;

        for(int i = 0; i < tab.length; i++){
            result += Integer.valueOf(tab[i]);
        }

        return result;
    }
}
