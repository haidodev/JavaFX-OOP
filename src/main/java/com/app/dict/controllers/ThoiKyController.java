package com.app.dict.controllers;

import com.app.dict.base.DoiTuong;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ThoiKyController extends GeneralController implements Initializable {
    public Button showTextBtn;
    private final ArrayList<DoiTuong> searchTemp = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (DoiTuong temp : duLieu.getThoiKy()) {
            objectList.add(temp.getSearching());
        }
        listView.setItems(objectList);
    }
    @FXML
    public void showThoiKyDetail() {
        showDetail(duLieu.getThoiKy());
    }
    @FXML
    public void thoiKySearchFieldAction() {
        searchFieldAction(duLieu.getThoiKy());
    }
//    public void initThoiKyListView() {
//        listView.getItems().clear();
//        setThoiKyListViewItem();
//    }
}
