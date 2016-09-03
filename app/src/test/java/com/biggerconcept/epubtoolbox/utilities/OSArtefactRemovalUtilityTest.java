package com.biggerconcept.epubtoolbox.utilities;

import com.biggerconcept.epubtoolbox.results.Message;
import support.TestHelper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OSArtefactRemovalUtilityTest {
    private static OSArtefactRemovalUtility instance;
    private static final String SEP = File.separator;
    private final File testDsStore = Paths
            .get(TestHelper.RESOURCE_DIR.toString(), "test.DS_Store")
            .toFile();
    private final File testThumbsDb = Paths
            .get(TestHelper.RESOURCE_DIR.toString(), "test-thumbs.db")
            .toFile();
    
    public OSArtefactRemovalUtilityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new OSArtefactRemovalUtility(
                new File("path/to/in/fake.epub"), 
                new File("path/to/out/fake.epub"));
        instance.setWorkDir(TestHelper.RESOURCE_DIR);   
    }
    
    @Before
    public void setUp() throws IOException {
        FileUtils.deleteQuietly(Paths
                .get(instance.workDir.toString(), ".DS_Store")
                .toFile());
        FileUtils.deleteQuietly(Paths
                .get(instance.workDir.toString(), "thumbs.db")
                .toFile());
        FileUtils.write(testDsStore, "");
        FileUtils.write(testThumbsDb, "");
    }
    
    @After
    public void tearDown() throws IOException {
        FileUtils.write(testDsStore, "");
        FileUtils.write(testThumbsDb, "");
    }
    
    @Test
    public void testDoJob_importsEpubToWorkspace() throws Exception {
        System.out.println("#doJob imports epub to workspace");
        
        OSArtefactRemovalUtility instanceSpy = spy(instance);
        doNothing().when(instanceSpy).importEpubToWorkspace();
        doNothing().when(instanceSpy).exportEpubFromWorkspace();
        
        instanceSpy.doJob();
        
        verify(instanceSpy, times(1))
                .importEpubToWorkspace();
    }
    
    @Test
    public void testDoJob_RemovesOsArtefacts() throws Exception {
        System.out.println("#doJob removes os artefacts");
        
        OSArtefactRemovalUtility instanceSpy = spy(instance);
        doNothing().when(instanceSpy).importEpubToWorkspace();
        doNothing().when(instanceSpy).exportEpubFromWorkspace();
        
        instanceSpy.doJob();
        
        assertFalse(testDsStore.exists());
        assertFalse(testThumbsDb.exists());
    }
    
    @Test
    public void testDoJob_exportsEpubFromWorkspace() throws Exception {
        System.out.println("#doJob exports epub after job");
        
        OSArtefactRemovalUtility instanceSpy = spy(instance);
        doNothing().when(instanceSpy).importEpubToWorkspace();
        doNothing().when(instanceSpy).exportEpubFromWorkspace();
        
        instanceSpy.doJob();
        
        verify(instanceSpy, times(1))
                .exportEpubFromWorkspace();
    }
    
    @Test
    public void testDoJob_ReportsComplete() throws Exception {
        System.out.println("#doJob reports completion after job");
        
        OSArtefactRemovalUtility instanceSpy = spy(instance);
        doNothing().when(instanceSpy).importEpubToWorkspace();
        doNothing().when(instanceSpy).exportEpubFromWorkspace();
        
        instanceSpy.doJob();
        
        verify(instanceSpy, times(1)).reportComplete();
    }
    
    @Test
    public void testReportComplete_addsInfoMessage() throws Exception {
        System.out.println("#reportComplete reports expected completion msg");
        
        OSArtefactRemovalUtility instanceSpy = spy(instance);
        
        instanceSpy.reportComplete();
        
        Message result = instanceSpy
                .getResult()
                .getMessages()
                .get(0);
        
        assertTrue(instanceSpy.getResult().isPass());
        assertTrue(result.isInfo());
        assertEquals(
                "OS Artefacts have been removed.",
                result.getMessage());
    }

    @Test
    public void testUtilityTargets() throws Exception {
        System.out.println("#utilityTargets .ds_store and thumbs.db files");
        
        ArrayList<File> targets = new ArrayList();
        
        for (File entry : instance.utilityTargets()) {
            assertTrue(isExpectedTarget(entry.getName()));
            targets.add(entry);
        }
        
        assertEquals(2, targets.size());
    }
    
    private boolean isExpectedTarget(String name){
        
        boolean result;
        
        switch (name) {
            case "test-thumbs.db": case "test.DS_Store":
                result = true;
                break;
            default:
                result = false;
                break;
        }
        
        return result;
    }
    
}
