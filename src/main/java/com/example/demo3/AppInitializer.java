package com.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/view/MainLayout.fxml"));
        Scene scene = new Scene(load);

        stage.setTitle("Supermarket FX");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/supermarket_logo.jpg")));
        stage.setScene(scene);
        stage.show();
    }
}
