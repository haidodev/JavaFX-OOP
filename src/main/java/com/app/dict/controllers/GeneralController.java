package com.app.dict.controllers;

import com.app.dict.base.LoadData;
import com.app.dict.base.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class GeneralController extends MainController implements Initializable {
    protected final ObservableList<String> nhanVatList = FXCollections.observableArrayList();
    protected final ObservableList<String> objectList = FXCollections.observableArrayList();
    protected final ObservableList<String> suKienList = FXCollections.observableArrayList();
    protected final ObservableList<String> diTichList = FXCollections.observableArrayList();
    protected final ObservableList<String> leHoiList = FXCollections.observableArrayList();
    protected static LoadData database = new LoadData();

    @FXML
    protected ListView<String> listView;
    @FXML
    protected TextField searchField;
    @FXML
    private WebView definitionView;
    private final ArrayList<Model> searchTemp = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void setListViewItem(ArrayList<Model> resource) {
        objectList.clear();
        if (searchField.getText().equals("")) {
            searchTemp.clear();
            searchTemp.addAll(resource);
        }
        for (Model temp : searchTemp) {
            objectList.add(temp.getName());
        }
        listView.setItems(objectList);
    }

    public void searchFieldAction(ArrayList<Model> resource) {
        searchTemp.clear();
        objectList.clear();
        String word = searchField.getText();
        int index = database.binaryLookup(0, resource.size() - 1, word, resource);
        if (index < 0) {
            index = database.binaryLookup(0, resource.size() -1, word, resource);
        }
        updateWordInListView(word, index, resource, searchTemp);
        setListViewItem(resource);
    }

    public void showDetail(ArrayList<Model> resource) {
        String spelling = listView.getSelectionModel().getSelectedItem();
        if (spelling == null) {
            return;
        }
        int index = Collections.binarySearch(resource, new Model(spelling, ""));
        String meaning = resource.get(index).getHTML();
        System.out.println(spelling);
        definitionView.getEngine().loadContent(meaning, "text/html");
    }
    public void updateWordInListView(String word, int index, ArrayList<Model> res, ArrayList<Model> des) {
        if (index < 0) {
            return;
        }
        int j = index;
        while (j >= 0) {
            if (LoadData.isContain(word, res.get(j).getName()) == 0) {
                j--;
            } else {
                break;
            }
        }
        for (int i = j + 1; i <= index; i++) {
            Model temp = new Model(res.get(i).getName(), res.get(i).getHTML());
            des.add(temp);
        }
        for (int i = index + 1; i < res.size(); i++) {
            if (LoadData.isContain(word, res.get(i).getName()) == 0) {
                Model temp = new Model(res.get(i).getName(), res.get(i).getHTML());
                des.add(temp);
            } else {
                break;
            }
        }
    }
}
