package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Result;
import com.biggerconcept.epubtoolbox.utilities.UnpackUtility;
import java.io.File;
import java.util.UUID;
import org.apache.commons.io.FileUtils;

/**
 * Checker superclass that implements the IChecker interface.
 * 
 * This is the base class for all checks.
 * 
 * @author Andrew Bigger
 */
public class Checker implements IChecker {
    /**
     * Working directory for running the check.
     */
    protected File workDir;
    
    /**
     * Target EPUB file to be checked.
     */
    protected static File ePub;
    
    /**
     * Check outcome object
     */
    protected Result checkOutcome;

    /**
     * Constructor for creating a new checker.
     * 
     * This will set the ePub instance variable, to the given EPUB
     * 
     * Then the outcome result is initialized with a new result that includes
     * the intention of the check.
     * 
     * Then the working directory is setup in the java temp directory. The
     * working directory is platform safe as it uses the file separator for
     * each supported platform (i.e. backslash for windows, forward slash for
     * mac). The working directory for each EPUB is randomized.
     * 
     * Files saved in the working dir will be cleared from the host system 
     * after the system is restarted.
     * 
     * @param inEpub 
     */
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

    /**
     * Check setup hook.
     * 
     * The working directory is torn down if it exists.
     * 
     * The input EPUB file is unpacked using the UnpackUtility into the working 
     * directory as all checks run on the expanded EPUB.
     * 
     * @throws Exception 
     */
    @Override
    public void setup() throws Exception {
        if (workDir.exists()) tearDown();
        UnpackUtility util = new UnpackUtility(ePub, workDir);
        util.doJob();
    }

    /**
     * Check hook.
     * 
     * This is called when the check is run from the activity.
     * 
     * This should be over-ridden by the implementing class. If this is not
     * over-ridden, then an UnsupportedOperationException will be raised.
     * 
     * @throws Exception 
     */
    @Override
    public void check() throws Exception {
        throw new UnsupportedOperationException(
                "Use Checker subclass for check"
        );
    }

    /**
     * Check tear down hook.
     * 
     * Deletes all files from the working directory.
     * 
     * @throws Exception 
     */
    @Override
    public void tearDown() throws Exception {
        FileUtils.deleteDirectory(workDir);
    }

    /**
     * Returns the result of the check.
     * 
     * @return 
     */
    @Override
    public Result getResult() {
        return checkOutcome;
    }
    
    /**
     * Sets the working directory to the given File.
     * 
     * @param workDir 
     */
    @Override
    public void setWorkDir(File workDir) {
        this.workDir = workDir;
    }

}
