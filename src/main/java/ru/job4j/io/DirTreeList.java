package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DirTreeList {
    static class MyFileVisitor extends SimpleFileVisitor<Path> {
        public FileVisitResult visitFile(Path path, BasicFileAttributes attributes) throws IOException {
            System.out.println(path);
            return FileVisitResult.CONTINUE;
        }
    }

    public static void main(String[] args) {
        String dirname = "src";
        System.out.println("Дepeвo каталогов, "
                + "начиная с каталога" + dirname + ":\n");
        try {
            Files.walkFileTree(Path.of(dirname), new MyFileVisitor());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
