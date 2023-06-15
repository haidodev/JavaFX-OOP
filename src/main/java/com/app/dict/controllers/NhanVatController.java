package com.app.dict.controllers;

import com.app.dict.base.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NhanVatController extends GeneralController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Model temp : database.getNhanVat()) {
            objectList.add(temp.getTenModel());
        }
        listView.setItems(objectList);

    }
    public void initializer(String spelling) {
        listView.getSelectionModel().select(spelling);
        showDetail((ArrayList<Model>) database.getNhanVat(), spelling);

    }
    @FXML
    public void nhanVatSearchFieldAction() {
        searchFieldAction((ArrayList<Model>) database.getNhanVat());
    }
    @FXML
    public void showNhanVatDetail() {
        showDetail((ArrayList<Model>) database.getNhanVat());
    }
}
