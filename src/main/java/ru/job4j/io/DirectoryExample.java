package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryExample {
    public static void main(String[] args) throws IOException {
        File directory = new File("src/main/java/ru/job4j/io/files/directory");
//        directory.mkdirs(); // Создает директорию, включая все промежуточные директории
//        File target = new File("src/main/java/ru/job4j/io/files");
//        File fileOne = new File("src/main/java/ru/job4j/io/files/file1.txt");
//        fileOne.createNewFile(); // Создает новый файл
//        File fileTwo = new File("src/main/java/ru/job4j/io/files/directory/file2.txt");
//        fileTwo.createNewFile(); // Создает новый файл
//        String[] list = target.list(); // Получаем имена файлов и директорий в target
//        File target = new File("src");
//        String[] list = target.list();
//        for (String fileName : list) {
//            System.out.println(fileName);
//        }
//        File[] listFiles = target.listFiles(); // Получаем объекты File для файлов и директорий в target
//        for (File file : listFiles) {
//            System.out.println(file);
//        }
        String dirname = "src";
        try (DirectoryStream<Path> dirstr = Files.newDirectoryStream(Path.of(dirname))) {
            System.out.println("Каталог " + dirname);
            for (Path entry : dirstr) {
                BasicFileAttributes attributes = Files.readAttributes(entry, BasicFileAttributes.class);
                if (attributes.isDirectory()) {
                    System.out.println(entry.getName(1));
                }
            }
        }
    }
}