import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tdd training on 14.10.17.
 */
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
    public void shouldReturnZeroWhenZeroIsPassed() {
        int result = testedObject.Add("0");

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldReturnOneWhenOneIsPassed() {
        int result = testedObject.Add("1");

        assertThat(result).isEqualTo(1);
    }

}