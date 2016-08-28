package com.biggerconcept.epubtoolbox.utilities;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;

public class PackUtilityTest {
    private static final Path RESOURCE_PATH = Paths
            .get("src", "test", "resources");
    private static final Path TARGET_PATH = Paths
            .get("target", "test-output");
    private final File outLoc = Paths
            .get(TARGET_PATH.toString(), "TestPackBook.epub")
            .toFile();
    
    public PackUtilityTest() {
    }
    
    @Before
    public void setUpClass() throws Exception {
        FileUtils.deleteQuietly(outLoc);
        FileUtils.forceMkdir(TARGET_PATH.toFile());
    }
    
    @After
    public void tearDown() throws Exception {
        FileUtils.deleteQuietly(outLoc);
        FileUtils.forceMkdir(TARGET_PATH.toFile());
    }
    
}