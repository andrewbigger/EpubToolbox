package com.biggerconcept.epubtoolbox.utilities;

import java.io.File;
import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.io.FilenameUtils;

/**
 * Utility for unpacking EPUB files.
 * 
 * @author Andrew Bigger
 */
public class UnpackUtility extends Utility {
    /**
     * Constructor for UnpackUtility.
     * 
     * Calls Utility constructor with the input file and output File.
     */
    public UnpackUtility(File inFile, File outFile) {
        super(inFile, outFile);
    }

    /**
     * Performs the task of unzipping an EPUB file to the set location.
     * 
     * There isn't much special about the unpack, it's a simple unzipping
     * action of all files within the container.
     * 
     * @throws Exception 
     */
    @Override
    public void doJob() throws Exception {
        File unpackLocation = new File(
                outLocation.getAbsolutePath()
                + File.separator
                + FilenameUtils.removeExtension(inLocation.getName())
        );
        
        getResult().setTitle("");

        ZipFile zipFile = new ZipFile(inLocation.getAbsolutePath());
        zipFile.extractAll(unpackLocation.getAbsolutePath());
    }

}
