package com.screen;

import com.theorygameapplication.Main;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetFile {
    public static String readFile(Class<?> clazz, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(fileName);
        System.out.println(clazz.getResourceAsStream(fileName));
        try (InputStream inputStream = clazz.getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        int startIndex = stringBuilder.indexOf("\n") + 1; // Находим позицию начала второй строки
        stringBuilder.delete(0, startIndex); // Удаляем первую строку
        return stringBuilder.toString();
    }

}
