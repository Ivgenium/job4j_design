package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkArgs(ArgsName args) {
        String ln = System.lineSeparator();
        if (args.size() != 3) {
            throw new IllegalArgumentException("There should be three args:" + ln
                    + "-d - directory - which we want to archive." + ln
                    + "-e - exclude - exclude files with the class extension." + ln
                    + "-o - output - what we archive into.");
        }
        if (!Files.isDirectory(Path.of(args.get("d")))) {
            throw new IllegalArgumentException("The archived directory does not exist.");
        }
        if (!args.get("e").startsWith(".")) {
            throw new IllegalArgumentException("The extension must start with the character \".\".");
        }
        if (!args.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("the archive name must have the \".zip\" extension");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        Path directory = Path.of(argsName.get("d"));
        List<Path> sources = Search.search(directory, path -> !path.toFile().getName().endsWith(argsName.get("e")));
        Zip zip = new Zip();
        zip.packFiles(sources, new File(argsName.get("o")));
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}