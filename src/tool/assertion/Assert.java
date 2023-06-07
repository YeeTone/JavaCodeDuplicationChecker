package tool.assertion;

public class Assert {

    public static void assertion(boolean shouldBeTrue){
        if(!shouldBeTrue){
            throw new AssertionError("Assertion Failure!");
        }
    }
}
