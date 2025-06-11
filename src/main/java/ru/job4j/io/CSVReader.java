package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String delimiter = argsName.get("delimiter");
        String[] columnNamesFilter = argsName.get("filter").split(",");
        try (var file = new FileInputStream(argsName.get("path"));
             var scanner = new Scanner(file);
             var result = "stdout".equals(argsName.get("out"))
                     ? new PrintStream(System.out) : new PrintStream(argsName.get("out"))) {
            List<Integer> columnIndexes = new ArrayList<>();
            String[] columnNames = scanner.nextLine().split(delimiter);
            for (String columnNameFilter : columnNamesFilter) {
                for (int i = 0; i < columnNames.length; i++) {
                    if (columnNameFilter.equals(columnNames[i])) {
                        columnIndexes.add(i);
                        break;
                    }
                }
            }
            var firstLine = new StringJoiner(delimiter);
            for (String s : columnNamesFilter) {
                firstLine.add(s);
            }
            result.println(firstLine);
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(delimiter);
                var filterLine = new StringJoiner(delimiter);
                for (Integer index : columnIndexes) {
                    filterLine.add(line[index]);
                }
                result.println(filterLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkArgs(ArgsName args) {
        String ln = System.lineSeparator();
        if (args.size() != 4) {
            throw new IllegalArgumentException("There should be four args:" + ln
                    + "-path - the csv file that needs to be parsed." + ln
                    + "-delimiter - data separator in a csv file." + ln
                    + "-out - data receiver." + ln
                    + "-filter - filter by columns.");
        }
        if (!Files.exists(Path.of(args.get("path")))) {
            throw new IllegalArgumentException("There is no file in the specified path.");
        }
        if (!"stdout".equals(args.get("out")) && !args.get("out").endsWith(".csv")) {
            throw new IllegalArgumentException("The path for writing data is not specified.");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        handle(argsName);
    }
}