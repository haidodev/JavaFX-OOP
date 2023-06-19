package com.app.dict.controllers;

import com.app.dict.base.Model;
import com.app.dict.base.NhanVatModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LeHoiController extends GeneralController implements Initializable {
    public VBox contentVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getLeHoi()) {
            objectList.add(temp.getTenModel());
        }
        listView.setItems(objectList);
    }
    @FXML
    public void leHoiSearchFieldAction(){
        searchFieldAction((ArrayList<Model>) database.getLeHoi());
    }
    @FXML
    public void showLeHoiDetail(){
        contentVBox.getChildren().clear();
        NhanVatModel item = (NhanVatModel) showDetail((ArrayList<Model>) database.getLeHoi());
        if (item == null) return;
    }
}
