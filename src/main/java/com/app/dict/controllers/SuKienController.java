package com.app.dict.controllers;

import com.app.dict.base.Model;
import com.app.dict.base.SuKienModel;
import com.app.dict.base.ThoiKyModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SuKienController extends GeneralController implements Initializable {
    public VBox cacNhanVatLienQuan;
    public VBox cacDiTichLienQuan;
    public Label nhanVatLienQuanLabel;
    public Label diTichLienQuanLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getSuKien()) {
            objectList.add(temp.getTenModel());
        }
        listView.setItems(objectList);
    }
    public void preloadSuKien(String suKienName) {
        listView.getSelectionModel().select(suKienName);
        showSuKienDetail(suKienName);
    }
    @FXML
    public void showSuKienDetail() {
        SuKienModel item = (SuKienModel) showDetail((ArrayList<Model>) database.getSuKien());
        showDanhSachLienQuan(item);
    }
    public void showSuKienDetail(String suKienName) {
        SuKienModel item = (SuKienModel) showDetail((ArrayList<Model>) database.getSuKien(), suKienName);
        showDanhSachLienQuan(item);
    }
    @FXML
    public void suKienSearchFieldAction(){
        searchFieldAction((ArrayList<Model>) database.getSuKien());
    }
    private void showDanhSachLienQuan(SuKienModel item){
        nhanVatLienQuanLabel.setVisible(true);
        diTichLienQuanLabel.setVisible(true);

        cacNhanVatLienQuan.getChildren().clear();
        cacDiTichLienQuan.getChildren().clear();
        if (item == null) return;

        for (String nhanVat : item.getcacNhanVatLienQuan()) {
            List<Model> nvL = database.getNhanVat();
            int idx = database.binaryLookupByCode(0, nvL.size() - 1, nhanVat, (ArrayList<Model>) nvL);
            if (idx < 0) continue;
            Button btn = new Button(nvL.get(idx).getTenModel());
            btn.setOnAction(this::handleNhanVatLienQuanButton);
            cacNhanVatLienQuan.getChildren().add(btn);
        }
        for (String diTich : item.getcacDiTichLienQuan()) {
            List<Model> dtL = database.getDiTich();
            int idx = database.binaryLookupByCode(0, dtL.size() - 1, diTich, (ArrayList<Model>) dtL);
            if (idx < 0) continue;
            Button btn = new Button(dtL.get(idx).getTenModel());
            btn.setOnAction(this::handleDiTichLienQuanButton);
            cacDiTichLienQuan.getChildren().add(btn);
        }
    }
}
