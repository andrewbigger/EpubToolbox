package com.biggerconcept.epubtoolbox.features;

import support.ToolboxUITestHarness;
import org.junit.Test;

public class CollectionModeToggleFeature extends ToolboxUITestHarness {
    
    @Test
    public void scenario_CollectionModeTurnedOffByDefault() throws Exception {
        System.out.println("Collection mode off by default");
        
        toolboxCollectionModeShouldBeFalse();
    }
    
    @Test
    public void scenario_CollectionModeTurnedOn() throws Exception {
        System.out.println("When user selects collection mode");
        
        clickOn("#runOnCollectionChoice");
        
        toolboxCollectionModeShouldBeTrue();
    }
    
    @Test
    public void scenario_CollectionModeTurnedOnAndOff() throws Exception {
        System.out.println("When user deselects collection mode");
        
        clickOn("#runOnCollectionChoice");
        clickOn("#runOnCollectionChoice");
        
        toolboxCollectionModeShouldBeFalse();
    }
    
}