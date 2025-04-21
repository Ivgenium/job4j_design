package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        files.putIfAbsent(fileProperty, new ArrayList<>());
        files.get(fileProperty).add(file);
        return super.visitFile(file, attributes);
    }

    public void printDuplicates() {
        files.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> {
                    System.out.printf("%s - %d bytes%s", entry.getKey().getName(), entry.getKey().getSize(), System.lineSeparator());
                    entry.getValue().forEach(System.out::println);
                });
    }
}