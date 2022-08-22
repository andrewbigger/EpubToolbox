package com.biggerconcept.epubtoolbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Epub toolbox application
 */
public class App extends Application {
    /**
     * Default scene for the toolbox
     */
    private static Scene scene;

    /**
     * Starts EPUB toolbox
     * 
     * Loads the toolbox view from resources, applies styles and sets the
     * window title and dimensions.
     * 
     * Then the stage will be shown.
     * 
     * @param stage
     * @throws IOException 
     */
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

    /**
     * Main
     * 
     * Launches Toolbox FX application
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch();
    }

}
