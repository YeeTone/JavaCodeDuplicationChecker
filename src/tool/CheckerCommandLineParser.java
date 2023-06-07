package tool;

import org.apache.commons.cli.*;

class CheckerCommandLineParser {
    private final String[] args;
    private final CommandLineParser parser;
    private final HelpFormatter formatter;
    private final Options options;

    public CheckerCommandLineParser(String[] argv) {
        args = argv;
        parser = new DefaultParser();
        formatter = new HelpFormatter();
        options = new Options();
        addOptions();
    }

    public String base() {
        return getRequiredOption("base");
    }

    public String source(){
        return getRequiredOption("source");
    }

    public String target() {
        return getRequiredOption("target");
    }

    public String output() {
        return getOptionOrDefault("output", "result");
    }

    public boolean all(){
        return parseCommandLine().hasOption("all");
    }

    private CommandLine parseCommandLine() {
        try {
            return parser.parse(options, args);
        } catch (ParseException pe) {
            formatter.printHelp("CommandLine Example:", options);
            throw new RuntimeException(pe);
        }
    }

    private String getRequiredOption(String target) {
        return parseCommandLine().getOptionValue(target);
    }

    private String getOptionOrDefault(String target, String defaultValue) {
        if (parseCommandLine().hasOption(target)) {
            return parseCommandLine().getOptionValue(target);
        }
        return defaultValue;
    }

    private void addOptions() {
        Option base = new Option("b", "base", true, "base directory");
        base.setRequired(true);

        Option target = new Option("t", "target", true, "result target file");
        target.setRequired(true);

        Option output = new Option("o", "output", true, "result output file");
        output.setRequired(false);

        Option all = new Option("a", "all", false, "check all problem subDirectories");
        all.setRequired(false);

        options.addOption(base);
        options.addOption(target);
        options.addOption(output);
        options.addOption(all);
    }
}
