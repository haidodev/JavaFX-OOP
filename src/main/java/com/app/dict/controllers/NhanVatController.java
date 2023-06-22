package com.app.dict.controllers;

import com.app.dict.base.Model;
import com.app.dict.base.NhanVatModel;
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

public class NhanVatController extends GeneralController implements Initializable {
    public VBox cacNhanVatLienQuan;
    public VBox cacDiTichLienQuan;
    public VBox cacThoiKyLienQuan;
    public Label diTichLienQuanLabel;
    public Label nhanVatLienQuanLabel;
    public Label thoiKyLienQuanLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getNhanVat()) {
            objectList.add(temp.getTenModel());
        }
        listView.setItems(objectList);

    }
    public void preloadNhanVat(String nhanVatName) {
        listView.getSelectionModel().select(nhanVatName);
        showNhanVatDetail(nhanVatName);

    }
    @FXML
    public void nhanVatSearchFieldAction() {
        searchFieldAction((ArrayList<Model>) database.getNhanVat());
    }
    @FXML
    public void showNhanVatDetail() {
        showDanhSachLienQuan((NhanVatModel) showDetail((ArrayList<Model>) database.getNhanVat()));
    }
    public void showNhanVatDetail(String nhanVatName) {
        showDanhSachLienQuan((NhanVatModel) showDetail((ArrayList<Model>) database.getNhanVat(), nhanVatName));
    }
    private void showDanhSachLienQuan(NhanVatModel nhanVat){
        nhanVatLienQuanLabel.setVisible(true);
        diTichLienQuanLabel.setVisible(true);
        thoiKyLienQuanLabel.setVisible(true);

        cacNhanVatLienQuan.getChildren().clear();
        cacDiTichLienQuan.getChildren().clear();
        cacThoiKyLienQuan.getChildren().clear();

        if (nhanVat == null) return;

        for (String nv : nhanVat.getCacNhanVatLienQuan()) {
            List<Model> nvL = database.getNhanVat();
            int idx = database.binaryLookupByCode(0, nvL.size() - 1, nv, (ArrayList<Model>) nvL);
            if (idx < 0) continue;
            Button btn = new Button(nvL.get(idx).getTenModel());
            btn.setOnAction(this::handleNhanVatLienQuanButton);
            cacNhanVatLienQuan.getChildren().add(btn);
        }
        for (String diTich : nhanVat.getCacDiTichLienQuan()) {
            List<Model> dtL = database.getDiTich();
            int idx = database.binaryLookupByCode(0, dtL.size() - 1, diTich, (ArrayList<Model>) dtL);
            if (idx < 0) continue;
            Button btn = new Button(dtL.get(idx).getTenModel());
            btn.setOnAction(this::handleDiTichLienQuanButton);
            cacDiTichLienQuan.getChildren().add(btn);
        }
        for (String thoiKy : nhanVat.getCacThoiKyLienQuan()) {
            List<Model> tkL = database.getThoiKy();
            int idx = database.binaryLookupByCode(0, tkL.size() - 1, thoiKy, (ArrayList<Model>) tkL);
            if (idx < 0) continue;
            Button btn = new Button(tkL.get(idx).getTenModel());
            btn.setOnAction(this::handleThoiKyLienQuanButton);
            cacThoiKyLienQuan.getChildren().add(btn);
        }
    }
}
