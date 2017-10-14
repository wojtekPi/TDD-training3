import java.util.Arrays;
import java.util.List;

public class StringCalculator {

    public static final String EMPTY_STRING = "";

    public static int Add(String numbers) throws Exception {
        if (numbers.matches("[a-zA-Z]+")) {
            throw new NumberFormatException ("How about NO!");
        }
        if (isEmpty(numbers)) {
            return 0;
        }


        int result=0;
        String delimeter = "[,\n]";



       if ((numbers.length() >3) && (numbers.substring(0,2).equals("//"))) {
           delimeter ="[" + numbers.substring(2,3) + "\\n]";
           numbers = numbers.substring(4,numbers.length());
       }

        List<String> numberList = Arrays.asList(numbers.split(delimeter));
        for (String s : numberList) {
            result += Integer.valueOf(s);
        }
        return result;
    }

    private static boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }


}
