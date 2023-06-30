package com.app.dict.controllers;

import com.app.dict.crawl.CrawlAll;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static MainController instance;
    public MainController(){
        instance = this;
    }
    public static MainController getInstance(){
        return instance;
    }
    public Stage primaryStage;
    public Scene dictMainScene;
    public Scene splashScene;
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
    private Button crawlingBtn;

    @FXML
    private AnchorPane thoiKyPane;
    @FXML
    private AnchorPane nhanVatPane;
    @FXML
    private AnchorPane diTichPane;
    @FXML
    private AnchorPane suKienPane;
    @FXML
    private AnchorPane leHoiPane;
    @FXML
    private ThoiKyController thoiKyController;
    @FXML
    private NhanVatController nhanVatController;
    @FXML
    private SuKienController suKienController;
    @FXML
    private DiTichController diTichController;
    @FXML
    private LeHoiController leHoiController;
    @FXML
    public ProgressIndicator loadingCircle;


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

    public void showThoiKyPane() {
        resetStyleNav();
        thoiKyBtn.getStyleClass().add("active");
        setMainContent(thoiKyPane);

    }
    public void linkThoiKyPane(String thoiKyName) {
        showThoiKyPane();
        thoiKyController.preloadThoiKy(thoiKyName);
    }
    public void showNhanVatPane() {
        resetStyleNav();
        nhanVatBtn.getStyleClass().add("active");
        setMainContent(nhanVatPane);
    }
    public void linkNhanVatPane(String nhanVatName) {
        showNhanVatPane();
        nhanVatController.preloadNhanVat(nhanVatName);
    }

    public void showSuKienPane() {
        resetStyleNav();
        suKienBtn.getStyleClass().add("active");
        setMainContent(suKienPane);
    }
    public void linkSuKienPane(String suKienName) {
        showSuKienPane();
        suKienController.preloadSuKien(suKienName);
    }

    public void linkDiTichPane(String diTichName) {
        showDiTichPane();
        diTichController.preloadDiTich(diTichName);
    }
    public void showDiTichPane() {
        resetStyleNav();
        diTichBtn.getStyleClass().add("active");
        setMainContent(diTichPane);
    }

    public void showLeHoiPane() {
        resetStyleNav();
        leHoiBtn.getStyleClass().add("active");
        setMainContent(leHoiPane);
    }
    public void linkLeHoiPane(String leHoiName) {
        showLeHoiPane();
        leHoiController.preloadLeHoi(leHoiName);
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/dict/nhan-vat.fxml"));
            nhanVatPane = loader.load();
            nhanVatController = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/dict/su-kien.fxml"));
            suKienPane = loader.load();
            suKienController = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/dict/di-tich.fxml"));
            diTichPane = loader.load();
            diTichController = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/dict/le-hoi.fxml"));
            leHoiPane = loader.load();
            leHoiController = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        thoiKyBtn.getStyleClass().add("active");
        setMainContent(thoiKyPane);
    }

    public void setScenes(Stage primaryStage, Scene scene, Scene splashScene) {
        this.primaryStage = primaryStage;
        this.dictMainScene = scene;
        this.splashScene = splashScene;
    }

    public void showSplashScreenAndRunMethod() {
        primaryStage.setScene(splashScene);
        primaryStage.show();

        Task<Void> crawlTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                CrawlAll crawlAll = new CrawlAll();
                crawlAll.crawl();
                crawlAll.link();
                return null;
            }
        };

        crawlTask.setOnSucceeded(e -> {
            primaryStage.setScene(dictMainScene);
            primaryStage.show();
        });

        // Run the task in a separate thread
        Thread crawlThread = new Thread(crawlTask);
        crawlThread.start();
    }
}