package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.results.Result;

/**
 * Interface for an Action
 * 
 * @author Andrew Bigger
 */
public interface IAction {
   /**
    * Method that executes the action
    * 
    * @throws Exception 
    */
    public void doAction() throws Exception;
    
    /**
     * Getter for action result.
     * 
     * @return 
     */
    public Result getResult();
}
