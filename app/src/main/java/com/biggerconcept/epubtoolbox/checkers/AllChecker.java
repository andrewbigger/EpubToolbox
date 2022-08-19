package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;

public class AllChecker extends Checker implements IChecker {
    protected final Checker[] validations;
    
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
    
    @Override
    public void check() throws Exception {
        for (Checker validation : validations) {
            validation.check();
            getResult().addResult(validation.getResult());
        }
    }

}
