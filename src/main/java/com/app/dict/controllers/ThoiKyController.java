package com.app.dict.controllers;

import com.app.dict.base.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ThoiKyController extends GeneralController implements Initializable {
    public ScrollPane scbar;
    public AnchorPane outerAnchorPane;
    public VBox contentVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getThoiKy()) {
            objectList.add(temp.getName());
        }
        listView.setItems(objectList);
    }
    @FXML
    public void showThoiKyDetail() {
        showDetail((ArrayList<Model>) database.getThoiKy());
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
