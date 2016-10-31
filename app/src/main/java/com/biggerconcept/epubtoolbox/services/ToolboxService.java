package com.biggerconcept.epubtoolbox.services;

import com.biggerconcept.epubtoolbox.actions.Action;
import com.biggerconcept.epubtoolbox.exceptions.NoChoiceMadeException;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ToolboxService {
    private Action lastAction;
    public void performAction(Action task) throws Exception {
        task.doAction();
    }
    
    public Action getLastAction() {
        return lastAction;
    }
    
    public void setLastAction(Action action) {
        lastAction = action;
    }
    
    public File chooseDirectory(String prompt, Stage toolboxStage) 
            throws NoChoiceMadeException {
        DirectoryChooser dirChoice = new DirectoryChooser();
        dirChoice.setTitle(prompt);
        File choice = dirChoice.showDialog(toolboxStage);
        if (choice == null) throw new NoChoiceMadeException();
        return choice;
    }
    
    public File chooseFile(String prompt, Stage toolboxStage) 
            throws NoChoiceMadeException {
        FileChooser fileChoice = new FileChooser();
        fileChoice.setTitle(prompt);
        File choice = fileChoice.showOpenDialog(toolboxStage);
        if (choice == null) throw new NoChoiceMadeException();
        return choice;
    }
    
    public File chooseSaveFile(String prompt, Stage toolboxStage) 
            throws NoChoiceMadeException {
        FileChooser fileSaveChoice = new FileChooser();
        fileSaveChoice.setTitle(prompt);
        File choice = fileSaveChoice.showSaveDialog(toolboxStage);
        if (choice == null) throw new NoChoiceMadeException();
        return choice;
    }
    
    public String listPrompt(String title, String headerText, String contentText) 
            throws NoChoiceMadeException {
        TextInputDialog choicePrompt = new TextInputDialog();
        choicePrompt.setTitle(title);
        choicePrompt.setHeaderText(headerText);
        choicePrompt.setContentText(contentText);
        choicePrompt.showAndWait();

        String choices = choicePrompt.getResult();
        if (choices.isEmpty()) throw new NoChoiceMadeException();
        return choices;
    }
    
    public ButtonType yesNoPrompt(AlertType type, String header, String question) 
            throws NoChoiceMadeException {
        Alert confirm = new Alert(
                type,
                question,
                ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText(header);
        confirm.showAndWait();
        
        if (confirm.getResult() == ButtonType.NO) throw new NoChoiceMadeException();
        return confirm.getResult();
    }
}
