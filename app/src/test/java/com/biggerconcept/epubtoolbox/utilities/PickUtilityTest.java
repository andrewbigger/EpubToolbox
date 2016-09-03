package com.biggerconcept.epubtoolbox.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PickUtilityTest {
    private static final Path RESOURCE_PATH = Paths
            .get("src", "test", "resources");
    private static final File TEST_WORK_DIR = RESOURCE_PATH.toFile();
    private final String[] targets; 
    private final PickUtility instance; 
    
    public PickUtilityTest() {
        targets = new String [2];
        targets[0] = "fake-book-1.epub";
        targets[1] = "fake-book-3.epub";
        
        instance = new PickUtility(
            new File("path/to/in/fake.epub"),
            new File("path/to/out/fake.epub"),
            targets);
        instance.inLocation = TEST_WORK_DIR;
    }

    @Test
    public void testDoJob() throws Exception {
        System.out.println("#doJob calls copy on targetted files");
        
        PickUtility instanceSpy = spy(instance);
        doNothing().when(instanceSpy).copy(Matchers.anyObject());
        
        instanceSpy.doJob();
        
        verify(instanceSpy, times(2)).copy(Matchers.anyObject());
    }
    
    @Test
    public void testEpubCollection() throws IOException {
        System.out.println("#epubCollection returns list of epub files in dir");
        
        ArrayList<File> collection = new ArrayList();
        
        for (Path entry : instance.epubCollection()) {
            assertTrue(isExpectedInCollection(entry.getFileName().toString()));
            collection.add(entry.toFile());
        }
        
        assertEquals(4, collection.size());
    } 
    
    private boolean isExpectedInCollection(String name) {
        
        boolean result;
        
        switch(name) {
            case "fake-book-1.epub": case "fake-book-2.epub":
            case "fake-book-3.epub": case "TestUnpackBook.epub":
                result = true;
                break;
            default:
                result = false;
                break;
        }
        
        return result;
    }
    
}
