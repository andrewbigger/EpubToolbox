package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Message;
import com.biggerconcept.epubtoolbox.results.Result;
import support.TestHelper;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ImageSizeCheckerTest {
    private final ImageSizeChecker instance;
    
    public ImageSizeCheckerTest() {
        instance = new ImageSizeChecker(new File("path/to/fake.epub"));
        instance.setWorkDir(TestHelper.RESOURCE_DIR);
    }
    
    @Test
    public void testCheck_when_files_LessThanSizeThreshold() throws Exception {
        System.out.println("#check doesn't report when file is < threshold");
        
        ImageSizeChecker instanceSpy = spy(instance);
        doReturn(instanceSpy.IMAGE_SIZE_THRESHOLD - 1)
                .when(instanceSpy).imageSize(Matchers.anyObject());
        
        instanceSpy.check();
        
        verify(instanceSpy, times(4)).imageSize(Matchers.anyObject());
        verify(instanceSpy, times(0))
                .reportResult(Matchers.anyObject(), Matchers.anyObject());
    }
    
    @Test
    public void testCheck_when_files_MoreThanSizeThreshold() throws Exception {
        System.out.println("#check reports error when file > threshold");
        
        ImageSizeChecker instanceSpy = spy(instance);
        doReturn(instanceSpy.IMAGE_SIZE_THRESHOLD + 1)
                .when(instanceSpy).imageSize(Matchers.anyObject());
        
        instanceSpy.check();
        
        verify(instanceSpy, times(4))
                .reportResult(Matchers.anyObject(), Matchers.anyObject());
    }
    
    @Test
    public void testGetResult() {
        System.out.println("#getResult returns outcome of test");
        
        Result outcome = new Result("Image size check outcome");
        instance.checkOutcome = outcome;
        
        assertEquals(outcome, instance.getResult());
    }
    
    @Test
    public void testReportResult() {
        System.out.println("#reportResult adds error message to outcome");
        
        String dummyFilePath = "/path/to/file";
        String dummyOutcome = "13.8";
        File dummyFile = new File(dummyFilePath);
        
        instance.reportResult(dummyFile, dummyOutcome);
        
        Message reportedResult = instance.getResult().getMessages().get(0);
        
        assertTrue(reportedResult.isError());
        assertEquals(
                "ERROR: " 
                + dummyFile.getName()
                + ": File is " 
                + dummyOutcome 
                + " "
                + "megapixels in size. It should be less than "
                + instance.IMAGE_SIZE_THRESHOLD
                + "mpx. "
                + "Please reduce the size of this image."
              , reportedResult.getMessage());
    }
    
    @Test
    public void testCheckerTargets() throws IOException {
        System.out.println("#checkerTargets only targets image files");

        ArrayList<File> targets = new ArrayList();
        
        for (File entry: instance.checkerTargets()) {
            assertTrue(isExpectedTarget(entry.getName()));
            targets.add(entry);
        }
        
        assertEquals(4, targets.size());
   }
    
    private boolean isExpectedTarget(String name){
        
        boolean result;
        
        switch (name) {
            case "fake-image.jpg": case "fake-image.jpeg":
            case "fake-image.png": case "fake-image.gif":
                result = true;
                break;
            default:
                result = false;
                break;
        }
        
        return result;
    }
    
    @Test
    public void testImageSize() throws Exception {
        System.out.println("#fileSize returns file size in megapixels");
        
        File testFile = spy(new File("path/to/file"));
        
        ImageSizeChecker checkerSpy = spy(instance);
        BufferedImage fakeImage = mock(BufferedImage.class);

        doReturn(fakeImage).when(checkerSpy).readImage(Matchers.anyObject());
        doReturn(3000).when(fakeImage).getWidth();
        doReturn(4000).when(fakeImage).getHeight();
        
        assertEquals(Double.parseDouble("12"), checkerSpy.imageSize(testFile), 0);
    }
    
}
