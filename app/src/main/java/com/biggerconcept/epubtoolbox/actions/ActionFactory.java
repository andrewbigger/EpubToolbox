package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.checkers.*;
import com.biggerconcept.epubtoolbox.utilities.*;
import java.io.File;

/**
 * Factory for building toolbox check and utility classes
 * 
 * @author abigger
 */
public class ActionFactory {
    /**
     * Builds a check action based on the given class and target file.
     * 
     * If the given checker class does not match any of the toolbox check 
     * actions then an UnsupportedOperationException will be raised.
     * 
     * @param checkerClass
     * @param File target
     * @return
     */
    public static Checker createChecker(String checkerClass, File target) {
        Checker returnChecker;
        
        switch (checkerClass) {
            case "All Checks":
                returnChecker = new AllChecker(target);
                break; 
           case "Epub Checker":
                returnChecker = new EpubChecker(target);
                break;
            case "File Size Checker":
                returnChecker = new FileSizeChecker(target);
                break;
            case "Image Size Checker":
                returnChecker = new ImageSizeChecker(target);
                break;
            default:
                returnChecker = null;
                break;
        }       
        
        if (returnChecker == null) throw new UnsupportedOperationException(
                "unknown checker");
        
        return returnChecker;
    }
    
    /**
     * Builds a utility action based on the given utility class, input and 
     * output files.
     * 
     * When the given checker class does not match any of the toolbox
     * utilities, then an UnsupportedOperationException will be thrown.
     * 
     * @param checkerClass
     * @param target
     * @param outTarget
     * @return 
     */
    public static Utility createUtility (
            String checkerClass, File target, File outTarget) {
        
        Utility returnUtil;
        
        switch(checkerClass) {
            case "Unpack Utility":
                returnUtil = new UnpackUtility(target, outTarget);
                break;
            case "Pack Utility":
                returnUtil = new PackUtility(target, outTarget);
                break;
            case "OS Artefact Removal Utility":
                returnUtil = new OSArtefactRemovalUtility(target, outTarget);
                break;
            case "Itunes Meta Removal Utility":
                returnUtil = new ItunesMetaRemovalUtility(target, outTarget);
                break;
            default:
                returnUtil = null;
        }
        
        if (returnUtil == null) throw new UnsupportedOperationException(
                "unknown utility");
        
        return returnUtil;
    }
    
}
