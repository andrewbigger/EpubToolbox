package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;

/**
 * Checker interface.
 * 
 * Specifies the members of a valid check class.
 * 
 * @author Andrew Bigger
 */
public interface IChecker {
    /**
     * Check setup callback.
     * 
     * The setup method is for any task that needs to be completed before the
     * check can run.
     * 
     * This method should throw an exception if the setup was unable to be
     * completed.
     * 
     * @throws Exception 
     */
    public void setup() throws Exception;
    
    /**
     * Check callback.
     * 
     * Method responsible for executing the check.
     * 
     * This method should throw an exception if the check was unable to be
     * completed. It should not throw an exception if the check failed.
     * 
     * @throws Exception 
     */
    public void check() throws Exception;
    
    /**
     * Check tear down callback.
     * 
     * The tear down method is executed after a check is executed whether it
     * succeeds or fails. It is responsible for cleaning up the host system
     * once the check is complete.
     * 
     * This method should throw an exception if the tear down was unable to be
     * completed.
     * 
     * @throws Exception 
     */
    public void tearDown() throws Exception;
    
    /**
     * Check result getter.
     * 
     * This method should return the result object of the check.
     * 
     * If the result is unable to be retrieved from the check class, then the
     * method should throw an exception.
     * 
     * @return
     * @throws Exception 
     */
    public Result getResult() throws Exception;
    
    /**
     * Working directory setter.
     * 
     * This method should set the working directory for the check. This is 
     * usually a temporary directory for the check to expand the EPUB into.
     * 
     * The working directory should always be unique. Consider using a random
     * number generator in producing the name of the working directory.
     * 
     * @param workDir 
     */
    public void setWorkDir(File workDir);
}
