package com.biggerconcept.epubtoolbox.services;

import com.biggerconcept.epubtoolbox.actions.Action;
import com.biggerconcept.epubtoolbox.exceptions.NoChoiceMadeException;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Toolbox view model.
 * 
 * @author Andrew Bigger
 */
public class ToolboxService {
    /**
     * Boolean to indicate whether actions are being recorded.
     * 
     * When rec is true, this means that the actions should be saved into the
     * recording array list, so they can be replayed later.
     */
    private boolean rec = false;
    
    /**
     * Array list of actions that were performed while the toolbox was
     * recording.
     * 
     * This allows the user to replay the actions later.
     */
    private final ArrayList recording = new ArrayList<>();
    
    /**
     * Pointer to the last performed action.
     */
    private Action lastAction;
    
    /**
     * Returns the last performed action to the caller.
     * 
     * @return 
     */
    public Action getLastAction() {
        return lastAction;
    }
    
    /**
     * Sets last action to the given action.
     * 
     * This allows the UI to persist the last action.
     * 
     * @param action 
     */
    public void setLastAction(Action action) {
        lastAction = action;
    }
    
    /**
     * Boolean to indicate whether the toolbox service has a last action
     * available to execute.
     * 
     * This returns true if the lastAction instance variable is null, which
     * means that there is no last action to perform.
     * 
     * @return 
     */
    public boolean hasLastAction() {
        return lastAction != null;
    }
    
    /**
     * Returns the currently set recording.
     * 
     * This will return an array list of actions that can be replayed on
     * demand.
     * 
     * @return 
     */
    public ArrayList<Action> getRecording() {
        return recording;
    }
    
    /**
     * Adds the given action to the recording.
     * 
     * This is called when the toolbox is in recording state and has been
     * asked to perform another action.
     * 
     * @param action 
     */
    public void addToRecording(Action action) {
        recording.add(action);
    }
    
    /**
     * Empties the recording array list, so that the recording can be
     * over written.
     */
    public void clearRecording() {
        recording.clear();
    }
    
    /**
     * Boolean that indicates whether the toolbox service has a recording
     * available.
     * 
     * A recording is available if the recording instance variable has any
     * actions in it.
     * 
     * If there are no actions this will return false.
     * 
     * @return 
     */
    public boolean hasRecording() {
        return !recording.isEmpty();
    }
    
    /**
     * Boolean to indicate whether the toolbox is in recording mode.
     * 
     * If the toolbox service is recording then this will be true, otherwise
     * it will be false. This is used to determine whether to add an action
     * to the recording.
     * 
     * @return 
     */
    public boolean isRecording()      { 
        return rec; 
    }
    
    /**
     * Sets the recording state to the given value.
     * 
     * When true, the toolbox will record actions and place them into the
     * recording instance variable. When false, the actions will be performed
     * without caching them.
     * 
     * @param state 
     */
    public void setRecordingStatus(boolean state) {
        rec = state;
    }
    
    /**
     * Initiates an action.
     * 
     * When the action has been executed, it will be added to the last action
     * so that it can be repeated.
     * 
     * When the toolbox is recording, then the action will be added to the
     * recording.
     * 
     * @param task
     * @throws Exception 
     */
    public void performAction(Action task) throws Exception {
        task.doAction();
        setLastAction(task);
        if (isRecording() == true) { addToRecording(task); }
    }
    
    /**
     * Creates and displays a directory choice dialog.
     * 
     * This dialog will be shown on the given stage, and when closed, the 
     * choice the user made will be returned to the caller.
     * 
     * If no choice is made, then a NoChoiceMadeException will be thrown.
     * 
     * @param prompt
     * @param toolboxStage
     * @return
     * @throws NoChoiceMadeException 
     */
    public File chooseDirectory(String prompt, Stage toolboxStage) 
            throws NoChoiceMadeException {
        DirectoryChooser dirChoice = new DirectoryChooser();
        dirChoice.setTitle(prompt);
        File choice = dirChoice.showDialog(toolboxStage);
        if (choice == null) throw new NoChoiceMadeException();
        return choice;
    }
    
    /**
     * Creates and displays a file choice dialog.
     * 
     * This dialog will be shown on the given stage, and when closed, the
     * choice the user made will be returned to the caller.
     * 
     * If no choice is made, then a NoChoiceMadeException will be thrown.
     * 
     * @param prompt
     * @param toolboxStage
     * @return
     * @throws NoChoiceMadeException 
     */
    public File chooseFile(String prompt, Stage toolboxStage) 
            throws NoChoiceMadeException {
        FileChooser fileChoice = new FileChooser();
        fileChoice.setTitle(prompt);
        File choice = fileChoice.showOpenDialog(toolboxStage);
        if (choice == null) throw new NoChoiceMadeException();
        return choice;
    }
    
    /**
     * Creates and displays a file save dialog.
     * 
     * This dialog will be shown on the given stage, and when closed, the 
     * choice the user made will be returned to the caller.
     * 
     * If no choice is made, then a NoChoiceMadeException will be thrown.
     * 
     * @param prompt
     * @param toolboxStage
     * @return
     * @throws NoChoiceMadeException 
     */
    public File chooseSaveFile(String prompt, Stage toolboxStage) 
            throws NoChoiceMadeException {
        FileChooser fileSaveChoice = new FileChooser();
        fileSaveChoice.setTitle(prompt);
        File choice = fileSaveChoice.showSaveDialog(toolboxStage);
        if (choice == null) throw new NoChoiceMadeException();
        return choice;
    }
    
    /**
     * Creates and displays a list choice dialog box.
     * 
     * This dialog will be shown on the given stage, with the given choices.
     * 
     * When closed the choice will be returned to the caller.
     * 
     * If no choice is made, then a NoChoiceMadeException will be thrown.
     * 
     * @param title
     * @param headerText
     * @param contentText
     * @return
     * @throws NoChoiceMadeException 
     */
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
    
    /**
     * Creates and displays a yes no dialog box.
     * 
     * This dialog is for asking a user a yes or no question.
     * 
     * The dialog is created and shown, and the result is returned to the user.
     * 
     * If there is no choice made then a NoChoiceException will be thrown.
     * 
     * @param type
     * @param header
     * @param question
     * @return
     * @throws NoChoiceMadeException 
     */
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
