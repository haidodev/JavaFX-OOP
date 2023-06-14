package com.app.dict.controllers;

import com.app.dict.base.LoadData;
import com.app.dict.base.Model;
import com.app.dict.base.ThoiKyModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThoiKyController extends GeneralController implements Initializable {
    public ScrollPane scbar;
    public AnchorPane outerAnchorPane;
    public VBox contentVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getThoiKy()) {
            objectList.add(temp.getTenModel());
        }
        listView.setItems(objectList);
    }
    @FXML
    public void showThoiKyDetail() {
        contentVBox.getChildren().clear();
        ThoiKyModel item = (ThoiKyModel) showDetail((ArrayList<Model>) database.getThoiKy(), true);
        String str = "";
        if (item != null){
            for (String nhanVat : item.getcacNhanVatLienQuan()){
                List<Model> nvL = database.getNhanVat();
                int idx = LoadData.binaryLookupByCode(0, nvL.size() - 1, nhanVat, (ArrayList<Model>) nvL);
                if (idx < 0) continue;
                str += nvL.get(idx).getTenModel();
                str += "\n";
            }
        }
        Label lb = new Label(str);
        contentVBox.getChildren().add(lb);
    }
    @FXML
    public void thoiKySearchFieldAction() {
        searchFieldAction((ArrayList<Model>) database.getThoiKy());
    }

    public void handleButtonClick(ActionEvent actionEvent) {
        Button btn = new Button("A new Btn created");
        contentVBox.getChildren().add(btn);

    }

    public void handleButtonClick2(ActionEvent actionEvent) {
        contentVBox.getChildren().clear();
    }
//    public void initThoiKyListView() {
//        listView.getItems().clear();
//        setThoiKyListViewItem();
//    }
}
