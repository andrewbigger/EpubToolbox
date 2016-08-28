package com.biggerconcept.epubtoolboxlauncher;

import fxlauncher.FXManifest;
import fxlauncher.UIProvider;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Splash implements UIProvider {
    public static final String APP_NAME = "EpubToolbox";
    public static final String LOAD_STATUS = "Firing Up...";
    public static final String UPDATE_STATUS = "Updating, please wait...";
    public static final String FOOTER_TEXT = 
            "© 2016 Bigger Concept. All Rights Reserved.";
            
    private Stage stage;
    private ProgressBar updateProgress;
    
    @Override
    public void init(Stage stage) {
        this.stage = stage;
        stage
            .getScene()
            .getStylesheets()
            .add(getClass()
                .getResource("/styles/Styles.css")
                .toExternalForm()
            );
        
         updateProgress = new ProgressBar();
         updateProgress.setId("updateProgressBar");
    }
    
    @Override
    public Parent createLoader() {
        stage.setTitle("Loading " + APP_NAME);
        
        BorderPane loadPane = buildSplashPane();
        
        loadPane.setCenter(
                buildHeader(
                        APP_NAME,
                        LOAD_STATUS,
                        buildLoadIndicator()
                )
        );
        
        return loadPane;
    }
    
    @Override
    public Parent createUpdater(FXManifest manifest) {
        stage.setTitle("Updating " + APP_NAME);
        
        BorderPane updatePane = buildSplashPane();
        
        updatePane.setCenter(
                buildHeader(
                        APP_NAME,
                        UPDATE_STATUS,
                        updateProgress
                )
        );
        
        return updatePane;
    }

    @Override
    public void updateProgress(double progress) {
        updateProgress.setProgress(progress);
    }
    
        private BorderPane buildSplashPane() {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("splash");
        
        root.setBottom(buildFooter());
        
        return root;
    }
    
    private Control buildLoadIndicator() {
        ProgressIndicator loadIndicator = new ProgressIndicator();
        loadIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        loadIndicator.getStyleClass().add("prog");
        loadIndicator.setId("loadIndicator");
        
        return loadIndicator;
    }
    
    private VBox buildHeader(String title, String status, Control indicator) {
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        header.setPrefSize(300, 500);

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("title");
        titleLabel.setId("titleLabel");
        
        Label statusLabel = new Label(status);
        statusLabel.setId("statusLabel");
        
        header.getChildren().addAll(titleLabel, statusLabel, indicator);
        
        return header;
    }
    
    private VBox buildFooter() {
        VBox footer = new VBox();
        footer.setAlignment(Pos.CENTER);
        footer.setPrefSize(300, 40);
        footer.getStyleClass().add("footer");
        
        Label footerLabel = new Label(FOOTER_TEXT);
        footerLabel.setId("footerLabel");
        
        footer.getChildren().add(footerLabel);
        
        return footer;
    }
}