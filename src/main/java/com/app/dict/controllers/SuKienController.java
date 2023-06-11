package com.app.dict.controllers;

import com.app.dict.base.DoiTuong;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SuKienController extends GeneralController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (DoiTuong temp : duLieu.getSuKien()) {
            objectList.add(temp.getSearching());
        }
        listView.setItems(objectList);
    }
    @FXML
    public void suKienSearchFieldAction(){
        searchFieldAction(duLieu.getSuKien());
    }
    @FXML
    public void showSuKienDetail(){
        showDetail(duLieu.getSuKien());
    }
}
