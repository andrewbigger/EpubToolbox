package com.biggerconcept.epubtoolbox.utilities;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class UnpackUtilityTest {
    private static final Path RESOURCE_PATH = Paths
            .get("src", "test", "resources");
    private static final Path TEST_OUTPUT_PATH = Paths
            .get("target", "test-output");
    private final File inLoc = Paths
            .get(RESOURCE_PATH.toString(), "TestUnpackBook.epub")
            .toFile();
    private final File outLoc = Paths
            .get(TEST_OUTPUT_PATH.toString(), "unpack-output")
            .toFile();
    private final File unpackLocation = Paths
            .get(TEST_OUTPUT_PATH.toString(), "unpack-output", "TestUnpackBook")
            .toFile();
    private final UnpackUtility instance = new UnpackUtility(inLoc, outLoc);
    
    private final String s = File.separator;
    
    private final File mime       = Paths
            .get(unpackLocation.toString(), "mimetype")
            .toFile();
    private final File metaInf    = Paths
            .get(unpackLocation.toString(), "META-INF")
            .toFile();
    private final File container  = Paths
            .get(metaInf.toString(), "container.xml")
            .toFile();
    private final File contentDir = Paths
            .get(unpackLocation.toString(), "GoogleDoc")
            .toFile();
    private final File navXhtml   = Paths
            .get(contentDir.toString(), "nav.xhtml")
            .toFile();
    private final File packageOpf = Paths
            .get(contentDir.toString(), "package.opf")
            .toFile();
    private final File chapter    = Paths
            .get(contentDir.toString(), "TestBook.xhtml")
            .toFile();
    
    public UnpackUtilityTest() {
    }
    
    @Before
    public void setUpClass() throws Exception {
        if (outLoc.exists()) FileUtils.deleteQuietly(outLoc);
    }
    
    @After
    public void tearDown() throws Exception {
        if (outLoc.exists()) FileUtils.deleteQuietly(outLoc);
    }

    @Test
    public void testDoJob() throws Exception {
        System.out.println("Unpack Utility unzips epub");
        
        instance.doJob();
        
        assertTrue(mime.exists());
        assertTrue(container.exists());
        assertTrue(navXhtml.exists());
        assertTrue(packageOpf.exists());
        assertTrue(chapter.exists());
    }
    
}
