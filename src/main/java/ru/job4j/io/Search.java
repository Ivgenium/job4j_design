package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        checkArgs(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void checkArgs(String[] args) {
        String ln = System.lineSeparator();
        if (args.length != 2) {
            throw new IllegalArgumentException("There should be two args:" + ln
                    + "The first parameter is the initial folder." + ln
                    + "The second parameter is the file extension to be searched for.");
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException(String.format("The initial folder: %s  does not exist.", args[0]));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("The file extension must start with \".\"");
        }
    }
}