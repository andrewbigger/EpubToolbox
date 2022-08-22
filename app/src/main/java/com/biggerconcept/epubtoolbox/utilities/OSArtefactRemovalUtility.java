package com.biggerconcept.epubtoolbox.utilities;

import com.biggerconcept.epubtoolbox.results.Message;
import java.io.File;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * Utility for removing artifacts like .DS_Store and thumbs.db from a
 * packed EPUB.
 * 
 * @author abigger
 */
public class OSArtefactRemovalUtility extends Utility {
    /**
     * List of OS artifact file extensions for the utility to target.
     */
    private final String[] TARGET_EXTENSIONS = new String[2];
    
    /**
     * Path for expanding the EPUB while the utility is working.
     */
    private final File workLocation = new File(
            workDir.getAbsolutePath()
            + File.separator
            + FilenameUtils.removeExtension(inLocation.getName())
    );
    
    /**
     * Constructor for the OSArtifactRemovalUtility.
     * 
     * This calls the utility constructor with the input and output location.
     * 
     * It then adds common OS artifact file extensions to the TARGET_EXTENSIONS
     * instance variable. These are .DS_Store from macs and and thumbs.db
     * from windows machines.
     * 
     * @param inFile
     * @param outFile 
     */
    public OSArtefactRemovalUtility(File inFile, File outFile) {
        super(inFile, outFile);
        TARGET_EXTENSIONS[0] = "DS_Store";
        TARGET_EXTENSIONS[1] = "db";
    }
    
    /**
     * Removes OS Artifacts from the EPUB.
     * 
     * It first expands the EPUB into the working directory.
     * 
     * Then when the EPUB is expanded, it will iterate through each file
     * and remove any files that are likely to be artifacts left behind by
     * operating system file explorers.
     * 
     * Once the artifacts are removed, the EPUB is packed back up and placed
     * saved to disk.
     * 
     * When the utility has completed its job, then the results are reported.
     * 
     * @throws Exception 
     */
    @Override
    public void doJob() throws Exception {
        importEpubToWorkspace();
        
        for (File entry: utilityTargets()) {
            entry.delete();
        }
        
        exportEpubFromWorkspace();
        reportComplete();
    }
    
    /**
     * Produces a success message for when the utility has successfully been
     * executed.
     */
    protected void reportComplete() {
        getResult().addMessage(new Message(
                "info", "OS Artefacts have been removed."));
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
    
    /**
     * Finds all files that have the targeted file extensions for removal.
     * 
     * @return 
     */
    protected Collection<File> utilityTargets() {
        return FileUtils.listFiles(workDir, TARGET_EXTENSIONS, true);
    }
    
}
