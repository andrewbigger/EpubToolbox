package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.checkers.*;
import com.biggerconcept.epubtoolbox.utilities.*;
import java.io.File;

public class ActionFactory {    
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
