package com.app.dict.controllers;

import base.DoiTuong;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ThoiKyController extends GeneralController implements Initializable {
    public WebView definitionView;
    private final ArrayList<DoiTuong> searchTemp = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (DoiTuong temp : duLieu.getThoiKy()) {
            thoiKyList.add(temp.getSearching());
        }
        listView.setItems(thoiKyList);
    }
    public void setThoiKyListViewItem() {
        thoiKyList.clear();
        if (searchField.getText().equals("")) {
            searchTemp.clear();
            searchTemp.addAll(duLieu.getThoiKy());
        }
        for (DoiTuong temp : searchTemp) {
            thoiKyList.add(temp.getSearching());
        }
        listView.setItems(thoiKyList);
    }
    @FXML
    public void thoiKySearchFieldAction() throws IOException {
        searchTemp.clear();
        thoiKyList.clear();
        String word = searchField.getText();
        int index = duLieu.binaryLookup(0, duLieu.getThoiKy().size() - 1, word, duLieu.getThoiKy());
        if (index < 0) {
            index = duLieu.binaryLookup(0, duLieu.getThoiKy().size() -1, word, duLieu.getThoiKy());
        }
        updateWordInListView(word, index, duLieu.getThoiKy(), searchTemp);
        setThoiKyListViewItem();
    }
    @FXML
    public void showThoiKyDetail() {
        String spelling = listView.getSelectionModel().getSelectedItem();
        if (spelling == null) {
            return;
        }
        int index = Collections.binarySearch(duLieu.getThoiKy(), new DoiTuong(spelling, null));
        String meaning = duLieu.getThoiKy().get(index).getMeaning();
        definitionView.getEngine().loadContent(meaning, "text/html");
    }
    public void initThoiKyListView() {
        listView.getItems().clear();
        setThoiKyListViewItem();
    }
}
