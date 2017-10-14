import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tdd training on 14.10.17.
 */
@RunWith(JUnitParamsRunner.class)
public class StringCalculatorTest {

    private StringCalculator testedObject;

    @Before
    public void setUp() throws Exception {
        testedObject = new StringCalculator();
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
    }

    @Test
    public void shouldReturnZeroWhenEmptyStringPassed() {
        int result = testedObject.Add("");

        assertThat(result).isEqualTo(0);
    }

    @Test
    @Parameters({"0,0", "1,1", ",0"})
    public void shouldReturnCorrectValueWhenOneNumberPassed(String input,
                                                            int expectedOutput) {
        int result = testedObject.Add(input);

        assertThat(result).isEqualTo(expectedOutput);
    }

    private Object[][] parametersForTestingNonStandardInput() {
        return new Object[][]{
                {"", 0},
                {"1", 1},
                {"1,2", 3},
                {"1,2,3,4", 10}

        };
    }

    @Test
    @Parameters(method = "parametersForTestingNonStandardInput")
    public void shouldReturnCorrectValueWhenNonStandardInputPassed(String input, int expectedOutput) {
        int result = testedObject.Add(input);

        assertThat(result).isEqualTo(expectedOutput);
    }


}

//import junitparams.JUnitParamsRunner;
//import junitparams.Parameters;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * Tdd training on 14.10.17.
// */
//@RunWith(JUnitParamsRunner.class)
//
//public class StringCalculatorTest {
//
//    private StringCalculator testedObject;
//
//    @Before
//    public void setUp() throws Exception {
//        testedObject = new StringCalculator();
//    }
//
//    @Test
//    public void shouldCreateObject() throws Exception {
//        assertThat(testedObject).isNotNull();
//    }
//
//    @Test
//    public void shouldReturnZeroWhenEmptyStringPassed()
//    {
//        int result = testedObject.Add("");
//
//        assertThat(result).isEqualTo(0);
//    }
//
//    @Test
//    public void shouldReturnZeroWhenZeroIsPassed()
//    {
//        int result = testedObject.Add("0");
//
//        assertThat(result).isEqualTo(0);
//    }
//
//    @Test
//    public void shouldReturnZeroWhenOneIsPassed()
//    {
//        int result = testedObject.Add("1");
//
//        assertThat(result).isEqualTo(1);
//
//
//       // assertEquals(result).isEqualTo(1);
//    }
//
//    @Test
//    @Parameters({"0,0", "1,1", ",0"})
//    public void shouldReturnCorrectValueWhenOneNumberPassed(String input, int expectedOutput)
//    {
//        int result = testedObject.Add(input);
//
//        assertThat(result).isEqualTo(expectedOutput);
//    }
//
//    private Object[][] parametersForTestingNonStandardInput()
//    {
//        return new Object[][]
//                {
//                        {"", 0},
//                        {"1", 1},
//                        {"1,2,3", 6}
//                };
//    }
//
////    @Test
////    @Parameters({"0,0", "1,1", ",0"})
////    public void shouldReturnCorrectValueWhenNumbersAdded(int input, int output)
////    {
////        int result = testedObject.Add(1, 1);
////
////        assertThat(result).isEqualTo(result);
////    }
////
////    private Object[][] parametersForTestingNonStandardInput()
////    {
////        return new Object[][]
////                {
////                        {"", 0},
////                        {"1", 1},
////                };
////    }
//
//
//
//
//
//
//    @Test
//    @Parameters(method = "parametersForTestingNonStandardInput")
//    public void shouldReturnCorrectValueWhenNonStandardInputPassed(
//            String input, int expectedOutput)
//    {
//        int result = testedObject.Add(input);
//
//        assertThat(result).isEqualTo(expectedOutput);
//    }
//
//
//
//}