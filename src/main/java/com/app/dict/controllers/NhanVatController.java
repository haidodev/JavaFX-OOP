package com.app.dict.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class NhanVatController implements Initializable {
    private int cntClick = 0;
    public FlowPane objContainer;
    public Label showAction;
    public Button addingMore;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addingMore.setPrefHeight(100);
        addingMore.setPrefWidth(100);
    }
}
