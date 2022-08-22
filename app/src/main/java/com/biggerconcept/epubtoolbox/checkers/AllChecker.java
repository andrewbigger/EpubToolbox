package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;

/**
 * Runs all validations on a given EPUB.
 * 
 * @author Andrew Bigger
 */
public class AllChecker extends Checker implements IChecker {
    /**
     * Array of Checker objects
     */
    protected final Checker[] validations;
    
    /**
     * Constructor for all checks.
     * 
     * Calls the constructor of the Checker superclass.
     * 
     * Then the intention of the check is added to the checkOutcome instance
     * variable.
     * 
     * Then a new instance of each check is added to the validations instance
     * variable, so that each check can be executed.
     * 
     * Finally, the working directory is set on each validation.
     * 
     * @param inEpub 
     */
    public AllChecker(File inEpub) {
        super(inEpub);
        ePub = inEpub;
        checkOutcome = new Result("Checking " + ePub.getName());
        validations = new Checker[3];
        
        validations[0] = new EpubChecker(ePub);
        validations[1] = new FileSizeChecker(ePub);
        validations[2] = new ImageSizeChecker(ePub);
        
        validations[0].setWorkDir(workDir);
        validations[1].setWorkDir(workDir);
        validations[2].setWorkDir(workDir);
    }
    
    /**
     * Runs each checker in the order they have been added to the validations
     * instance variable.
     * 
     * Once each check has concluded, the result is added to the validation so
     * that it can be reported back to the user.
     * 
     * @throws Exception 
     */
    @Override
    public void check() throws Exception {
        for (Checker validation : validations) {
            validation.check();
            getResult().addResult(validation.getResult());
        }
    }

}
