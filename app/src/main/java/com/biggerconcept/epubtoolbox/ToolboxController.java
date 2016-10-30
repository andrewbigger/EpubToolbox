package com.biggerconcept.epubtoolbox;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;

public class ToolboxController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { }    
    
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
