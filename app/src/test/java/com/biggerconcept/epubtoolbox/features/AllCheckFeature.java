package com.biggerconcept.epubtoolbox.features;

import java.io.IOException;
import org.junit.BeforeClass;
import support.ToolboxUITestHarness;
import org.junit.Test;

public class AllCheckFeature extends ToolboxUITestHarness {
    
    @BeforeClass
    public static void setup() throws IOException {
        setupTestOutputFolder("AllCheckFeature");
    }
 
    @Test
    public void scenario_AllChecksOnValidEpub() throws Exception {
        System.out.println("When user runs all checks on a valid epub");
        
        stubOpenFilePromptWithValidEbook();
        
        clickOn("#allCheckButton");
        
        toolboxShouldPerformAction();
        
        testHasExpectedOutcome("AllCheckFeature", "valid-epub");
        
        toolboxActionShouldBeSuccessful();
    }
    
    @Test
    public void scenario_AllChecksOnInvalidEpub() throws Exception {
        System.out.println("When user runs all checks on an invalid epub");
        
        stubOpenFilePromptWithInValidEbook();
        
        clickOn("#allCheckButton");
        
        toolboxShouldPerformAction();
        
        testHasExpectedOutcome("AllCheckFeature", "invalid-epub");
        
        toolboxActionShouldNotBeSuccessful();
    }
    
    @Test
    public void scenario_NoEpubChosen() throws Exception {
        System.out.println("When user cancels epub choice prompt");
        
        stubOpenFilePromptWithNoEbook();
        
        clickOn("#allCheckButton");
        
        toolboxShouldNotPerformAction();
        consoleShouldBeEmpty();
    }
    
    @Test
    public void scenario_NonEpubChosen() throws Exception {
        System.out.println("When user chooses a file which is not an epub");
        
        stubOpenFilePromptWithNonEbook();
        
        clickOn("#allCheckButton");
        
        testHasExpectedOutcome("AllCheckFeature", "not-epub");
    }
    
}
