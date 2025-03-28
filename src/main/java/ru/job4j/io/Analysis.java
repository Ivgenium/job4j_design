package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String line;
            String startTime = "";
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(" ");
                if (startTime.isEmpty() && validStatus(temp[0])) {
                    startTime = temp[1];
                } else if (!startTime.isEmpty() && !validStatus(temp[0])) {
                    writer.write(String.format("%s;%s;%s", startTime, temp[1], System.lineSeparator()));
                    startTime = "";
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean validStatus(String status) {
        return "400".equals(status) || "500".equals(status);
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}