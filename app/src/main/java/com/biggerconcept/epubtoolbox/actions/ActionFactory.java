package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.checkers.*;
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
    
}
