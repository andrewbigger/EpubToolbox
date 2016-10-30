package com.biggerconcept.epubtoolbox;

import com.biggerconcept.epubtoolbox.exceptions.NoChoiceMadeException;
import com.biggerconcept.epubtoolbox.services.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

public class ToolboxController implements Initializable {
    public ToolboxService toolbox;
    public ConsoleService console;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        toolbox = new ToolboxService();
        console = new ConsoleService(consoleView);
    }
    
    @FXML
    public TreeView<String> consoleView;
    
    @FXML
    private void handleUnpackClick(MouseEvent event) {
        try { System.out.println("Unpack"); }
        catch (Exception e) {
            
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
}
