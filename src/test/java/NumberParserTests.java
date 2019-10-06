import java.util.ArrayList;
import equation.NumberParser.AggregateType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.util.Arrays.asList;
import static equation.NumberParser.AggregateType.*;

public class NumberParserTests {

    static {
        rsrc.Resources.loadResources();
    }

    public void testDetermineAggregateType(Object input, AggregateType expected) {
        final AggregateType actual = equation.NumberParser.determineAggregateType(input);

        try {
            Assert.assertEquals(actual, expected);
        } catch(java.lang.AssertionError e) {
            e.printStackTrace();
            System.err.printf("For input %s (Code: %s)", input, input);
            System.exit(1);
        }
    }

    @Test public void test_determineAggregateType_LegalText() {
        testDetermineAggregateType('a', LEGAL_TEXT);
        testDetermineAggregateType("b", LEGAL_TEXT);
        testDetermineAggregateType('x', LEGAL_TEXT);
        testDetermineAggregateType("y", LEGAL_TEXT);
        testDetermineAggregateType("z", LEGAL_TEXT);
        testDetermineAggregateType('+', LEGAL_TEXT);
        testDetermineAggregateType("-", LEGAL_TEXT);
        testDetermineAggregateType("*", LEGAL_TEXT);
        testDetermineAggregateType("/", LEGAL_TEXT);
        testDetermineAggregateType('^', LEGAL_TEXT);
    }

    @Test public void test_determineAggregateType_IllegalText() {
        testDetermineAggregateType('`', ILLEGAL_TEXT);
        testDetermineAggregateType('~', ILLEGAL_TEXT);
        testDetermineAggregateType('@', ILLEGAL_TEXT);
        testDetermineAggregateType('#', ILLEGAL_TEXT);
        testDetermineAggregateType('\'', ILLEGAL_TEXT);
        testDetermineAggregateType('\"', ILLEGAL_TEXT);
        testDetermineAggregateType(';', ILLEGAL_TEXT);
        testDetermineAggregateType(':', ILLEGAL_TEXT);
    }

    @Test public void test_determineAggregateType_Whitespace() {
        testDetermineAggregateType(' ', WHITE_SPACE);
        testDetermineAggregateType('\n', WHITE_SPACE);
        testDetermineAggregateType('\r', WHITE_SPACE);
        testDetermineAggregateType('\t', WHITE_SPACE);
    }

    @Test public void test_determineAggregateType_Number() {
        testDetermineAggregateType(1, NUMBER);
        testDetermineAggregateType(-1, NUMBER);
        testDetermineAggregateType(0.0625, NUMBER);
        testDetermineAggregateType("0.0625", NUMBER);
        testDetermineAggregateType("1000000", NUMBER);
        testDetermineAggregateType("-1", NUMBER);
        testDetermineAggregateType('0', NUMBER);
    }

    @Test public void test_determineAggregateType_DecimalPoint() {
        testDetermineAggregateType('.', DECIMAL_POINT);
        testDetermineAggregateType(".", DECIMAL_POINT);
    }

    public void testDetermineAggregateTypeArray(char[] input, AggregateType[] expected) {
        final ArrayList<AggregateType> actual = equation.NumberParser.determineAggregateTypeArray(input);

        Assert.assertEquals(actual.toArray(new AggregateType[0]), expected);
    }

    @Test public void test_determineAggregateTypeArray_LegalText() {
        testDetermineAggregateTypeArray(new char[] {'a', 'b', 'c', 'd', 'e'}, new AggregateType[] {LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT});
        testDetermineAggregateTypeArray(new char[] {'f', 'g', 'h', 'i', 'j'}, new AggregateType[] {LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT});
        testDetermineAggregateTypeArray(new char[] {'k', 'l', 'm', 'n', 'o'}, new AggregateType[] {LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT});
        testDetermineAggregateTypeArray(new char[] {'p', 'q', 'r', 's', 't'}, new AggregateType[] {LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT});
        testDetermineAggregateTypeArray(new char[] {'u', 'v', 'w', 'x', 'y'}, new AggregateType[] {LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT});
        testDetermineAggregateTypeArray(new char[] {'z', '+', '-', '*', '/'}, new AggregateType[] {LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT});
        testDetermineAggregateTypeArray(new char[] {'[', ']', '{', '}', '('}, new AggregateType[] {LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT});
        testDetermineAggregateTypeArray(new char[] {'(', ')', '*'}, new AggregateType[] {LEGAL_TEXT, LEGAL_TEXT, LEGAL_TEXT});
    }

    @Test public void test_determineAggregateTypeArray_IllegalText() {
        testDetermineAggregateTypeArray(new char[] {'`', '~', '@', '#', '$'}, new AggregateType[] {ILLEGAL_TEXT, ILLEGAL_TEXT, ILLEGAL_TEXT, ILLEGAL_TEXT, ILLEGAL_TEXT});
        testDetermineAggregateTypeArray(new char[] {'|', '\'', '\"', ';', ':'}, new AggregateType[] {ILLEGAL_TEXT, ILLEGAL_TEXT, ILLEGAL_TEXT, ILLEGAL_TEXT, ILLEGAL_TEXT});
        testDetermineAggregateTypeArray(new char[] {'?', ',', '_'}, new AggregateType[] {ILLEGAL_TEXT, ILLEGAL_TEXT, ILLEGAL_TEXT});
    }

    @Test public void test_determineAggregateTypeArray_Number() {
        testDetermineAggregateTypeArray(new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}, new AggregateType[] {NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER});
    }

    public void testAggregateNumbers(String input, String[] expected) {
        final ArrayList<String> actual = equation.NumberParser.aggregateNumbers(input);

        Assert.assertEquals(asList(expected), actual);
    }

    @Test public void test_aggregateNumbers() {
        testAggregateNumbers("y = mx + b", new String[] {"y", "=", "m", "x", "+", "b"});
        testAggregateNumbers("0 = 0x + 0", new String[] {"0", "=", "0", "x", "+", "0"});
        testAggregateNumbers("10 + 2.01x = 5y - 3.3a", new String[] {"10", "+", "2.01", "x", "=", "5", "y", "-", "3.3", "a"});
    }

}
