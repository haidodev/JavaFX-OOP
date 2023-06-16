package com.app.dict.controllers;

import com.app.dict.base.DiTichModel;
import com.app.dict.base.Model;
import com.app.dict.base.NhanVatModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DiTichController extends GeneralController implements Initializable {
    public VBox contentVBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getDiTich()) {
            objectList.add(temp.getTenModel());
        }
        listView.setItems(objectList);
    }
    public void preloadDiTich(String diTichName) {
        listView.getSelectionModel().select(diTichName);
        showDetail((ArrayList<Model>) database.getDiTich(), diTichName);
    }
    @FXML
    public void diTichSearchFieldAction(){
        searchFieldAction((ArrayList<Model>) database.getDiTich());
    }
    @FXML
    public void showDiTichDetail(){
        contentVBox.getChildren().clear();
        DiTichModel item = (DiTichModel) showDetail((ArrayList<Model>) database.getDiTich(), true);
        if (item == null) return;
        for (String nhanVat : item.getcacNhanVatLienQuan()) {
            List<Model> nvL = database.getNhanVat();
            int idx = database.binaryLookupByCode(0, nvL.size() - 1, nhanVat, (ArrayList<Model>) nvL);
            if (idx < 0) continue;
            Button btn = new Button(nvL.get(idx).getTenModel());
            btn.setOnAction(this::handleNhanVatLienQuanButton);
            contentVBox.getChildren().add(btn);
        }
    }
}
