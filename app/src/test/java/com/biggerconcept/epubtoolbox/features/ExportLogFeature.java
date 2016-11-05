package com.biggerconcept.epubtoolbox.features;

import com.biggerconcept.epubtoolbox.results.Message;
import java.io.IOException;
import org.junit.BeforeClass;
import support.ToolboxUITestHarness;
import org.junit.Test;

public class ExportLogFeature extends ToolboxUITestHarness {
    
    @BeforeClass
    public static void setup() throws IOException {
        setupTestOutputFolder("ExportLogFeature");
    }
    
    @Test
    public void scenario_ExportsContentsOfLogToTextFile() throws Exception {
        System.out.println("When user selects export log");
                
        stubSaveFileOutputLocation(
                testOutputFile("ExportLogFeature", "export-log-file"));
        
        ctrl.console.out(new Message("info", "Something for the log"));
        clickOn("#exportLogButton");
        
        fixtureShouldMatchExpected("ExportLogFeature", "export-log-file");
        testHasExpectedOutcome("ExportLogFeature", "valid-epub");
    }
    
}