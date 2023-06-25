package com.app.dict.controllers;

import com.app.dict.base.LoadData;
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
        resetDanhSachLienQuan();

        if (nhanVat == null) return;

        if (nhanVat.getCacNhanVatLienQuan().size() > 0) nhanVatLienQuanLabel.setVisible(true);
        cacNhanVatLienQuan.getChildren().addAll(GeneralController.nhanVatLienQuanButtons(nhanVat.getCacNhanVatLienQuan(), database.getNhanVat()));

        if (nhanVat.getCacDiTichLienQuan().size() > 0) diTichLienQuanLabel.setVisible(true);
        cacDiTichLienQuan.getChildren().addAll(GeneralController.diTichLienQuanButtons(nhanVat.getCacDiTichLienQuan(), database.getDiTich()));

        if (nhanVat.getCacThoiKyLienQuan().size() > 0) thoiKyLienQuanLabel.setVisible(true);
        cacThoiKyLienQuan.getChildren().addAll(GeneralController.thoiKyLienQuanButtons(nhanVat.getCacThoiKyLienQuan(), database.getThoiKy()));
//        for (String diTich : nhanVat.getCacDiTichLienQuan()) {
//            List<Model> dtL = database.getDiTich();
//            int idx = LoadData.binaryLookupByCode(0, dtL.size() - 1, diTich, (ArrayList<Model>) dtL);
//            if (idx < 0) continue;
//            Button btn = new Button(dtL.get(idx).getTenModel());
//            btn.setOnAction(this::handleDiTichLienQuanButton);
//            cacDiTichLienQuan.getChildren().add(btn);
//        }
//        for (String thoiKy : nhanVat.getCacThoiKyLienQuan()) {
//            List<Model> tkL = database.getThoiKy();
//            int idx = database.binaryLookupByCode(0, tkL.size() - 1, thoiKy, (ArrayList<Model>) tkL);
//            if (idx < 0) continue;
//            Button btn = new Button(tkL.get(idx).getTenModel());
//            btn.setOnAction(this::handleThoiKyLienQuanButton);
//            cacThoiKyLienQuan.getChildren().add(btn);
//        }
    }
    private void resetDanhSachLienQuan(){
        nhanVatLienQuanLabel.setVisible(false);
        diTichLienQuanLabel.setVisible(false);
        thoiKyLienQuanLabel.setVisible(false);

        cacNhanVatLienQuan.getChildren().clear();
        cacDiTichLienQuan.getChildren().clear();
        cacThoiKyLienQuan.getChildren().clear();
    }
}
