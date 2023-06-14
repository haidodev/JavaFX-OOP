package com.app.dict.controllers;

import com.app.dict.base.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DiaDanhController extends GeneralController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getDiTich()) {
            objectList.add(temp.getTenModel());
        }
        listView.setItems(objectList);
    }
    @FXML
    public void diTichSearchFieldAction(){
        searchFieldAction((ArrayList<Model>) database.getDiTich());
    }
    @FXML
    public void showDiTichDetail(){
        showDetail((ArrayList<Model>) database.getDiTich());
    }
}
