package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Message;
import java.io.File;
import java.util.Collection;
import org.apache.commons.io.FileUtils;

public class FileSizeChecker extends Checker implements IChecker {
    public final long FILE_SIZE_THRESHOLD = 300;
    private final String[] TARGET_EXTENSIONS = new String[3];

    public FileSizeChecker(File inEpub) {
        super(inEpub);
        TARGET_EXTENSIONS[0] = "htm";
        TARGET_EXTENSIONS[1] = "html";
        TARGET_EXTENSIONS[2] = "xhtml";
    }
    
    @Override
    public void check() throws Exception {
        getResult().setTitle("File Size Check Results");
        getResult().addMessage(new Message("info", "Validating html file size."));
        
        for (File entry: checkerTargets()) {
            long size = fileSize(entry);
            
            if (size > FILE_SIZE_THRESHOLD) {
                reportResult(entry, Long.toString(size));
            }
        }
    }
    
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
    
    protected Collection<File> checkerTargets() {
        return FileUtils.listFiles(workDir, TARGET_EXTENSIONS, true);
    }
    
    protected long fileSize(File file) {
        return file.length()/1024;
    }
}
