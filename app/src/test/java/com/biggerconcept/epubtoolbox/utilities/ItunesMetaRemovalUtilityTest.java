package com.biggerconcept.epubtoolbox.utilities;

import com.biggerconcept.epubtoolbox.results.Message;
import support.TestHelper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ItunesMetaRemovalUtilityTest {
    private final File testItunesMeta = Paths
            .get(TestHelper.RESOURCE_PATH.toString(), "iTunesMetadata.plist")
            .toFile();
    private static ItunesMetaRemovalUtility instance;

    
    public ItunesMetaRemovalUtilityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new ItunesMetaRemovalUtility(
            new File("path/to/in/fake.epub"),
            new File("path/to/out/fake.epub"));
        instance.workLocation = TestHelper.RESOURCE_DIR;
        instance.setWorkDir(TestHelper.RESOURCE_DIR);   
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        FileUtils.write(testItunesMeta, "");
    }
    
    @After
    public void tearDown() throws IOException {
        FileUtils.write(testItunesMeta, "");
    }
    
    @Test
    public void testDoJob_importsEpubToWorkspace() throws Exception {
        System.out.println("#doJob imports epub to workspace");
        
        ItunesMetaRemovalUtility instanceSpy = spy(instance);
        doNothing().when(instanceSpy).importEpubToWorkspace();
        doNothing().when(instanceSpy).exportEpubFromWorkspace();
        
        instanceSpy.doJob();
        
        verify(instanceSpy, times(1))
                .importEpubToWorkspace();
    }

    @Test
    public void testDoJob() throws Exception {
        System.out.println("#doJob removes itunes metadata");
        
        ItunesMetaRemovalUtility instanceSpy = spy(instance);
        doNothing().when(instanceSpy).importEpubToWorkspace();
        doNothing().when(instanceSpy).exportEpubFromWorkspace();
        
        instanceSpy.doJob();
        
        assertFalse(testItunesMeta.exists());
    }

    @Test
    public void testDoJob_exportsEpubFromWorkspace() throws Exception {
        System.out.println("#doJob exports epub after job");
        
        ItunesMetaRemovalUtility instanceSpy = spy(instance);
        doNothing().when(instanceSpy).importEpubToWorkspace();
        doNothing().when(instanceSpy).exportEpubFromWorkspace();
        
        instanceSpy.doJob();
        
        verify(instanceSpy, times(1))
                .exportEpubFromWorkspace();
    }
    
    @Test
    public void testDoJob_ReportsComplete() throws Exception {
        System.out.println("#doJob reports completion after job");
        
        ItunesMetaRemovalUtility instanceSpy = spy(instance);
        doNothing().when(instanceSpy).importEpubToWorkspace();
        doNothing().when(instanceSpy).exportEpubFromWorkspace();
        
        instanceSpy.doJob();
        
        verify(instanceSpy, times(1)).reportComplete();
    }
    
    @Test
    public void testReportComplete_addsInfoMessage() throws Exception {
        System.out.println("#reportComplete reports expected completion msg");
        
        ItunesMetaRemovalUtility instanceSpy = spy(instance);
        
        instanceSpy.reportComplete();
        
        Message result = instanceSpy
                .getResult()
                .getMessages()
                .get(0);
        
        assertTrue(instanceSpy.getResult().isPass());
        assertTrue(result.isInfo());
        assertEquals(
                "iTunesMetadata.plist has been removed.",
                result.getMessage());
    }
    
}
