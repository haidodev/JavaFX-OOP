package com.app.dict.controllers;

import com.app.dict.base.DiTichModel;
import com.app.dict.base.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DiTichController extends GeneralController implements Initializable {
    public VBox cacNhanVatLienQuan;
    public Label nhanVatLienQuanLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getDiTich()) {
            objectList.add(temp.getTenModel());
        }
        listView.setItems(objectList);
    }
    public void preloadDiTich(String diTichName) {
        listView.getSelectionModel().select(diTichName);
        showDiTichDetail(diTichName);
    }
    @FXML
    public void showDiTichDetail() {
        DiTichModel item = (DiTichModel) showDetail((ArrayList<Model>) database.getDiTich());
        showDanhSachLienQuan(item);
    }
    public void showDiTichDetail(String diTichName) {
        DiTichModel item = (DiTichModel) showDetail((ArrayList<Model>) database.getDiTich(), diTichName);
        showDanhSachLienQuan(item);
    }
    @FXML
    public void diTichSearchFieldAction(){
        searchFieldAction((ArrayList<Model>) database.getDiTich());
    }
    @FXML
    private void showDanhSachLienQuan(DiTichModel item){
        nhanVatLienQuanLabel.setVisible(false);
        cacNhanVatLienQuan.getChildren().clear();
        if (item == null) return;
        if (item.getCacNhanVatLienQuan().size() > 0) nhanVatLienQuanLabel.setVisible(true);
        for (String nhanVat : item.getCacNhanVatLienQuan()) {
            List<Model> nvL = database.getNhanVat();
            int idx = database.binaryLookupByCode(0, nvL.size() - 1, nhanVat, (ArrayList<Model>) nvL);
            if (idx < 0) continue;
            Button btn = new Button(nvL.get(idx).getTenModel());
            btn.setOnAction(this::handleNhanVatLienQuanButton);
            cacNhanVatLienQuan.getChildren().add(btn);
        }
    }
}
