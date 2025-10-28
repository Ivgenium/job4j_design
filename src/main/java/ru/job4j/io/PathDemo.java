package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathDemo {
    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("logs", "access.log");
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            reader.lines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
