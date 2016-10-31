package com.biggerconcept.epubtoolbox.features;

import java.io.IOException;
import org.junit.BeforeClass;
import support.ToolboxUITestHarness;
import org.junit.Test;

public class ImageCheckFeature extends ToolboxUITestHarness {
    
    @BeforeClass
    public static void setup() throws IOException {
        setupTestOutputFolder("ImageCheckFeature");
    }
    
    @Test
    public void scenario_ImageCheckOnValidEpub() throws Exception {
        System.out.println("When user runs image size check on a valid epub");
        
        stubOpenFilePromptWithValidEbook();
        clickOn("#imageCheckButton");
        
        toolboxShouldPerformAction();
        testHasExpectedOutcome("ImageCheckFeature", "valid-epub");
        toolboxActionShouldBeSuccessful();
    }
    
    @Test
    public void scenario_ImageCheckOnInvalidEpub() throws Exception {
        System.out.println("When user runs image size check on an invalid epub");
        
        stubOpenFilePromptWithInValidEbook();
        
        clickOn("#imageCheckButton");
        
        toolboxShouldPerformAction();
        testHasExpectedOutcome("ImageCheckFeature", "invalid-epub");
        toolboxActionShouldNotBeSuccessful();
    }
    
    @Test
    public void scenario_NoEpubChosen() throws Exception {
        System.out.println("When user cancels epub choice prompt");
        
        stubOpenFilePromptWithNoEbook();
        
        clickOn("#imageCheckButton");
        
        toolboxShouldNotPerformAction();
        consoleShouldBeEmpty();
    }
    
    @Test
    public void scenario_NonEpubChosen() throws Exception {
        System.out.println("When user chooses a file which is not an epub");
        
        stubOpenFilePromptWithNonEbook();
        
        clickOn("#imageCheckButton");
        
        testHasExpectedOutcome("ImageCheckFeature", "not-epub");
    }
   
}
