package com.biggerconcept.epubtoolbox.features;

import java.io.IOException;
import org.junit.BeforeClass;
import support.ToolboxUITestHarness;
import org.junit.Test;

public class EpubCheckFeature extends ToolboxUITestHarness {
    
    @BeforeClass
    public static void setup() throws IOException {
        setupTestOutputFolder("EpubCheckFeature");
    }
    
    @Test
    public void scenario_EpubCheckOnValidEpub() throws Exception {
        System.out.println("When user runs epub check on a valid epub");
        
        stubOpenFilePromptWithValidEbook();
        
        clickOn("#epubCheckButton");
        
        toolboxShouldPerformAction();
        testHasExpectedOutcome("EpubCheckFeature", "valid-epub");
        toolboxActionShouldBeSuccessful();
    }
    
    @Test
    public void scenario_EpubCheckOnInvalidEpub() throws Exception {
        System.out.println("When user runs epub check on an invalid epub");
        
        stubOpenFilePromptWithInValidEbook();
        
        clickOn("#epubCheckButton");
        
        toolboxShouldPerformAction();
        testHasExpectedOutcome("EpubCheckFeature", "invalid-epub");
        toolboxActionShouldNotBeSuccessful();
    }
    
    @Test
    public void scenario_NoEpubChosen() throws Exception {
        System.out.println("When user cancels epub choice prompt");
        
        stubOpenFilePromptWithNoEbook();
        
        clickOn("#epubCheckButton");
        
        toolboxShouldNotPerformAction();
        consoleShouldBeEmpty();
    }
    
    @Test
    public void scenario_NonEpubChosen() throws Exception {
        System.out.println("When user chooses a file which is not an epub");
        
        stubOpenFilePromptWithNonEbook();
        
        clickOn("#epubCheckButton");
        
        testHasExpectedOutcome("EpubCheckFeature", "not-epub");
    }

}
