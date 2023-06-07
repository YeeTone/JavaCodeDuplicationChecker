package checker;

import jplag.ExitException;
import jplag.Program;
import jplag.options.CommandLineOptions;
import jplag.options.Options;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static checker.CopyAndRewrite.copyRewrite;
import static checker.CopyAndRewrite.ensureDirectory;

public class JavaCodeDuplicationChecker {
    public static void main(String[] args) throws ExitException, IOException {
        GlobalConfiguration.init(args);
        ensureDirectory("./reformattedCode");
        copyRewrite();
        runJPlag();
    }
    private static void runJPlag() throws ExitException {
        String path = String.valueOf(Path.of(".", "reformattedCode"));
        Options o = new CommandLineOptions(new String[]{path}, null);
        Program p = new Program(o);
        o.result_dir += File.separator + GlobalConfiguration.target();
        p.run();
        p.closeWriter();

    }
}
