package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Result;
import com.biggerconcept.epubtoolbox.utilities.UnpackUtility;
import java.io.File;
import java.util.UUID;
import org.apache.commons.io.FileUtils;

public class Checker implements IChecker {
    protected File workDir;
    protected static File ePub;
    protected Result checkOutcome;

    public Checker(File inEpub) {
        ePub = inEpub;
        checkOutcome = new Result("Checking " + ePub.getName());
        workDir = new File(
            System.getProperty("java.io.tmpdir")
            + File.separator
            + "com.biggerconcept.epubtoolbox.workdir"
            + File.separator
            + UUID.randomUUID()
        );
    }

    @Override
    public void setup() throws Exception {
        if (workDir.exists()) tearDown();
        UnpackUtility util = new UnpackUtility(ePub, workDir);
        util.doJob();
    }

    @Override
    public void check() throws Exception {
        throw new UnsupportedOperationException("Use Checker subclass for check");
    }

    @Override
    public void tearDown() throws Exception {
        FileUtils.deleteDirectory(workDir);
    }

    @Override
    public Result getResult() {
        return checkOutcome;
    }
    
    @Override
    public void setWorkDir(File workDir) {
        this.workDir = workDir;
    }

}
