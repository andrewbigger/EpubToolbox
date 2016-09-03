package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.checkers.*;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActionFactoryTest {
    
    public ActionFactoryTest() {
    }
    
    @Test
    public void testCreateChecker_when_AllChecker() {
        System.out.println("#createChecker when allcheck action");
       
        Checker result = ActionFactory.createChecker(
                "All Checks", 
                new File("/path/to/some.epub"));
        
        assertEquals(AllChecker.class, result.getClass());
    }

    @Test
    public void testCreateChecker_when_EpubChecker() {
        System.out.println("#createChecker when epubcheck action");
       
        Checker result = ActionFactory.createChecker(
                "Epub Checker", 
                new File("/path/to/some.epub"));
        
        assertEquals(EpubChecker.class, result.getClass());
    }
    
    @Test
    public void testCreateChecker_when_FileSizeChecker() {
        System.out.println("#createChecker when file size checker action");
       
        Checker result = ActionFactory.createChecker(
                "File Size Checker", 
                new File("/path/to/some.epub"));
        
        assertEquals(FileSizeChecker.class, result.getClass());
    }
    
    @Test
    public void testCreateChecker_when_ImageSizeChecker() {
        System.out.println("#createChecker when image size checker action");
       
        Checker result = ActionFactory.createChecker(
                "Image Size Checker", 
                new File("/path/to/some.epub"));
        
        assertEquals(ImageSizeChecker.class, result.getClass());
    }
     
    @Test(expected=UnsupportedOperationException.class)
    public void testCreateChecker_when_UnknownChecker() {
        System.out.println("#createChecker when unknown checker action");
       
        ActionFactory.createChecker(
                "Another Checker", 
                new File("/path/to/some.epub"));
    }
}
