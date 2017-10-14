import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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

    private Object[][] parametersForTestingNonStandardInput() {
        return new Object[][]{
                {"", 0},
                {"0", 0},
                {"1", 1},
                {"2", 2},
                {"1,2,3,4,5,6", 21},
                {"1\n2,3,4,5,6", 21},
                {"//;\n3;5;6;7", 21},
        };
    }

    @Test
    @Parameters(method = "parametersForTestingNonStandardInput")
    public void shouldReturnCorrectValueWhenNonStandardInputPassed(
            String input, int expectedOutput
    ) throws Exception {
        int result = testedObject.Add(input);

        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    public void shouldReturnSumOfTwoElements() throws Exception {
        int result = testedObject.Add("2,4");
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void shouldReturnSumOfThreeElements() throws Exception {
        int result = testedObject.Add("2,4,35");
        assertThat(result).isEqualTo(41);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldThrowNumberFormatExceptionWhenParameterIsStupid() throws Exception {
        expectedException.expect(NumberFormatException.class);
        expectedException.expectMessage("How about NO!");
        testedObject.Add("W");
    }
}