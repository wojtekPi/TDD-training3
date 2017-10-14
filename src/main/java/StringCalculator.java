import java.util.Arrays;
import java.util.List;

public class StringCalculator {

    public static final String EMPTY_STRING = "";

    public int Add(String numbers) {
        if (isEmpty(numbers)) {
            return 0;
        }
        int result=0;


        List<String> numberList = Arrays.asList(numbers.split(","));
        for (String s : numberList) {
            result += Integer.valueOf(s);
        }
        return result;
    }

    private boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }

}
