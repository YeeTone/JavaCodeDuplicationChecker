package tool.assertion;

import org.junit.jupiter.api.Test;
import tool.assertion.Assert;

import static org.junit.jupiter.api.Assertions.fail;

public class AssertTest {
    @Test
    public void assertionTestTrue(){
        Assert.assertion(true);
    }

    @Test
    public void assertionTestFalse(){
        try{
            Assert.assertion(false);
            fail("Assertion Failure");
        }catch (AssertionError ignored){

        }

    }
}
