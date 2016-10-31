package support;

import com.biggerconcept.epubtoolbox.ToolboxController;
import com.biggerconcept.epubtoolbox.exceptions.NoChoiceMadeException;
import com.biggerconcept.epubtoolbox.services.*;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.mockito.Matchers;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import org.testfx.framework.junit.ApplicationTest;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.matcher.base.NodeMatchers;

public class ToolboxUITestHarness extends ApplicationTest {
    protected ToolboxController ctrl;
    protected final ToolboxService toolboxSpy = spy(new ToolboxService());
    protected ConsoleService consoleSpy;
    
    protected File testInput;
    protected File testOutput;
    protected File testPrompt;
    protected String listChoices;
     
    @Override
    public void start(Stage stage) throws Exception {   
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Toolbox.fxml"));
        Parent root = loader.load();
        
        ctrl = loader.getController();
        ctrl.toolbox = toolboxSpy;
        
        consoleSpy = spy(new ConsoleService(ctrl.consoleView));
        ctrl.console = consoleSpy;
        
        Scene testScene = new Scene(root);
        testScene.getStylesheets().add("fxml/Toolbox.css");
        
        stage.setScene(testScene);
        stage.show();
    }
    
    protected File testFixtureFile(String fixtureName, String fileName) {
        return new File(
                "src"
                + File.separator
                + "test"
                + File.separator
                + "fixtures"
                + File.separator
                + fixtureName
                + File.separator
                + fileName
        );
    }
    
    protected File testOutputFile(String fixtureName, String fileName) {
        return new File(
                "target"
                + File.separator
                + "test-output"
                + File.separator
                + fixtureName
                + File.separator
                + fileName
        );
    }
    
    protected File testFixtureDirectory(String fixtureName) {
        return new File(
                "src"
                + File.separator
                + "test"
                + File.separator
                + "fixtures"
                + File.separator
                + fixtureName
        );
    }
    
    protected File testOutputDirectory(String fixtureName) {
        return new File(
                "target"
                + File.separator
                + "test-output"
                + File.separator
                + fixtureName
        );
    }
    
    protected static void setupTestOutputFolder(String fixtureName) throws IOException {
        File testOutputFolder = new File(
                "target"
                + File.separator
                + "test-output"
                + File.separator
                + fixtureName
        );
        
        if (testOutputFolder.exists()) FileUtils.deleteDirectory(testOutputFolder);
        FileUtils.forceMkdir((testOutputFolder));
    }
    
    protected void stubSaveFileOutputLocation(File outputLocation) 
            throws NoChoiceMadeException {
        doReturn(outputLocation)
                .when(toolboxSpy)
                .chooseSaveFile(Matchers.anyObject(), Matchers.anyObject());
    }
    
    protected void stubYes() throws NoChoiceMadeException {
        doReturn(ButtonType.YES).when(toolboxSpy).yesNoPrompt(
                Matchers.anyObject(),
                Matchers.anyObject(),
                Matchers.anyObject()
        );
    }
    
    protected void stubNo() throws NoChoiceMadeException {
        doThrow(new NoChoiceMadeException()).when(toolboxSpy).yesNoPrompt(
                Matchers.anyObject(),
                Matchers.anyObject(),
                Matchers.anyObject()
        );
    }
    
    protected void stubListPrompt(String targets) throws NoChoiceMadeException {
        doReturn(targets).when(toolboxSpy).listPrompt(
                Matchers.anyObject(), 
                Matchers.anyObject(), 
                Matchers.anyObject()
        );
    }
    
    protected void stubOpenFilePromptWithNoEbook() throws NoChoiceMadeException {
        doThrow(new NoChoiceMadeException())
                .when(toolboxSpy)
                .chooseFile(Matchers.anyObject(), Matchers.anyObject());
    }
    
    protected void stubOpenFilePromptWithNonEbook() throws NoChoiceMadeException {
        stubChooseFilePrompt(testFixtureFile("Common", "TestNotBook.jpeg"));
    }
    
    protected void stubOpenFilePromptWithValidEbook() throws NoChoiceMadeException {        
        stubChooseFilePrompt(testFixtureFile("Common", "TestPassBook.epub"));
    }
    
    protected void stubOpenFilePromptWithInValidEbook() throws NoChoiceMadeException {
        stubChooseFilePrompt(testFixtureFile("Common", "TestFailBook.epub"));
    }
    
    protected void stubChooseFilePrompt(File choice) 
            throws NoChoiceMadeException {
        doReturn(choice)
                .when(toolboxSpy)
                .chooseFile(Matchers.anyObject(), Matchers.anyObject());
    }
    
    protected void stubChooseDirectoryPrompt(File choice)
            throws NoChoiceMadeException {
        doReturn(choice)
                .when(toolboxSpy)
                .chooseDirectory(Matchers.anyObject(), Matchers.anyObject());
    }
    
    protected void stubChooseDirectoryPrompt(File firstChoice, File secondChoice) 
            throws NoChoiceMadeException {
        doReturn(firstChoice).doReturn(secondChoice)
                .when(toolboxSpy)
                .chooseDirectory(Matchers.anyObject(), Matchers.anyObject());
    }
    
    protected void selectUtilityAction(String actionName) {
        clickOn("#utilsMenu");
        clickOn(actionName);
    }
    
    protected void makeRecording() throws NoChoiceMadeException {
        stubOpenFilePromptWithValidEbook();
        
        clickOn("#recordButton");
        clickOn("#sizeCheckButton");
        clickOn("#stopButton");
    }
    
    protected void fixtureShouldMatchExpected(
            String fixtureName, String fileName) 
            throws IOException {
        System.out.println(" = " + fileName + " should match fixture");
        
        File fixtureFile = testFixtureFile(fixtureName, fileName);
        File actualFile = testOutputFile(fixtureName, fileName);
        
        assertEquals(
                FileUtils.readFileToString(fixtureFile), 
                FileUtils.readFileToString(actualFile));
        
    }
    
    protected void buttonShouldBeDisabled(String buttonId) {
        System.out.println(" = " + buttonId + " should be disabled");
        verifyThat(buttonId, NodeMatchers.isDisabled());
    }
    
    protected void buttonShouldBeEnabled(String buttonId) {
        System.out.println(" = " + buttonId + " should be enabled");
        verifyThat(buttonId, NodeMatchers.isEnabled());
    }
    
    protected void toolboxShouldPerformAction() throws Exception {     
        System.out.println(" = Toolbox performs action");
        
        verify(toolboxSpy, Mockito.times(1))
                .performAction(Matchers.anyObject());
    }
    
    protected void toolboxShouldNotPerformAction() throws Exception {     
        System.out.println(" = Toolbox does not perform action");
        
        verify(toolboxSpy, Mockito.times(0))
                .performAction(Matchers.anyObject());
    }
    
    protected void toolboxShouldReportToConsole(String expectedMessage) throws Exception {        
        System.out.println(" = Toolbox reports the outcome");
        
        verify(consoleSpy, Mockito.times(1))
                .out(Matchers.anyObject(), Matchers.startsWith(expectedMessage));
    }
    
    protected void toolboxActionShouldBeSuccessful() {
        System.out.println(" = Action reports that it was successful");
        assertTrue(toolboxSpy.getLastAction().getResult().isPass());
        assertFalse(toolboxSpy.getLastAction().getResult().isFail());
    }
    
    protected void toolboxActionShouldNotBeSuccessful() {
        System.out.println(" = Action reports that it was unsuccessful");
        assertFalse(toolboxSpy.getLastAction().getResult().isPass());
        assertTrue(toolboxSpy.getLastAction().getResult().isFail());
    }
    
    protected void consoleShouldBeEmpty() {
        System.out.println(" = Console should be empty");
        
        // Outputs is the name of the root node - if this is all that's in the
        // output then it will appear blank to the user.
        assertEquals("Outputs", ctrl.console.toString().trim());
    }
    
    protected void testHasExpectedOutcome(String featureName,String testName) 
            throws IOException {
        String testResultFileName = featureName
                .toLowerCase() + "-" + testName + "-result";
        File testResultFile = testOutputFile(featureName, testResultFileName);
        
        FileUtils.touch(testResultFile);
        FileUtils.writeStringToFile(testResultFile, ctrl.console.toString());
        
        fixtureShouldMatchExpected(featureName, testResultFileName);
    }
    
}
