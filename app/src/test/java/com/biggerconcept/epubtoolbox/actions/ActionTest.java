package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.results.Message;
import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ActionTest {
    private final Path targetFile = Paths.get("path", "to", "target");
    private final String targetFilePath = targetFile.toFile().getAbsolutePath();
    private final Action instance = new Action(
            "EpubChecker", 
            new File(targetFilePath), 
            false);
    
    public ActionTest() {
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testDoAction() throws Exception {
        System.out.println("#doAction throws unsupported op ex on superclass");
        instance.doAction();
    }
    
    @Test
    public void testGetResult() {
        System.out.println("#getResult returns action outcome");
        
        Result expectedResult = new Result("Action outcome");
        instance.actionOutcome = expectedResult;
        
        assertEquals(expectedResult, instance.getResult());
    }
    
    @Test
    public void testReportIntention_Singular() {
        System.out.println("#reportIntention adds epub info message to result");
        
        instance.reportIntention();
        Message intention = instance.getResult().getMessages().get(0);
        
        assertTrue(intention.isInfo());
        assertEquals("ePub: " + targetFilePath, intention.getMessage());
    }
    
    @Test
    public void testReportIntention_Collection() {
        System.out.println("#reportIntention adds epubs info message to result");
        
        instance.collectionMode = true;
        instance.reportIntention();
        Message intention = instance.getResult().getMessages().get(0);
        
        assertTrue(intention.isInfo());
        assertEquals("ePubs Folder: " + targetFilePath, intention.getMessage());
    }
    
    @Test
    public void testTargetFiles() {
        System.out.println("#target files returns files");
      
        Object[] result = instance.targetFiles();
        File resultFile = (File) result[0];
        assertTrue(result.length == 1);
        assertEquals(targetFilePath, resultFile.getAbsolutePath());
    }
    
}
