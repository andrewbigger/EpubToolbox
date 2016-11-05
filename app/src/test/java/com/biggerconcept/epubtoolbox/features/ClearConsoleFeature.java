package com.biggerconcept.epubtoolbox.features;

import com.biggerconcept.epubtoolbox.results.Message;
import support.ToolboxUITestHarness;
import org.junit.Test;

public class ClearConsoleFeature extends ToolboxUITestHarness {
    
    @Test
    public void scenario_ClearsConsole() throws Exception {
        System.out.println("When user clicks clear console");
        
        ctrl.console.out(new Message("info", "Some Message"));
        clickOn("#clearConsoleButton");
        
        consoleShouldBeEmpty();
    }
    
}