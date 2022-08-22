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

/**
 * Utility for copying a list of EPUB files from a folder into another folder.
 * 
 * @author Andrew Bigger
 */
public class PickUtility extends Utility {
    /**
     * List of target filenames
     */
    protected List<String> pickList;
    
    /**
     * Constructor for pick utility.
     * 
     * Calls the Utility constructor with the input and output file arguments.
     * 
     * Then the targets are converted into an array and stored in utility
     * pickList instance variable.
     * 
     * @param inFile
     * @param outFile
     * @param targets 
     */
    public PickUtility(File inFile, File outFile, String[] targets) {
        super(inFile, outFile);
        pickList = Arrays.asList(targets);
    }
    
    /**
     * Copies chosen EPUB files from source location to destination location.
     * 
     * This works by iterating over each file in the source location. If the
     * name of the EPUB file is on the pick list, then it is copied to the
     * output location.
     * 
     * A message indicating the actions performed will be saved to the utility
     * result.
     * 
     * @throws Exception 
     */
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
    
    /**
     * Returns a list of EPUBS that exist in the source.
     * 
     * @return
     * @throws IOException 
     */
    protected DirectoryStream<Path> epubCollection() throws IOException {
        return Files.newDirectoryStream(inLocation.toPath(), "*.epub");
    }
    
    /**
     * Copies EPUB file from source to output location.
     * 
     * @param target
     * @throws IOException 
     */
    protected void copy(File target) throws IOException {
        FileUtils.copyFileToDirectory(target, outLocation);
    }
    
}
