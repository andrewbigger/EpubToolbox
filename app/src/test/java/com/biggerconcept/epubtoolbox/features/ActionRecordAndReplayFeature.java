package com.biggerconcept.epubtoolbox.features;

import java.io.IOException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import support.ToolboxUITestHarness;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.matcher.base.NodeMatchers;

public class ActionRecordAndReplayFeature extends ToolboxUITestHarness {
    
    @BeforeClass
    public static void setup() throws IOException {
        setupTestOutputFolder("ActionRecordAndReplayFeature");
    }
    
    @Test
    public void scenario_UserStartsRecording() throws Exception {
        System.out.println("When user clicks record");
        
        buttonShouldBeDisabled("#playButton");
        buttonShouldBeDisabled("#stopButton");
        buttonShouldBeEnabled("#recordButton");
        
        clickOn("#recordButton");
        
        buttonShouldBeDisabled("#playButton");
        buttonShouldBeEnabled("#stopButton");
        buttonShouldBeDisabled("#recordButton");
        
        verifyThat("#consoleView", NodeMatchers.hasChild(">> Start Recording"));
        assertTrue(toolboxSpy.isRecording());
    }
    
    @Test
    public void scenario_UserStopsRecording() throws Exception {
        System.out.println("When user clicks stop after record");
        
        makeRecording();
        
        buttonShouldBeEnabled("#playButton");
        buttonShouldBeDisabled("#stopButton");
        buttonShouldBeEnabled("#recordButton");
        
        verifyThat("#consoleView", NodeMatchers.hasChild(">> Stop Recording"));
        assertFalse(toolboxSpy.isRecording());
    }
    
    @Test
    public void scenario_UserDoesNotRecordAnything() throws Exception {
        System.out.println("When user does not record anything");
        
        clickOn("#recordButton");
        clickOn("#stopButton");
        
        buttonShouldBeDisabled("#playButton");
        buttonShouldBeDisabled("#stopButton");
        buttonShouldBeEnabled("#recordButton");
        assertFalse(toolboxSpy.hasRecording());
    }
    
    @Test
    public void scenario_UserPlaysRecording() throws Exception {
        System.out.println("When user plays back recording");
        
        makeRecording();
        
        buttonShouldBeEnabled("#playButton");
        buttonShouldBeDisabled("#stopButton");
        buttonShouldBeEnabled("#recordButton");
        
        assertTrue(toolboxSpy.hasRecording());
        
        clickOn("#playButton");
        
        testHasExpectedOutcome("ActionRecordAndReplayFeature", "valid-epub");
    }

}
