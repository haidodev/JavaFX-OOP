package com.app.dict;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SplashScreenExample extends Application {

    private Stage primaryStage;
    private Scene mainScene;
    private Scene splashScene;
    private ProgressIndicator loadingCircle;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Create the main scene with a button
        Button button = new Button("Run Method");
        button.setOnAction(event -> showSplashScreenAndRunMethod());

        StackPane mainRoot = new StackPane(button);
        mainScene = new Scene(mainRoot, 200, 200);

        // Create the splash screen with a loading circle
        loadingCircle = new ProgressIndicator();
        StackPane splashRoot = new StackPane(loadingCircle);
        splashScene = new Scene(splashRoot, 200, 200);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Splash Screen Example");
        primaryStage.show();
    }

    private void showSplashScreenAndRunMethod() {
        primaryStage.setScene(splashScene);
        loadingCircle.setVisible(true);

        // Simulate running a method
        Task<Void> methodTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Simulate some time-consuming operation
                Thread.sleep(3000);
                return null;
            }
        };

        methodTask.setOnSucceeded(e -> {
            // Return to the main scene once the method is finished
            primaryStage.setScene(mainScene);
            loadingCircle.setVisible(false);
        });

        // Run the task in a separate thread
        Thread methodThread = new Thread(methodTask);
        methodThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

