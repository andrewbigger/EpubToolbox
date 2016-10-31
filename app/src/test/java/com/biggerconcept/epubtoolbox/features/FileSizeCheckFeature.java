package com.biggerconcept.epubtoolbox.features;

import java.io.IOException;
import org.junit.BeforeClass;
import support.ToolboxUITestHarness;
import org.junit.Test;

public class FileSizeCheckFeature extends ToolboxUITestHarness {
    
    @BeforeClass
    public static void setup() throws IOException {
        setupTestOutputFolder("FileSizeCheckFeature");
    }
    
    @Test
    public void scenario_FileSizeCheckOnValidEpub() throws Exception {
        System.out.println("When user runs file size check on a valid epub");
        
        stubOpenFilePromptWithValidEbook();
        clickOn("#sizeCheckButton");
        
        toolboxShouldPerformAction();
        testHasExpectedOutcome("FileSizeCheckFeature", "valid-epub");
        toolboxActionShouldBeSuccessful();
    }
    
    @Test
    public void scenario_FileSizeCheckOnInvalidEpub() throws Exception {
        System.out.println("When user runs file size check on an invalid epub");
        
        stubOpenFilePromptWithInValidEbook();
        
        clickOn("#sizeCheckButton");
        
        toolboxShouldPerformAction();
        testHasExpectedOutcome("FileSizeCheckFeature", "invalid-epub");
        toolboxActionShouldNotBeSuccessful();
    }
    
    @Test
    public void scenario_NoEpubChosen() throws Exception {
        System.out.println("When user cancels epub choice prompt");
        
        stubOpenFilePromptWithNoEbook();
        
        clickOn("#sizeCheckButton");
        
        toolboxShouldNotPerformAction();
        consoleShouldBeEmpty();
    }
    
    @Test
    public void scenario_NonEpubChosen() throws Exception {
        System.out.println("When user chooses a file which is not an epub");
        
        stubOpenFilePromptWithNonEbook();
        
        clickOn("#sizeCheckButton");
        
        testHasExpectedOutcome("FileSizeCheckFeature", "not-epub");
    }
  
}
