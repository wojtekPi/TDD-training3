import junitparams.JUnitParamsRunner;

import org.junit.runner.RunWith;
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
                {"1,8", 9},
                {"14,98", 112},
                {"14,15,29", 58},
                {"41,1,12",54}

        };
    }

    @Test
    @Parameters(method = "parametersForTestingNonStandardInput")
    public void shouldReturnCorrectValueWhenNonStandardInputPassed(
            String input, int expectedOutput
    ) {
        int result = testedObject.Add(input);

        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    @Parameters(method = "parametersForTestingNonStandardInput" )
    public void shouldReturnCorrectValueWhenStandardInputPassed(String input,
                                                            int expectedOutput) {
        int result = testedObject.Add(input);
        assertThat(result).isEqualTo(expectedOutput);
    }
    @Test
    @Parameters(method = "parametersForTestingNonStandardInput" )
    public void shouldReturnCorrectValueWhenMoreThatTwoInputPassed(String input,
                                                                int expectedOutput) {
        int result = testedObject.Add(input);
        assertThat(result).isEqualTo(expectedOutput);
    }


}