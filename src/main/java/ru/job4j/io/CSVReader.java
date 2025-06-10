package ru.job4j.io;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get("delimiter");
        FileInputStream file = new FileInputStream(argsName.get("path"));
        String[] columnNamesFilter = argsName.get("filter").split(",");
        List<String> temp = new ArrayList<>();
        var scanner = new Scanner(file);
        List<Integer> columnIndexes = new ArrayList<>();
        while (scanner.hasNext()) {
            temp.add(scanner.nextLine());
        }
        if (!temp.isEmpty()) {
            String[] columnNames = temp.get(0).split(delimiter);
            for (String columnNameFilter : columnNamesFilter) {
                for (int i = 0; i < columnNames.length; i++) {
                    if (columnNameFilter.equals(columnNames[i])) {
                        columnIndexes.add(i);
                    }
                }
            }
        }
        PrintStream result;
        if (argsName.get("out").equals("stdout")) {
            result = new PrintStream(System.out);
        } else {
            result = new PrintStream(argsName.get("out"));
        }
        for (String s : temp) {
            String[] line = s.split(delimiter);
            var filterLine = new StringJoiner(delimiter);
            for (Integer index : columnIndexes) {
                filterLine.add(line[index]);
            }
            result.println(filterLine);
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

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        checkArgs(argsName);
        handle(argsName);
    }
}