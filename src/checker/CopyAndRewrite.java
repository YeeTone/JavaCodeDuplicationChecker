package checker;

import tool.GlobalConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Objects;

import static tool.Assert.assertion;

public class CopyAndRewrite {
    private CopyAndRewrite(){
        throw new RuntimeException("No instance allowed!");
    }

    public static void ensureDirectory(String dir) throws IOException {
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }

        if (dirFile.exists() && !dirFile.isDirectory()) {
            Files.delete(dirFile.toPath());
            dirFile.mkdir();
        }

        // reference: https://blog.csdn.net/qq_33965490/article/details/123558096

        Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                //遍历文件时删除文件
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dire, IOException exc) throws IOException {
                //遍历目录时删除目录
                if (!dire.equals(Paths.get(dir))) {
                    Files.delete(dire);
                }

                return super.postVisitDirectory(dire, exc);
            }
        });
    }

    public static boolean copyRewrite(){
        File baseDirectory = new File(GlobalConfiguration.base());
        assertion(baseDirectory.exists());
        assertion(baseDirectory.isDirectory());
        Arrays.stream(Objects.requireNonNull(baseDirectory.listFiles()))
                .forEach(sid -> {
                    File targetDirectory = new File(sid.getAbsoluteFile().getAbsolutePath()
                    +File.separator+GlobalConfiguration.target());
                    Arrays.stream(Objects.requireNonNull(targetDirectory.listFiles()))
                            .forEach(code -> {
                                reformatCopy(sid.getName(), code);
                            });
                });
        return true;
    }

    private static void reformatCopy(String sid, File origin){
        File f = new File(String.format("%s/%s_%s_%s","./reformattedCode", sid,
                GlobalConfiguration.target(), origin.getName()));
        copyFile(origin, f);
    }

    private static void copyFile(File src, File dst) {
        try (FileInputStream fis = new FileInputStream(src);
             FileOutputStream fos = new FileOutputStream(dst);
             FileChannel in = fis.getChannel();
             FileChannel out = fos.getChannel()) {
            out.transferFrom(in, 0, in.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
