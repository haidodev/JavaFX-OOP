package com.oop2.supers;

import com.oop2.models.Model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;

public abstract class SCrawler
{
    public <T> List<T> loader(String filePath, TypeToken<List<T>> typeToken) {
        List<T> list = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            // Read the JSON string from the file
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            String jsonString = jsonBuilder.toString();

            // Use Gson to deserialize the JSON string into an ArrayList of the specified type
            Gson gson = new GsonBuilder().create();
            list = gson.fromJson(jsonString, typeToken.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void writeHTML(String fileName, List<Model> models)
    {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Model model : models)
            {
                writer.write(model.toHTML() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJson(String fileName, List<Model> models)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        String json = gson.toJson(models);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
