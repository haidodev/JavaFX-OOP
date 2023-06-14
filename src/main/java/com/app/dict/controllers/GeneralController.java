package com.app.dict.controllers;

import com.app.dict.base.LoadData;
import com.app.dict.base.Model;
import com.app.dict.base.ThoiKyModel;
import com.app.dict.util.StringUtility;
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
        super.initialize(location, resources);
    }
    public void setListViewItem(ArrayList<Model> resource) {
        objectList.clear();
        if (searchField.getText().equals("")) {
            searchTemp.clear();
            searchTemp.addAll(resource);
        }
        for (Model temp : searchTemp) {
            objectList.add(temp.getTenModel());
        }
        listView.setItems(objectList);
    }

    public void searchFieldAction(ArrayList<Model> resource) {
        searchTemp.clear();
        objectList.clear();
        String word = searchField.getText();
        int index = database.binaryLookup(0, resource.size() - 1, word, resource);
//        if (index < 0) {
//            System.out.println("Question ???");
//            index = database.binaryLookup(0, resource.size() - 1, word, resource);
//        }
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
        definitionView.getEngine().loadContent(meaning, "text/html");
    }
    public Model showDetail(ArrayList<Model> resource, boolean returnValue) {
        String spelling = listView.getSelectionModel().getSelectedItem();
        if (spelling == null) {
            return null;
        }
        int index = Collections.binarySearch(resource, new Model(spelling, ""));
        String meaning = resource.get(index).getHTML();
        definitionView.getEngine().loadContent(meaning, "text/html");
        return resource.get(index);
    }
    public void updateWordInListView(String word, int index, ArrayList<Model> res, ArrayList<Model> des) {
        if (index < 0) {
            return;
        }
        int j = index;
        while (j >= 0) {
            if (StringUtility.isContain(word, res.get(j).getTenModel()) == 0) {
                j--;
            } else {
                break;
            }
        }
        for (int i = j + 1; i <= index; i++) {
            Model temp = new Model(res.get(i).getTenModel(), res.get(i).getHTML());
            des.add(temp);
        }
        for (int i = index + 1; i < res.size(); i++) {
            if (StringUtility.isContain(word, res.get(i).getTenModel()) == 0) {
                Model temp = new Model(res.get(i).getTenModel(), res.get(i).getHTML());
                des.add(temp);
            } else {
                break;
            }
        }
    }
    public void handleNhanVatLienQuanBtn(){
        System.out.println(listView);

        //showNhanVatPane();
        String spelling = "An Dương Vương";
        //listView.getSelectionModel().select(spelling);
        ArrayList<Model> resource = (ArrayList<Model>) database.getNhanVat();
        int index = Collections.binarySearch(resource, new Model(spelling, ""));
        String meaning = resource.get(index).getHTML();
        System.out.println(spelling);
        definitionView.getEngine().loadContent(meaning, "text/html");
    }
}
