public class StringCalculator {
    public static final String EMPTY_STRING = "";
    public int Add(String numbers) {
        if (isEmpty(numbers)) {
            return 0;
        }
        else if (numbers.length()==1)
        return Integer.valueOf(numbers);
        else if (numbers.length()==3){
            String[] tab = numbers.split(",");
            return (Integer.valueOf(tab[0])+Integer.valueOf(tab[1])) ;
        }else {
            Integer res = 0;
            String[] tab = numbers.split(",");

            for (int i = 0; i<tab.length;i++){
                res+=Integer.valueOf(tab[i]);
            }
            return res;
        }
    }
    private boolean isEmpty(String numbers) {
        return EMPTY_STRING.equals(numbers);
    }
}
