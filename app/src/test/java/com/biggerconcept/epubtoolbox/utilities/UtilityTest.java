package com.biggerconcept.epubtoolbox.utilities;

import java.io.File;
import org.junit.Test;

public class UtilityTest {
    private final Utility instance = new Utility(
            new File("path/to/target.epub"), 
            new File("path/to/output"));
    
    public UtilityTest() {
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testDoJob() throws Exception {
        System.out.println("#doJob throws unsupported op ex on superclass");
        instance.doJob();
    }
    
}
