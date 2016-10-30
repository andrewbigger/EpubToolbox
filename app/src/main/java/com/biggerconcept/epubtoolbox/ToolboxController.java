package com.biggerconcept.epubtoolbox;

import com.biggerconcept.epubtoolbox.exceptions.NoChoiceMadeException;
import com.biggerconcept.epubtoolbox.services.*;
import com.biggerconcept.epubtoolbox.actions.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ToolboxController implements Initializable {
    public ToolboxService toolbox;
    public ConsoleService console;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        toolbox = new ToolboxService();
        console = new ConsoleService(consoleView);
    }
    
    private Stage toolboxStage() {
        Stage stage = (Stage) consoleView.getScene().getWindow();
        return stage;
    }
    
    public File makeInputChoice(
        boolean dirChoice, String choice, String prompt)
        throws NoChoiceMadeException {

        if (dirChoice | isCollectionMode()) {
            return toolbox.chooseDirectory(
                    choice + " epubs " + prompt, toolboxStage());
        }
        else {
            return toolbox.chooseFile(
                    choice + " epub " + prompt, toolboxStage());
        }
    }
    
    @FXML
    public TreeView<String> consoleView;
    
    @FXML
    private void handleUnpackClick(MouseEvent event) {
        try {
            // Choose epub for expansion
            File inFile = makeInputChoice(false, "Choose", "for expansion");
            
            // Choose expansion location
            File outFileChoice = makeInputChoice(true, "Choose ",
                    " expansion location");
            
            // Expand into directory with epub name
            File outFile = new File(
                    outFileChoice.getAbsolutePath()
            );
            
             Action unpackAction = new UtilityAction(
                        "Unpack Utility", inFile, outFile, isCollectionMode());

             toolbox.performAction(unpackAction);
             console.out(unpackAction.getResult(), unpackAction.getTask());
        }
        catch (NoChoiceMadeException ncm) {
            // Do nothing
        }
        catch (Exception e) {
            console.err("Epub expansion", e);
        }
    }
    
    @FXML
    private void handlePackClick(MouseEvent event) {
        try { }
        catch (Exception e) {
            
        }
    }
    
    @FXML
    private void handlePickClick(MouseEvent event) {
        try { }
        catch (Exception e) {
            
        }
    }
    
    @FXML
    private void handleEpubCheckClick(MouseEvent event) {
        try { }
        catch (Exception e) {
            
        }
    }
    
    @FXML
    private void handleImageCheckClick(MouseEvent event) {
        try { }
        catch (Exception e) {
            
        }
    }
    
    @FXML
    private void handleSizeCheckClick(MouseEvent event) {
        try { }
        catch (Exception e) {
            
        }
    }
    
    @FXML
    private void handleAllCheckClick(MouseEvent event) {
        try { }
        catch (Exception e) {
            
        }
    }
    
    @FXML
    private void handleArtefactRemove(ActionEvent event) {
        try { }
        catch (Exception e) {
            
        }
    }
    
    @FXML
    private void handleItunesMetaRemove(ActionEvent event) {
        try { }
        catch (Exception e) {
            
        }
    }

    private boolean isCollectionMode() {
        return false;
    }
}
