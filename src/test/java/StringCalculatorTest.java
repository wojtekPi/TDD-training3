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
    public ExpectedException expectedException = ExpectedException.none();

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
    private Object[][] parametersForTestingNonStandardInputt() {
        return new Object[][]{
                {"1",1},
                {"2",2},
                {"1,1,31,1,1,41",76},
                {"1,3\n",4},
                {"3\n3\n4",10}
        };
    }

    @Test
    @Parameters(method = "parametersForTestingNonStandardInputt")
    public void shouldReturnCorrectValueWhenNonStandardInputPassed(
            String input, int expectedOutput
    ) {
        int result = testedObject.Add(input);

        assertThat(result).isEqualTo(expectedOutput);
    }


    @Test
    public void shouldThrowNumberFormatExceptionWhenParameterIsStupid() throws Exception {
        expectedException.expect(NumberFormatException.class);
        expectedException.expectMessage("How about NO!");
        testedObject.Add("W");
    }


}