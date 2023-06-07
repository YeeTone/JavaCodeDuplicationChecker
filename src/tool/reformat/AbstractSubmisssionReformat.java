package tool.reformat;

public abstract class AbstractSubmisssionReformat {

    protected String baseDirectoryName;
    protected String targetDirectoryName;

    public AbstractSubmisssionReformat(String baseDirectoryName, String targetDirectoryName) {
        this.baseDirectoryName = baseDirectoryName;
        this.targetDirectoryName = targetDirectoryName;
    }

    public abstract void reformat();
}
