package com.biggerconcept.epubtoolbox.features;

import java.io.IOException;
import org.junit.BeforeClass;
import support.ToolboxUITestHarness;
import org.junit.Test;

public class PackFeature extends ToolboxUITestHarness {
    
    @BeforeClass
    public static void setup() throws IOException {
        setupTestOutputFolder("PackFeature");
    }
    
    @Test
    public void scenario_PackExistingExpandedEpub() throws Exception {
        System.out.println("When the user clicks pack");

        
        stubChooseDirectoryPrompt(
                testFixtureFile("PackFeature", "TestUnpackedBook"),
                testOutputDirectory("PackFeature"));
                
        clickOn("#packButton");
        
        toolboxShouldPerformAction();
        
        testHasExpectedOutcome("PackFeature", "valid-epub");
        toolboxActionShouldBeSuccessful();
    }
    
}
