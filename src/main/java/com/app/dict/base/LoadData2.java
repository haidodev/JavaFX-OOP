package com.app.dict.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadData2
{
    public void loadFigure()
    {
        List<FigureModel> myList = loader("database/json/historicalFigures.json",  new TypeToken<List<FigureModel>>() {});
        List<Model> newList = new ArrayList<>(myList);
        for (Model model : newList)
        {
            System.out.println(model.toHTML());
        }
    }

    public void loadFestval()
    {
        List<FestivalModel> myList = loader("database/json/festivals.json",  new TypeToken<List<FestivalModel>>() {});
        List<Model> newList = new ArrayList<>(myList);
        for (Model model : newList)
        {
            System.out.println(model.toHTML());
        }
    }

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

    public static void main(String[] args)
    {
        LoadData2 loadData2 = new LoadData2();
//        loadData2.loadFigure();
        loadData2.loadFestval();
    }
}
