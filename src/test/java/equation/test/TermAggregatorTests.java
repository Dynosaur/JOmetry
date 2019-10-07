package equation.test;

import equation.TermAggregator.SymbolType;
import symbol.Symbol;
import symbol.constant.FloatingPoint;
import symbol.constant.Integer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static equation.TermAggregator.SymbolType.*;

public class TermAggregatorTests {

    @BeforeMethod public void setUp() {
        rsrc.Resources.loadResources();
    }

    public void testDetermineSymbolType(Symbol input, SymbolType expected) {
        SymbolType actual = equation.TermAggregator.determineSymbolType(input);

        Assert.assertEquals(expected, actual);
    }

    @Test public void test_determineSymbolType_Integer() {
        testDetermineSymbolType(new Integer(1), CONSTANT);
        testDetermineSymbolType(new Integer("1"), CONSTANT);
        testDetermineSymbolType(new Integer("100000"), CONSTANT);
        testDetermineSymbolType(new FloatingPoint(1), CONSTANT);
    }



}
