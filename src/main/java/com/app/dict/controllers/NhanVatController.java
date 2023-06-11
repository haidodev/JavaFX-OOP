package com.app.dict.controllers;

import com.app.dict.base.DoiTuong;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class NhanVatController extends GeneralController implements Initializable {
    private int cntClick = 0;
    public FlowPane objContainer;
    public Label showAction;
    public Button addingMore;
    public WebView definitionView;
    public Button showTextBtn;
    private final ArrayList<DoiTuong> searchTemp = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (DoiTuong temp : duLieu.getNhanVat()) {
            nhanVatList.add(temp.getSearching());
        }
        listView.setItems(nhanVatList);
    }
    public void setNhanVatListViewItem() {
        nhanVatList.clear();
        if (searchField.getText().equals("")) {
            searchTemp.clear();
            searchTemp.addAll(duLieu.getNhanVat());
        }
        for (DoiTuong temp : searchTemp) {
            nhanVatList.add(temp.getSearching());
        }
        listView.setItems(nhanVatList);
    }
    @FXML
    public void nhanVatSearchFieldAction() throws IOException {
        searchTemp.clear();
        nhanVatList.clear();
        String word = searchField.getText();
        int index = duLieu.binaryLookup(0, duLieu.getNhanVat().size() - 1, word, duLieu.getNhanVat());
        if (index < 0) {
            index = duLieu.binaryLookup(0, duLieu.getNhanVat().size() -1, word, duLieu.getNhanVat());
        }
        updateWordInListView(word, index, duLieu.getNhanVat(), searchTemp);
        setNhanVatListViewItem();
    }
    @FXML
    public void showNhanVatDetail() {
        String spelling = listView.getSelectionModel().getSelectedItem();
        if (spelling == null) {
            return;
        }
        int index = Collections.binarySearch(duLieu.getNhanVat(), new DoiTuong(spelling, null));
        String meaning = duLieu.getNhanVat().get(index).getInfo();
        System.out.println(spelling);
        definitionView.getEngine().loadContent(meaning, "text/html");
    }
    public void searchFieldAction(KeyEvent keyEvent) {
    }

    public void handleClickListView(MouseEvent mouseEvent) {
    }

    public void showDefinition(MouseEvent mouseEvent) {
    }

    public void handleClickBtn(ActionEvent actionEvent) {
        ++cntClick;
        showAction.setText("You clicked " + cntClick + " times." );
    }

    public void addingNewBtns(ActionEvent actionEvent) {
        for (int i = 0; i < 3; ++i){
            Button newBtn = new Button("This is a BTN");

            newBtn.setOnAction(event -> handleClickBtn(null));
            newBtn.setPrefHeight(200);

            newBtn.setPrefWidth(200);
            objContainer.getChildren().add(newBtn);
        }
    }
}
