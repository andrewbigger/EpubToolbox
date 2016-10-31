package com.biggerconcept.epubtoolbox.features;

import java.io.IOException;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import support.ToolboxUITestHarness;
import org.junit.Test;

public class UnpackFeature extends ToolboxUITestHarness {
    
    @BeforeClass
    public static void setup() throws IOException {
        setupTestOutputFolder("UnpackFeature");
    }
    
    @Test
    public void scenario_UnpackExistingEpub() throws Exception {
        System.out.println("When the user clicks unpack");
        
        stubOpenFilePromptWithValidEbook();
        stubChooseDirectoryPrompt(testOutputFile("UnpackFeature", "unpack-epub"));
        clickOn("#unpackButton");
        
        toolboxShouldPerformAction();
        
        testHasExpectedOutcome("UnpackFeature", "valid-epub");
        toolboxActionShouldBeSuccessful();
        
        assertTrue(testOutputFile("UnpackFeature", "unpack-epub").exists());
    }
    
}
