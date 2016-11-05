package com.biggerconcept.epubtoolbox.features;

import java.io.IOException;
import org.junit.BeforeClass;
import support.ToolboxUITestHarness;
import org.junit.Test;

public class RepeatFeature extends ToolboxUITestHarness {
    
    @BeforeClass
    public static void setup() throws IOException {
        setupTestOutputFolder("RepeatFeature");
    }
    
    @Test
    public void scenario_RepeatsPreviousTask() throws Exception {
        System.out.println("When user clicks repeat after performing a task");
        
        stubOpenFilePromptWithValidEbook();
        clickOn("#sizeCheckButton");
        clickOn("#repeatButton");
        
        testHasExpectedOutcome("RepeatFeature", "success");
    }
    
    @Test
    public void scenario_AttemptToRepeatWhenNoTaskHasBeenRun() throws Exception {
        System.out.println("When user clicks clear console");
        
        clickOn("#repeatButton");
        
        consoleShouldBeEmpty();
    }
    
}