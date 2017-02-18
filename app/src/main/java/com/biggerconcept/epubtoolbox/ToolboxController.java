package com.biggerconcept.epubtoolbox;

import com.biggerconcept.epubtoolbox.exceptions.NoChoiceMadeException;
import com.biggerconcept.epubtoolbox.services.*;
import com.biggerconcept.epubtoolbox.actions.*;
import com.biggerconcept.epubtoolbox.results.Message;
import com.biggerconcept.epubtoolbox.utilities.PickUtility;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

public class ToolboxController implements Initializable {
    public ToolboxService toolbox;
    public ConsoleService console;
    public TooltipService tips;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        toolbox = new ToolboxService();
        console = new ConsoleService(consoleView);
        tips = new TooltipService();
        
        setupTooltips();
    }
    
    private void setupTooltips() {          
        unpackButton.setTooltip(
                new Tooltip(tips.getTip("unpackButton"))
        );
        packButton.setTooltip(
                new Tooltip(tips.getTip("packButton"))
        );
        
        pickButton.setTooltip(
                new Tooltip(tips.getTip("pickButton"))
        );
        epubCheckButton.setTooltip(
                new Tooltip(tips.getTip("epubCheckButton"))
        );
        imageCheckButton.setTooltip(
                new Tooltip(tips.getTip("imageCheckButton"))
        );
        sizeCheckButton.setTooltip(
                new Tooltip(tips.getTip("sizeCheckButton"))
        );
        allCheckButton.setTooltip(
                new Tooltip(tips.getTip("allCheckButton"))
        );
        recordButton.setTooltip(
                new Tooltip(tips.getTip("recordButton"))
        );
        stopButton.setTooltip(
                new Tooltip(tips.getTip("stopButton"))
        );
        playButton.setTooltip(
                new Tooltip(tips.getTip("playButton"))
        );
        repeatButton.setTooltip(
                new Tooltip(tips.getTip("repeatButton"))
        );
        clearConsoleButton.setTooltip(
                new Tooltip(tips.getTip("clearConsoleButton"))
        );
        runOnCollectionChoice.setTooltip(
                new Tooltip(tips.getTip("runOnCollectionChoice"))
        );
        exportLogButton.setTooltip(
                new Tooltip(tips.getTip("exportLogButton"))
        ); 
        
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
    public Button unpackButton;
    
    @FXML
    public Button packButton;
    
    @FXML
    public Button pickButton;
    
    @FXML
    public Button epubCheckButton;
    
    @FXML
    public Button imageCheckButton;
    
    @FXML
    public Button sizeCheckButton;
    
    @FXML
    public Button allCheckButton;
    
    @FXML
    public TreeView<String> consoleView;
    
    @FXML
    private CheckBox runOnCollectionChoice;

    @FXML
    private Button recordButton;

    @FXML
    private Button playButton;

    @FXML
    private Button stopButton;
    
    @FXML
    private Button repeatButton;
    
    @FXML
    private Button clearConsoleButton;
    
    @FXML
    private Button exportLogButton;

    public boolean isCollectionMode() {
        return runOnCollectionChoice.isSelected();
    }
    
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
        try {
            // Choose expanded epub(s) for packing
            File inFile = makeInputChoice(true, "Choose expanded",
                    "for packing");
            // Choose packed epub(s) output location
            File outFileChoice = makeInputChoice(true, "Choose packed",
                    "output location");
            
            // Add epub name to output location
            File outFile = new File(
                    outFileChoice.getAbsolutePath() 
                    + File.separator
                    + inFile.getName() 
                    + ".epub"
            );
            
            if (inFile.exists() && outFileChoice.exists()) {
                Action packAction = new UtilityAction(
                        "Pack Utility", inFile, outFile, isCollectionMode());

                toolbox.performAction(packAction);
                console.out(packAction.getResult(), packAction.getTask());
            }

        }
        catch (NoChoiceMadeException ncm) {
            // Do nothing
        }
        catch (Exception e) {
            console.err("Epub pack", e);
        }
    }
    
    @FXML
    private void handlePickClick(MouseEvent event) {
        try {
            File collectionLocation = makeInputChoice(true,
                    "Choose",
                    "collection location");

            String choices = toolbox.listPrompt(
                    "Pick ePubs from folder",
                    "Please enter the names of the ePubs"
                    + " you'd like to select (separated by commas):",
                    "i.e.: 'spain.epub, madrid.epub' etc.");

            String [] targets = choices.split(",");
            File outLocation = makeInputChoice(true, "Choose",
                    "selection output location");

            PickUtility pickUtility = new PickUtility(
                    collectionLocation, outLocation, targets);
            pickUtility.doJob();

            console.out(pickUtility.getResult(), "Pick ePubs from Folder");
        }
        catch (NoChoiceMadeException ncm) {
            // Do nothing
        }
        catch (Exception e) {
            console.err("Epub selection", e);
        }
    }
    
    @FXML
    private void handleEpubCheckClick(MouseEvent event) {
        try {
            // Choose epub(s) for epubcheck
            File inFile = makeInputChoice(false, "Choose", "for epub check");

            if (inFile.exists()) {
                Action epubCheck = new CheckerAction(
                        "Epub Checker", inFile, isCollectionMode());

                toolbox.performAction(epubCheck);
                console.out(epubCheck.getResult(), epubCheck.getTask());
            }

        }
        catch (NoChoiceMadeException ncm) {
            // Do nothing
        }
        catch (Exception e) {
            console.err("Epub check", e);
        }
    }
    
    @FXML
    private void handleImageCheckClick(MouseEvent event) {
        try {
            // Choose epub(s) for image check
            File inFile = makeInputChoice(false, "Choose", "for image check");

            if (inFile.exists()) {
                Action imageSizeCheck = new CheckerAction(
                        "Image Size Checker", inFile, isCollectionMode());

                toolbox.performAction(imageSizeCheck);
                console.out(imageSizeCheck.getResult(), imageSizeCheck.getTask());
            }

        }
        catch (NoChoiceMadeException ncm) {
            // Do nothing
        }
        catch (Exception e) {
            console.err("Epub image check", e);
        }
    }
    
    @FXML
    private void handleSizeCheckClick(MouseEvent event) {
        try {
            // Choose epub(s) for file size check
            File inFile = makeInputChoice(false, "Choose", "for file size check");

            if (inFile.exists()) {
                Action fileSizeCheck = new CheckerAction(
                        "File Size Checker", inFile, isCollectionMode());

                toolbox.performAction(fileSizeCheck);
                console.out(fileSizeCheck.getResult(), fileSizeCheck.getTask());
            }

        }
        catch (NoChoiceMadeException ncm) {
            // Do nothing
        }
        catch (Exception e) {
            console.err("Epub size check", e);
        }
    }
    
    @FXML
    private void handleAllCheckClick(MouseEvent event) {
        try {
            // Choose epub(s) for all check
            File inFile = makeInputChoice(false, "Choose", "for all checks");

            if (inFile.exists()) {
                Action allCheck = new CheckerAction(
                        "All Checks", inFile, isCollectionMode());

                toolbox.performAction(allCheck);
                console.out(allCheck.getResult(), allCheck.getTask());
            }

        }
        catch (NoChoiceMadeException ncm) {
            // Do nothing
        }
        catch (Exception e) {
            console.err("All checks", e);
        }
    }
    
    @FXML
    private void handleArtefactRemove(ActionEvent event) {
        try {
            File inFile = makeInputChoice(false, "Choose",
                    "for OS artefact removal");

            toolbox.yesNoPrompt(
                    AlertType.CONFIRMATION,
                    "OS Artefact Removal",
                    "Are you sure you want to remove OS artefacts (like "
                    + "thumbs.db, .DS_Store etc.) from this epub?");

            Action osArtefactRemovalUtility = new UtilityAction(
                    "OS Artefact Removal Utility", inFile, inFile,
                    isCollectionMode());
            toolbox.performAction(osArtefactRemovalUtility);

            console.out(osArtefactRemovalUtility.getResult(), 
                    osArtefactRemovalUtility.getTask());
        }
        catch (NoChoiceMadeException ncm) {
            // Do nothing
        }
        catch (Exception e) {
            console.err("OS Artefact removal", e);
        }
    }
    
    @FXML
    private void handleItunesMetaRemove(ActionEvent event) {
        try {
            // Choose epub(s) for image itunes meta removal
            File inFile = makeInputChoice(false, "Choose",
                    "for itunes meta removal");

            toolbox.yesNoPrompt(
                    AlertType.CONFIRMATION,
                    "iTunes Metadata Removal",
                    "Are you sure you want to remove iTunes metadata from "
                    + "this epub?");

            Action itunesMetaRemoval = new UtilityAction(
                    "Itunes Meta Removal Utility", inFile,
                    inFile, isCollectionMode());
            toolbox.performAction(itunesMetaRemoval);

            console.out(itunesMetaRemoval.getResult(), 
                    itunesMetaRemoval.getTask());
        }
        catch (NoChoiceMadeException ncm) {
            // Do nothing
        }
        catch (Exception e) {
            console.err("Itunes metadata removal", e);
        }
    }
    
    @FXML
    private void handleRecordClick(MouseEvent event) {
        console.out(new Message("info", ">> Start Recording"));

        toolbox.clearRecording();
        toolbox.setRecordingStatus(true);

        this.recordButton.setDisable(true);
        this.stopButton.setDisable(false);
        this.playButton.setDisable(true);
    }

    @FXML
    private void handleStopClick(MouseEvent event) {
        console.out(new Message("info", ">> Stop Recording"));

        toolbox.setRecordingStatus(false);

        this.recordButton.setDisable(false);
        this.stopButton.setDisable(true);

        if (toolbox.hasRecording()) { this.playButton.setDisable(false); }
    }

    @FXML
    private void handlePlayClick(MouseEvent event) {
        if (!toolbox.hasRecording()) { return; }

        try {
            console.out(new Message("info", ">> Replay Action"));
            for (Action action : toolbox.getRecording()) {
                action.resetResult();
                
                toolbox.performAction(action);
                console.out(action.getResult(), action.getTask());
            }
            console.out(new Message("info", ">> End Replay"));
        }
        catch (Exception e) {
            console.err("Action replay", e);
        }
    }

    @FXML
    private void handleRepeatClick(MouseEvent event) {
        if (!toolbox.hasLastAction()) { return; }

        try {
            console.out(new Message("info", ">> Repeat Action"));
            Action lastAction = toolbox.getLastAction();
            lastAction.resetResult();
            
            toolbox.performAction(lastAction);
            console.out(lastAction.getResult(), lastAction.getTask());
            console.out(new Message("info", ">> End Repeat"));
        }
        catch (Exception e) {
            console.err("Action repeat", e);
        }
    }

    @FXML
    private void handleClearClick(MouseEvent event) {
        console.clear();
    }

    @FXML
    private void handleExportClick(MouseEvent event) {
        try {
            File outFile = toolbox.chooseSaveFile(
                    "Choose console export location", toolboxStage());
            FileUtils.write(outFile, console.toString());
            console.out(new Message("info", ">> Export complete"));
       }
        catch (NoChoiceMadeException ncm) {
            // Do nothing
        }
        catch (Exception e) {
            console.err("Console export", e);
        }
   }
}
