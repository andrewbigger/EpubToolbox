package com.biggerconcept.epubtoolbox.features;

import java.io.IOException;
import org.junit.BeforeClass;
import support.ToolboxUITestHarness;
import org.junit.Test;

public class PickFeature extends ToolboxUITestHarness {
    
    @BeforeClass
    public static void setup() throws IOException {
        setupTestOutputFolder("PickFeature");
    }
    
    @Test
    public void scenario_PickSelectedEpub() throws Exception {
        System.out.println("When the user clicks pick and supplies list");
        
        String targets = "target-book-1.epub,target-book-2.epub";
        
        stubChooseDirectoryPrompt(
                testFixtureFile("PickFeature", "TestCollection"),
                testOutputDirectory("PickFeature"));
        stubListPrompt(targets);        
        
        clickOn("#pickButton");
        
        testHasExpectedOutcome("PickFeature", "on-collection");
    }
    
}
