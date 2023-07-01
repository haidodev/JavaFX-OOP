package com.app.dict;
import com.app.dict.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class dictApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(dictApplication.class.getResource("dict-main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 857, 600);
        MainController controller = fxmlLoader.getController();

        FXMLLoader fxmlSplashLoader = new FXMLLoader(getClass().getResource("/com/app/dict/splash_screen.fxml"));
        Scene splashScene = new Scene(fxmlSplashLoader.load(), 857, 600);

        controller.setScenes(stage, scene, splashScene);
        stage.setTitle("My Dict");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
