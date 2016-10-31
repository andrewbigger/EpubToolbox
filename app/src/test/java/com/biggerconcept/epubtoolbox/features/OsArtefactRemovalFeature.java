package com.biggerconcept.epubtoolbox.features;

import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import support.ToolboxUITestHarness;
import org.junit.Test;

public class OsArtefactRemovalFeature extends ToolboxUITestHarness {
    
    @BeforeClass
    public static void setup() throws IOException {
        setupTestOutputFolder("OSArtefactRemovalFeature");
    }
    
    @Test
    public void scenario_RmOSArtefactsFromEpub() throws Exception {
        System.out.println("When user runs rm os artefacts on file which has artefacts file");
       
        FileUtils.copyFile(
                testFixtureFile(
                        "OSArtefactRemovalFeature", 
                        "TestBookWithOSArtefacts.epub"
                ),
                testOutputFile(
                        "OSArtefactRemovalFeature",
                        "TestBookWithOSArtefacts.epub")
        );
        
        stubChooseFilePrompt(
                testOutputFile(
                    "OSArtefactRemovalFeature", 
                    "TestBookWithOSArtefacts.epub"
                )
        );
        
        stubYes();
        
        // Run epub check to ensure os artefacts were present
        // and has been removed post action:
        clickOn("#epubCheckButton");
        selectUtilityAction("OS Artefacts (i.e. .DS_Store, thumbs.db)");
        clickOn("#epubCheckButton");
        
        testHasExpectedOutcome("OSArtefactRemovalFeature", "success");
        toolboxActionShouldBeSuccessful();
    }
    
    @Test
    public void scenario_RmOSArtefactsOnNo() throws Exception {
        System.out.println("When user changes mind on rm os artefacts on an epub");
        
        stubOpenFilePromptWithValidEbook();
        stubNo();
        
        selectUtilityAction("OS Artefacts (i.e. .DS_Store, thumbs.db)");
        
        toolboxShouldNotPerformAction();
        consoleShouldBeEmpty();
    }
    
    @Test
    public void scenairo_RmOSArtefactsOnNothing() throws Exception {
        System.out.println("When user presses cancel on epub choice");
        
        stubOpenFilePromptWithNoEbook();
        
        selectUtilityAction("OS Artefacts (i.e. .DS_Store, thumbs.db)");
        
        toolboxShouldNotPerformAction();
        consoleShouldBeEmpty();
    }
    
    @Test
    public void scenario_NonEpubChosen() throws Exception {
        System.out.println("When user chooses a file which is not an epub");
        
        stubOpenFilePromptWithNonEbook();
        stubYes();
        
        selectUtilityAction("OS Artefacts (i.e. .DS_Store, thumbs.db)");
        
        testHasExpectedOutcome("OSArtefactRemovalFeature", "not-epub");
    }
    
}
