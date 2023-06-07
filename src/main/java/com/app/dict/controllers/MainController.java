package com.app.dict.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public AnchorPane mainContent;
    @FXML
    private Button thoiKyBtn;
    @FXML
    private Button nhanVatBtn;
    @FXML
    private Button suKienBtn;
    @FXML
    private Button diTichBtn;
    @FXML
    private Button leHoiBtn;

    @FXML
    private AnchorPane thoiKyPane;
    private ThoiKyController thoiKyController;
    private NhanVatController nhanVatController;
    private SuKienController suKienController;
    private DiTichController diTichController;
    private LeHoiController leHoiController;

    private void setMainContent(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }
    public void resetStyleNav() {
        thoiKyBtn.getStyleClass().removeAll("active");
        nhanVatBtn.getStyleClass().removeAll("active");
        suKienBtn.getStyleClass().removeAll("active");
        diTichBtn.getStyleClass().removeAll("active");
        leHoiBtn.getStyleClass().removeAll("active");
    }

    public void showThoiKyPane(ActionEvent actionEvent) {
        resetStyleNav();
        thoiKyBtn.getStyleClass().add("active");
        setMainContent(thoiKyPane);

    }

    public void showNhanVatPane(ActionEvent actionEvent) {
        resetStyleNav();
        nhanVatBtn.getStyleClass().add("active");
    }

    public void showSuKienPane(ActionEvent actionEvent) {
        resetStyleNav();
        suKienBtn.getStyleClass().add("active");
    }

    public void showDiTichPane(ActionEvent actionEvent) {
        resetStyleNav();
        diTichBtn.getStyleClass().add("active");
    }

    public void showLeHoiPane(ActionEvent actionEvent) {
        resetStyleNav();
        leHoiBtn.getStyleClass().add("active");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/dict/thoi-ky.fxml"));
            thoiKyPane = loader.load();
            thoiKyController = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setMainContent(thoiKyPane);


    }
}
