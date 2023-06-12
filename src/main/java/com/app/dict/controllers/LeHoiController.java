package com.app.dict.controllers;

import com.app.dict.base.DoiTuong;
import com.app.dict.base.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LeHoiController extends GeneralController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getLeHoi()) {
            objectList.add(temp.getName());
        }
        listView.setItems(objectList);
    }
    @FXML
    public void leHoiSearchFieldAction(){
        searchFieldAction((ArrayList<Model>) database.getLeHoi());
    }
    @FXML
    public void showLeHoiDetail(){
        showDetail((ArrayList<Model>) database.getLeHoi());
    }
}
