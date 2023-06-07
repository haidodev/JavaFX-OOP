package com.app.dict;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class DictMainController {
    public AnchorPane mainContent;
    public Button searchButton;
    @FXML
    public void showSearchPane(ActionEvent actionEvent) {
        searchButton.getStyleClass().add("active");
    }

    public void showTranslatePane(ActionEvent actionEvent) {

    }

    public void showBookmarkPane(ActionEvent actionEvent) {
    }

    public void showHistoryPane(ActionEvent actionEvent) {
    }

    public void showSettingPane(ActionEvent actionEvent) {
    }
}
