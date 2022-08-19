package com.biggerconcept.epubtoolbox.utilities;

import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;
import java.util.UUID;

public class Utility implements IUtility {
    protected File workDir;
    protected File inLocation;
    protected File outLocation;
    protected Result taskOutcome;

    public Utility(File inFile, File outFile) {
       inLocation = inFile;
       outLocation = outFile;
       taskOutcome = new Result("Adjusting " + inLocation.getName());
       workDir = new File(
            System.getProperty("java.io.tmpdir")
            + File.separator
            + "com.biggerconcept.epubtoolbox.workdir"
            + File.separator
            + UUID.randomUUID()
       );
    }

    @Override
    public void doJob() throws Exception {
        throw new UnsupportedOperationException("Use Utility subclass for task");
    }

    @Override
    public Result getResult() {
        return taskOutcome;
    }
    
    @Override
    public void setWorkDir(File workDir) {
        this.workDir = workDir;
    }

}
