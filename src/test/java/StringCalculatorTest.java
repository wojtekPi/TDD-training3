import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
    @Parameters({"0, 0", "1, 1", ", 0"})
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
                {"1,3,4,1", 9},
                {"23,14,0,1", 38},
                {"1\n2,3", 6}
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

    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWhenIncorrectFormat(){

        testedObject.Add("a");

    }

    @Test
    public void shouldThrowCorrectExceptionMessageWhenIncorrectFormat(){
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("Only numbers with commas are allowed!");
        testedObject.Add("a");
    }
}