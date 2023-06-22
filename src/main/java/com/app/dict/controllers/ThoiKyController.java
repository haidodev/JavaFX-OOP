package com.app.dict.controllers;

import com.app.dict.base.Model;
import com.app.dict.base.ThoiKyModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class ThoiKyController extends GeneralController implements Initializable {
    public VBox cacNhanVatLienQuan;
    public VBox cacDiTichLienQuan;
    public Label nhanVatLienQuanLabel;
    public Label diTichLienQuanLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getThoiKy()) {
            objectList.add(temp.getTenModel());
        }
        listView.setItems(objectList);
    }
    public void preloadThoiKy(String thoiKyName) {
        listView.getSelectionModel().select(thoiKyName);
        showThoiKyDetail(thoiKyName);
    }
    @FXML
    public void showThoiKyDetail() {
        ThoiKyModel item = (ThoiKyModel) showDetail((ArrayList<Model>) database.getThoiKy());
        showDanhSachLienQuan(item);
    }
    public void showThoiKyDetail(String thoiKyName) {
        ThoiKyModel item = (ThoiKyModel) showDetail((ArrayList<Model>) database.getThoiKy(), thoiKyName);
        showDanhSachLienQuan(item);
    }
    private void showDanhSachLienQuan(ThoiKyModel thoiKy){
        nhanVatLienQuanLabel.setVisible(true);
        diTichLienQuanLabel.setVisible(true);

        cacNhanVatLienQuan.getChildren().clear();
        cacDiTichLienQuan.getChildren().clear();

        if (thoiKy == null) return;
        if (thoiKy.getCacNhanVatLienQuan().size() > 0) nhanVatLienQuanLabel.setVisible(true);
        cacNhanVatLienQuan.getChildren().addAll(GeneralController.nhanVatLienQuanButtons(thoiKy.getCacNhanVatLienQuan(), database.getNhanVat()));
        for (String diTich : thoiKy.getcacDiTichLienQuan()) {
            List<Model> dtL = database.getDiTich();
            int idx = database.binaryLookupByCode(0, dtL.size() - 1, diTich, (ArrayList<Model>) dtL);
            if (idx < 0) continue;
            Button btn = new Button(dtL.get(idx).getTenModel());
            btn.setOnAction(this::handleDiTichLienQuanButton);
            cacDiTichLienQuan.getChildren().add(btn);
        }
    }
    @FXML
    public void thoiKySearchFieldAction() {
        searchFieldAction((ArrayList<Model>) database.getThoiKy());
    }
}
