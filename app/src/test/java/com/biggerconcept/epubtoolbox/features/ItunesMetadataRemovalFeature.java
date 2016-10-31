package com.biggerconcept.epubtoolbox.features;

import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import support.ToolboxUITestHarness;
import org.junit.Test;

public class ItunesMetadataRemovalFeature extends ToolboxUITestHarness {
    
    @BeforeClass
    public static void setup() throws IOException {
        setupTestOutputFolder("ItunesMetaRemovalFeature");
    }
    
    @Test
    public void scenario_RmItunesMetaFromEpub() throws Exception {
        System.out.println("When user runs rm itunes meta on file which has meta file");
       
        FileUtils.copyFile(
                testFixtureFile(
                        "ItunesMetaRemovalFeature", 
                        "TestBookWithItunesMeta.epub"
                ),
                testOutputFile(
                        "ItunesMetaRemovalFeature",
                        "TestBookWithItunesMeta.epub")
        );
        
        stubChooseFilePrompt(
                testOutputFile(
                    "ItunesMetaRemovalFeature", 
                    "TestBookWithItunesMeta.epub"
                )
        );
        
        stubYes();
        
        // Run epub check to ensure itunes meta was present
        // and has been removed post action:
        clickOn("#epubCheckButton");
        selectUtilityAction("iTunesMetadata.plist");
        clickOn("#epubCheckButton");
        
        testHasExpectedOutcome("ItunesMetaRemovalFeature", "success");
        toolboxActionShouldBeSuccessful();
    }
    
    @Test
    public void scenario_RmItunesMetaOnNo() throws Exception {
        System.out.println("When user changes mind on rm itunes meta on an epub");
        
        stubOpenFilePromptWithValidEbook();
        stubNo();
        selectUtilityAction("iTunesMetadata.plist");
        
        toolboxShouldNotPerformAction();
        consoleShouldBeEmpty();
    }
    
    @Test
    public void scenairo_RmItunesMetaOnNothing() throws Exception {
        System.out.println("When user presses cancel on epub choice");
        
        stubOpenFilePromptWithNoEbook();
        
        selectUtilityAction("iTunesMetadata.plist");
        
        toolboxShouldNotPerformAction();
        consoleShouldBeEmpty();
    }
    
    @Test
    public void scenario_NonEpubChosen() throws Exception {
        System.out.println("When user chooses a file which is not an epub");
        
        stubOpenFilePromptWithNonEbook();
        stubYes();
        
        selectUtilityAction("iTunesMetadata.plist");
        
        testHasExpectedOutcome("ItunesMetaRemovalFeature", "not-epub");
    }
    
}
