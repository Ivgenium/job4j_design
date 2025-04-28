package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Я учусь на Job4j");

        String textOne = "Я учусь на Job4j";
        Matcher matcherOne = pattern.matcher(textOne);
        boolean isPresentOne = matcherOne.matches();
        System.out.println(isPresentOne);

        String textTwo = "Я учусь на курсе Job4j";
        Matcher matcherTwo = pattern.matcher(textTwo);
        boolean isPresentTwo = matcherTwo.matches();
        System.out.println(isPresentTwo);

        pattern = Pattern.compile("Job4j");

        textOne = "Job4j";
        matcherOne = pattern.matcher(textOne);
        isPresentOne = matcherOne.matches();
        System.out.println(isPresentOne);

        textTwo = "job4j";
        matcherTwo = pattern.matcher(textTwo);
        isPresentTwo = matcherTwo.matches();
        System.out.println(isPresentTwo);

        pattern = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);

        textOne = "Job4j";
        matcherOne = pattern.matcher(textOne);
        isPresentOne = matcherOne.matches();
        System.out.println(isPresentOne);

        textTwo = "job4j";
        matcherTwo = pattern.matcher(textTwo);
        isPresentTwo = matcherTwo.matches();
        System.out.println(isPresentTwo);

        pattern = Pattern.compile("Job4j");

        String text = "Я учусь на курсе Job4j";
        Matcher matcher = pattern.matcher(text);
        boolean isPresent = matcher.find();
        System.out.println(isPresent);

        pattern = Pattern.compile("Job4j");
        text = "Job4j1 и Job4j2 и Job4j3";
        matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение. iStart: " + matcher.start()
                    + " iEnd: " + matcher.end());
        }
    }
}