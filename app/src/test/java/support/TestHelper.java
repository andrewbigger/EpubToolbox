package support;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

public class TestHelper {
    public static final Path RESOURCE_PATH = Paths
            .get("src", "test", "resources");
    public static final File RESOURCE_DIR = RESOURCE_PATH.toFile();
    
    public static final Path TEST_TARGET_PATH = Paths
            .get("target", "test-output");
    public static final File TEST_TARGET_DIR = TEST_TARGET_PATH.toFile();
    
    
    public static final Path FIXTURE_PATH = Paths
            .get("src", "test", "fixtures");
    public static final File FIXTURE_DIR = FIXTURE_PATH.toFile();
    
    public static void clearTarget() throws IOException {
        FileUtils.deleteDirectory(TEST_TARGET_DIR);
    }
    
}
