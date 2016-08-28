package com.biggerconcept.epubtoolbox.utilities;

import java.io.File;
import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.io.FilenameUtils;

public class UnpackUtility extends Utility {

    public UnpackUtility(File inFile, File outFile) {
        super(inFile, outFile);
    }

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