package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String userPhrase;
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        boolean status = true;
        do {
            userPhrase = scanner.nextLine();
            log.add("User phrase: " + userPhrase);
            if (OUT.equals(userPhrase) || STOP.equals(userPhrase)) {
                status = false;
            }
            if (CONTINUE.equals(userPhrase)) {
                status = true;
            }
            if (status) {
                Random randomizer = new Random();
                String randomPhrase = phrases.get(randomizer.nextInt(phrases.size()));
                System.out.println(randomPhrase);
                log.add("Bot phrase: " + randomPhrase);
            }
        } while (!OUT.equals(userPhrase));
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/chatLog.txt", "data/botAnswers.txt");
        consoleChat.run();
    }
}