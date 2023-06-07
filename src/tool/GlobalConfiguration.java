package tool;

public class GlobalConfiguration {

    private static CheckerCommandLineParser parser;

    public static void init(String[] args){
        parser = new CheckerCommandLineParser(args);
    }

    public static String base(){
        return parser.base();
    }

    public static String target() {
        return parser.target();
    }

    public static String output(){
        return parser.output();
    }

    public static boolean all(){
        return parser.all();
    }

    private GlobalConfiguration(){
        throw new RuntimeException("No instance allowed!");
    }
}
