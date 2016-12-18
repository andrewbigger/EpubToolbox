package com.biggerconcept.epubtoolbox.utilities;

import com.biggerconcept.epubtoolbox.results.Message;
import java.io.File;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class OSArtefactRemovalUtility extends Utility {
    private final String[] TARGET_EXTENSIONS = new String[2];
    private final File workLocation = new File(
            workDir.getAbsolutePath()
            + File.separator
            + FilenameUtils.removeExtension(inLocation.getName())
    );
    
    public OSArtefactRemovalUtility(File inFile, File outFile) {
        super(inFile, outFile);
        TARGET_EXTENSIONS[0] = "DS_Store";
        TARGET_EXTENSIONS[1] = "db";
    }
    
    @Override
    public void doJob() throws Exception {
        importEpubToWorkspace();
        
        for (File entry: utilityTargets()) {
            entry.delete();
        }
        
        exportEpubFromWorkspace();
        reportComplete();
    }
    
    protected void reportComplete() {
        getResult().addMessage(new Message(
                "info", "OS Artefacts have been removed."));
    }
    
    protected void importEpubToWorkspace() throws Exception {
        new UnpackUtility(
                inLocation, workDir).doJob();
    }
    
    protected void exportEpubFromWorkspace() throws Exception {
        new PackUtility(
            workLocation, outLocation).doJob();
    }
    
    protected Collection<File> utilityTargets() {
        return FileUtils.listFiles(workDir, TARGET_EXTENSIONS, true);
    }
    
}
