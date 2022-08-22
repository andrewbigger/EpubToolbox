package com.biggerconcept.epubtoolbox.utilities;

import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;

/**
 * Interface for a utility class.
 * 
 * @author Andrew Bigger
 */
public interface IUtility {    
    /**
     * Method that performs the utility action.
     * 
     * When the utility is not able to complete its purpose, then an exception
     * should be thrown.
     * 
     * @throws Exception 
     */   
    public void doJob() throws Exception;
    
    /**
     * Returns result of the utility once its complete.
     * 
     * If there is an issue in determining the result then an exception should
     * be thrown.
     * 
     * @return
     * @throws Exception 
     */
    public Result getResult() throws Exception;
    
    /**
     * Sets the working directory for the utility.
     * 
     * @param workDir 
     */
    public void setWorkDir(File workDir);
}