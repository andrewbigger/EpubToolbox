package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Message;
import java.io.File;
import java.util.Collection;
import org.apache.commons.io.FileUtils;

/**
 * Action for checking the file size of all HTML files
 * 
 * @author Andrew Bigger
 */
public class FileSizeChecker extends Checker implements IChecker {
    /**
     * Maximum HTML file size in megabytes
     */
    public final long FILE_SIZE_THRESHOLD = 300;
    
    /**
     * String array of file extensions of files to check
     */
    private final String[] TARGET_EXTENSIONS = new String[3];

    /**
     * Constructor for creating a new FileSizeChecker.
     * 
     * Calls Checker constructor and then adds each variant of a HTML 
     * file extension to the TARGET_EXTENSIONS instance variable.
     * 
     * @param inEpub 
     */
    public FileSizeChecker(File inEpub) {
        super(inEpub);
        TARGET_EXTENSIONS[0] = "htm";
        TARGET_EXTENSIONS[1] = "html";
        TARGET_EXTENSIONS[2] = "xhtml";
    }
    
    /**
     * Checks for HTML files above threshold.
     * 
     * Sets result title to indicate check intent. Then iterates through all
     * found HTML files and ensures that they are under the threshold.
     * 
     * When files are above threshold, they are added to the result.
     * 
     * @throws Exception 
     */
    @Override
    public void check() throws Exception {
        getResult().setTitle("File Size Check Results");
        getResult().addMessage(
                new Message("info", "Validating html file size.")
        );
        
        for (File entry: checkerTargets()) {
            long size = fileSize(entry);
            
            if (size > FILE_SIZE_THRESHOLD) {
                reportResult(entry, Long.toString(size));
            }
        }
    }
    
    /**
     * Produces an error message for files above threshold and adds to check
     * result.
     * 
     * @param testedFile
     * @param testOutcome 
     */
    protected void reportResult(File testedFile, String testOutcome) {
        String message = (
                "ERROR: " 
                + testedFile.getName() 
                + ": File is " 
                + testOutcome 
                + "kb in size. Please reduce the size to less than " 
                + FILE_SIZE_THRESHOLD + "kb." );
        
        getResult().addMessage(new Message("error", message));
    }
    
    /**
     * Produces a collection of File objects of files for the size check to 
     * validate.
     * 
     * This collection is all of the HTML files within the expanded EPUB.
     * 
     * @return 
     */
    protected Collection<File> checkerTargets() {
        return FileUtils.listFiles(workDir, TARGET_EXTENSIONS, true);
    }
    
    /**
     * Evaluates the size of a file in Megabytes.
     * 
     * @param file
     * @return 
     */
    protected long fileSize(File file) {
        return file.length()/1024;
    }
}
