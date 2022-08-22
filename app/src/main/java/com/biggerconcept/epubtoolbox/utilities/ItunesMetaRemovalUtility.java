package com.biggerconcept.epubtoolbox.utilities;

import com.biggerconcept.epubtoolbox.results.Message;
import java.io.File;
import org.apache.commons.io.FilenameUtils;

/**
 * Utility for removing the iTunes metadata artifact from an EPUB file.
 * 
 * @author Andrew Bigger
 */
public class ItunesMetaRemovalUtility extends Utility {
    /**
     * Path for expanding the EPUB while the utility is working.
     */
    protected File workLocation = new File(
            workDir.getAbsolutePath()
            + File.separator
            + FilenameUtils.removeExtension(inLocation.getName())
    );
  
    /**
     * Constructor for the ItunesMetaRemovalUtility.
     * 
     * This calls the utility constructor with the input file and output
     * location.
     * 
     * @param inFile
     * @param outFile 
     */
    public ItunesMetaRemovalUtility(File inFile, File outFile) {
        super(inFile, outFile);
    }
    
    /**
     * Removes ItunesMetadata artifact from the EPUB.
     * 
     * It first expands the EPUB into the working directory.
     * 
     * Then when the EPUB is expanded an attempt is made to locate the
     * iTunes metadata artifact. If the file is found, then it will be deleted
     * from the EPUB before the EPUB is packed back up and saved to disk.
     * 
     * When the utility has completed its job, then the results are reported.
     * 
     * @throws Exception 
     */
    @Override
    public void doJob() throws Exception {
        importEpubToWorkspace();
        
        File metaFile = new File(
                workLocation + File.separator + "iTunesMetadata.plist");
        if (metaFile.exists()) metaFile.delete();
        
        exportEpubFromWorkspace();
        reportComplete();
    }
    
    /**
     * Produces a success message for when the utility has successfully been
     * executed.
     */
    protected void reportComplete() {
        getResult().addMessage(new Message(
                "info", "iTunesMetadata.plist has been removed."));
    }
    
    /**
     * Uses the unpack utility to expand the EPUB into the working directory.
     * 
     * @throws Exception 
     */
    protected void importEpubToWorkspace() throws Exception {
        new UnpackUtility(
                inLocation, workDir).doJob();
    }
    
    /**
     * Uses the pack utility to pack the EPUB and replace the input EPUB.
     * 
     * @throws Exception 
     */
    protected void exportEpubFromWorkspace() throws Exception {
        new PackUtility(
            workLocation, outLocation).doJob();
    }
}
