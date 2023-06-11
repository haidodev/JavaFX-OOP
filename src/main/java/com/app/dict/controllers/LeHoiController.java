package com.app.dict.controllers;

import com.app.dict.base.DoiTuong;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LeHoiController extends GeneralController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (DoiTuong temp : duLieu.getLeHoi()) {
            objectList.add(temp.getSearching());
        }
        listView.setItems(objectList);
    }
    @FXML
    public void leHoiSearchFieldAction(){
        searchFieldAction(duLieu.getLeHoi());
    }
    @FXML
    public void showLeHoiDetail(){
        showDetail(duLieu.getLeHoi());
    }
}
