package com.biggerconcept.epubtoolbox.utilities;

import com.biggerconcept.epubtoolbox.results.Message;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class PickUtility extends Utility {
    protected List<String> pickList;
    
    public PickUtility(File inFile, File outFile, String[] targets) {
        super(inFile, outFile);
        pickList = Arrays.asList(targets);
    }
    
    @Override
    public void doJob() throws Exception {
        getResult().setTitle("Results");
        
        for (Path entry: epubCollection()) {
            if (pickList.contains(entry.getFileName().toString())) { 
                copy(entry.toFile());
                
                getResult().addMessage(
                        new Message("info", 
                                "Copied " 
                                + entry.getFileName() 
                                + " to " 
                                + inLocation.getAbsolutePath()));
            }
        }
        
    }
    
    protected DirectoryStream<Path> epubCollection() throws IOException {
        return Files.newDirectoryStream(inLocation.toPath(), "*.epub");
    }
    
    protected void copy(File target) throws IOException {
        FileUtils.copyFileToDirectory(target, outLocation);
    }
    
}
