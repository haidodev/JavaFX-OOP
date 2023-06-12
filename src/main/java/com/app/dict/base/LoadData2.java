package com.app.dict.base;

import com.app.dict.util.Config;
import com.app.dict.util.VietnameseUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoadData2
{
    private final List<Model> nhanVat;
    private final List<Model> thoiKy;
    private final List<Model> suKien;
    private final List<Model> diTich;
    private final List<Model> leHoi;
    public List<Model> getNhanVat() {
        return nhanVat;
    }

    public List<Model> getThoiKy() {
        return thoiKy;
    }

    public List<Model> getSuKien() {
        return suKien;
    }

    public List<Model> getDiTich() {
        return diTich;
    }

    public List<Model> getLeHoi() {
        return leHoi;
    }
    public LoadData2() {
        nhanVat = loadFigure();
        thoiKy = loadThoiKy();
        suKien = loadSuKien();
        diTich = loadDiTich();
        leHoi = loadFestival();
    }
    public List<Model> loadFigure()
    {
        List<FigureModel> myList = loader(Config.HISTORICAL_FIGURE_FILENAME,  new TypeToken<List<FigureModel>>() {});
        List<Model> newList = new ArrayList<>(myList);
        for (Model model : newList) model.setHTML();
        Collections.sort(newList);
        return newList;
    }
    public List<Model> loadThoiKy()
    {
        List<EraModel> myList = loader(Config.ERA_FILENAME,  new TypeToken<List<EraModel>>() {});
        List<Model> newList = new ArrayList<>(myList);
        for (Model model : newList) model.setHTML();
        Collections.sort(newList);
        return newList;
    }
    public List<Model> loadSuKien()
    {
        List<EventModel> myList = loader(Config.EVENT_FILENAME,  new TypeToken<List<EventModel>>() {});
        List<Model> newList = new ArrayList<>(myList);
        for (Model model : newList) model.setHTML();
        Collections.sort(newList);
        return newList;
    }
    public List<Model> loadDiTich()
    {
        List<PlaceModel> myList = loader(Config.HISTORICAL_DESTINATION_FILENAME,  new TypeToken<List<PlaceModel>>() {});
        List<Model> newList = new ArrayList<>(myList);
        for (Model model : newList) model.setHTML();
        Collections.sort(newList);
        return newList;
    }

    public List<Model> loadFestival()
    {
        List<FestivalModel> myList = loader(Config.FESTIVAL_FILENAME,  new TypeToken<List<FestivalModel>>() {});
        List<Model> newList = new ArrayList<>(myList);
        for (Model model : newList) model.setHTML();
        Collections.sort(newList);
        return newList;
    }
    public <T> List<Model> loading(String resource)
    {
        List<T> myList = loader(resource,  new TypeToken<List<T>>() {});
        List<Model> newList = (List<Model>) new ArrayList<>(myList);
        for (Model model : newList) model.setHTML();
        Collections.sort(newList);
        return newList;
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
    public int binaryCheck(int start, int end, String dT, ArrayList<Model> modelList) {
        if (end < start) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        int compareNext = dT.compareTo(modelList.get(mid).getName());
        if (mid == 0) {
            if (compareNext < 0) {
                return 0;
            } else if (compareNext > 0) {
                return binaryCheck(mid + 1, end, dT, modelList);
            } else {
                return -1;
            }
        } else {
            int comparePrevious = dT.compareTo(modelList.get(mid - 1).getName());
            if (comparePrevious > 0 && compareNext < 0) {
                return mid;
            } else if (comparePrevious < 0) {
                return binaryCheck(start, mid - 1, dT, modelList);
            } else if (compareNext > 0) {
                if (mid == modelList.size() - 1) {
                    return modelList.size();
                }
                return binaryCheck(mid + 1, end, dT, modelList);
            } else {
                return -1;
            }
        }
    }
    public static int isContain(String str1, String str2) {
        String normalizedStr1 = VietnameseUtil.generalizeVietnameseString(str1);
        String normalizedStr2 = VietnameseUtil.generalizeVietnameseString(str2);
        for (int i = 0; i < Math.min(normalizedStr1.length(), normalizedStr2.length()); i++) {
            if (normalizedStr1.charAt(i) > normalizedStr2.charAt(i)) {
                return 1;
            } else if (normalizedStr1.charAt(i) < normalizedStr2.charAt(i)) {
                return -1;
            }
        }
        if (normalizedStr1.length() > normalizedStr2.length()) {
            return 1;
        }
        return 0;
    }
    public int binaryLookup(int start, int end, String dT, ArrayList<Model> temp) {
        if (end < start) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        int compare = isContain(dT, temp.get(mid).getName());
        if (compare == -1) {
            return binaryLookup(start, mid - 1, dT, temp);
        } else if (compare == 1) {
            return binaryLookup(mid + 1, end, dT, temp);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        LoadData2 ld = new LoadData2();
        //List<Model> tmp = ld.loading(Config.HISTORICAL_FIGURE_FILENAME);
        for (Model m : ld.getDiTich()) System.out.println(m);
    }
}
