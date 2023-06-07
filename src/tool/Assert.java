package tool;

public class Assert {

    public static void assertion(boolean shouldBeTrue){
        if(!shouldBeTrue){
            throw new AssertionError("Assertion Failure!");
        }
    }
}
