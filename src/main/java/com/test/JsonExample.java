package com.test;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonExample {

    public static void main(String[] args) throws IOException {

        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Текущий рабочий каталог: " + currentDirectory);
        String filePath = "src/main/java/test/data.json";
        JSONObject jsonObject = new JSONObject(filePath);
        // Access the data from the JSONObject
        String name = jsonObject.getString("widget.debug");

        System.out.println("Name: " + name);

    }

}
