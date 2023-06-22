package com.app.dict.controllers;

import com.app.dict.base.LeHoiModel;
import com.app.dict.base.Model;
import com.app.dict.base.NhanVatModel;
import com.app.dict.base.ThoiKyModel;
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
    public void preloadLeHoi(String leHoiName) {
        listView.getSelectionModel().select(leHoiName);
        showLeHoiDetail(leHoiName);
    }
    @FXML
    public void showLeHoiDetail() {
        LeHoiModel item = (LeHoiModel) showDetail((ArrayList<Model>) database.getLeHoi());
        showDanhSachLienQuan(item);
    }
    public void showLeHoiDetail(String leHoiName) {
        LeHoiModel item = (LeHoiModel) showDetail((ArrayList<Model>) database.getLeHoi(), leHoiName);
        showDanhSachLienQuan(item);
    }
    @FXML
    public void leHoiSearchFieldAction(){
        searchFieldAction((ArrayList<Model>) database.getLeHoi());
    }
    private void showDanhSachLienQuan(LeHoiModel item){
        contentVBox.getChildren().clear();
        if (item == null) return;
    }
}
