package com.biggerconcept.epubtoolbox.utilities;

import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;
import java.util.UUID;

/**
 * Utility superclass that implements the IUtility interface.
 * 
 * @author abigger
 */
public class Utility implements IUtility {
    /**
     * Working directory while utility is running
     */
    protected File workDir;
    
    /**
     * Input file location
     */
    protected File inLocation;
    
    /**
     * Output file location
     */
    protected File outLocation;
    
    /**
     * Result representing the outcome of running the utility.
     */
    protected Result taskOutcome;

    /**
     * Utility constructor.
     * 
     * Sets the input and output file locations to given files.
     * 
     * Then instantiates a new result for reporting the outcome of execution
     * of the utility.
     * 
     * Finally, the working directory is crated in the temp folder. The temp
     * folder will be cleared on reboot. Each instance of the task should have
     * a unique working directory to ensure that other actions do not interfere
     * with the current action.
     * 
     * @param inFile
     * @param outFile 
     */
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

    /**
     * Job execution callback.
     * 
     * This is the method that is called when the utility is executed. This
     * should be overloaded in any implementing classes. If it is not
     * overloaded, then an UnsupportedOperationException will be thrown.
     * 
     * @throws Exception 
     */
    @Override
    public void doJob() throws Exception {
        throw new UnsupportedOperationException("Use Utility subclass for task");
    }

    /**
     * Returns utility execution result to the caller.
     * 
     * @return 
     */
    @Override
    public Result getResult() {
        return taskOutcome;
    }
    
    /**
     * Sets the working directory for the utility.
     * 
     * @param workDir 
     */
    @Override
    public void setWorkDir(File workDir) {
        this.workDir = workDir;
    }

}
