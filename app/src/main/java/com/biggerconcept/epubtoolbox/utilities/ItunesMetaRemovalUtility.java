package com.biggerconcept.epubtoolbox.utilities;

import com.biggerconcept.epubtoolbox.results.Message;
import java.io.File;
import org.apache.commons.io.FilenameUtils;

public class ItunesMetaRemovalUtility extends Utility {
    protected File workLocation = new File(
            workDir.getAbsolutePath()
            + File.separator
            + FilenameUtils.removeExtension(inLocation.getName())
    );
  
    public ItunesMetaRemovalUtility(File inFile, File outFile) {
        super(inFile, outFile);
    }
    
    @Override
    public void doJob() throws Exception {
        importEpubToWorkspace();
        
        File metaFile = new File(
                workLocation + File.separator + "iTunesMetadata.plist");
        if (metaFile.exists()) metaFile.delete();
        
        exportEpubFromWorkspace();
        reportComplete();
    }
    
    protected void reportComplete() {
        getResult().addMessage(new Message(
                "info", "iTunesMetadata.plist has been removed."));
    }
    
    protected void importEpubToWorkspace() throws Exception {
        new UnpackUtility(
                inLocation, workDir).doJob();
    }
    
    protected void exportEpubFromWorkspace() throws Exception {
        new PackUtility(
            workLocation, outLocation).doJob();
    }
}
