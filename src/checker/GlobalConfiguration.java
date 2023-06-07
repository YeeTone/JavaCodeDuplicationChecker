package checker;

import tool.cli.CheckerCommandLineParser;

class GlobalConfiguration {

    private static CheckerCommandLineParser parser;

    private GlobalConfiguration(){
        throw new RuntimeException("No instance allowed!");
    }

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


}
