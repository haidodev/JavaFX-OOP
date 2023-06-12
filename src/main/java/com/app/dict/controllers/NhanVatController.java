package com.app.dict.controllers;

import com.app.dict.base.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NhanVatController extends GeneralController implements Initializable {
//    private final ArrayList<DoiTuong> searchTemp = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getNhanVat()) {
            objectList.add(temp.getName());
        }
        listView.setItems(objectList);
    }
//    public void setNhanVatListViewItem() {
//        nhanVatList.clear();
//        if (searchField.getText().equals("")) {
//            searchTemp.clear();
//            searchTemp.addAll(duLieu.getNhanVat());
//        }
//        for (DoiTuong temp : searchTemp) {
//            nhanVatList.add(temp.getSearching());
//        }
//        listView.setItems(nhanVatList);
//    }
    @FXML
    public void nhanVatSearchFieldAction() {
//        searchTemp.clear();
//        nhanVatList.clear();
//        String word = searchField.getText();
//        int index = duLieu.binaryLookup(0, duLieu.getNhanVat().size() - 1, word, duLieu.getNhanVat());
//        if (index < 0) {
//            index = duLieu.binaryLookup(0, duLieu.getNhanVat().size() -1, word, duLieu.getNhanVat());
//        }
//        updateWordInListView(word, index, duLieu.getNhanVat(), searchTemp);
//        setNhanVatListViewItem();
        searchFieldAction((ArrayList<Model>) database.getNhanVat());
    }
    @FXML
    public void showNhanVatDetail() {
//        String spelling = listView.getSelectionModel().getSelectedItem();
//        if (spelling == null) {
//            return;
//        }
//        int index = Collections.binarySearch(duLieu.getNhanVat(), new DoiTuong(spelling, null));
//        String meaning = duLieu.getNhanVat().get(index).getInfo();
//        System.out.println(spelling);
//        definitionView.getEngine().loadContent(meaning, "text/html");
        showDetail((ArrayList<Model>) database.getNhanVat());
    }
}
