package com.biggerconcept.epubtoolbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/fxml/Toolbox.fxml")
        );
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/fxml/Toolbox.css");
        
        stage.setTitle("EpubToolbox");
        stage.setScene(scene);
        stage.setMinHeight(400);
        stage.setMinWidth(615);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}