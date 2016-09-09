package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Message;
import com.biggerconcept.epubtoolbox.results.Result;
import support.TestHelper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FileSizeCheckerTest {
    private final FileSizeChecker instance;
    
    public FileSizeCheckerTest() {
        instance = new FileSizeChecker(new File("path/to/fake.epub"));
        instance.setWorkDir(TestHelper.RESOURCE_DIR);
    }
    
    @Test
    public void testCheck_when_Files_LessThanFileSizeThreshold() 
            throws IOException, Exception {
        System.out.println("#check doesn't report when file is < threshold");
        
        FileSizeChecker instanceSpy = spy(instance);
        doReturn(instanceSpy.FILE_SIZE_THRESHOLD - 1)
                .when(instanceSpy).fileSize(Matchers.anyObject());
        
        instanceSpy.check();
        
        verify(instanceSpy, times(5)).fileSize(Matchers.anyObject());
        assertTrue(instanceSpy.getResult().isPass());
    }
    
    @Test
    public void testCheck_when_Files_MoreThanFileSizeThreshold() 
            throws IOException, Exception {   
        System.out.println("#check reports when file is > threshold");
        
        FileSizeChecker instanceSpy = spy(instance);
        doReturn(instanceSpy.FILE_SIZE_THRESHOLD + 1)
                .when(instanceSpy).fileSize(Matchers.anyObject());
        
        instanceSpy.check();
        
        assertTrue(instanceSpy.getResult().isFail());
    }
    
    @Test
    public void testGetResult() {
        System.out.println("#getResult returns outcome of test");
        
        Result outcome = new Result("File size check outcome");
        instance.checkOutcome = outcome;
        
        assertEquals(outcome, instance.getResult());
    }
    
    @Test
    public void testReportResult() {
        System.out.println("#reportResult adds error message to outcome");
        
        String dummyFilePath = "/path/to/file";
        String dummyOutcome = "400";
        File dummyFile = new File(dummyFilePath);
        
        instance.reportResult(dummyFile, dummyOutcome);
        
        Message reportedResult = instance.getResult().getMessages().get(0);
        
        assertTrue(reportedResult.isError());
        assertEquals(
                "ERROR: " 
                + dummyFile.getName() 
                + ": File is " 
                + dummyOutcome 
                + "kb in size. Please reduce the size to less than "
                + instance.FILE_SIZE_THRESHOLD
                + "kb.", reportedResult.getMessage());
    }
    
    @Test
    public void testCheckerTargets() throws IOException {
        System.out.println("#checkerTargets only targets html files");
        
        ArrayList<File> targets = new ArrayList();
        
        for (File entry: instance.checkerTargets()) {
            assertTrue(isExpectedTarget(entry.getName().toString()));
            targets.add(entry);
        }
        
        assertEquals(5, targets.size());
    }
    
    private boolean isExpectedTarget(String name){
        
        boolean result;
        
        switch (name) {
            case "fake-chapter.htm": case "fake-chapter.html":
            case "fake-chapter.xhtml": case "nav.xhtml": 
            case "TestBook.xhtml":
                result = true;
                break;
            default:
                result = false;
                break;
        }
        
        return result;
    }
    
    @Test
    public void testFileSize() {
        System.out.println("#fileSize returns file size in kilobytes");
        
        File testFile = spy(new File("path/to/file"));
        doReturn(Long.parseLong("1024")).when(testFile).length();
        
        assertEquals(Long.parseLong("1"), instance.fileSize(testFile));
    }
    
}
