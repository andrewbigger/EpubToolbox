package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.results.Message;
import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;
import org.apache.commons.io.FileUtils;

/**
 * Class representing a toolbox action.
 * 
 * Implements the Action interface IAction
 * 
 * @author Andrew Bigger
 */
public class Action implements IAction {
    /**
     * List array of EPUB container extensions
     */
    protected final String[] EPUB_EXTENSIONS = new String[1];
    
    /**
     * File object of the input EPUB
     */
    protected File inputTarget;
    
    /**
     * File object for the output location
     */
    protected File outputTarget;
    
    /**
     * Task class
     */
    protected String taskClass;
    
    /**
     * Boolean representing whether the toolbox is in collection mode
     */
    protected boolean collectionMode;
    
    /**
     * Outcome result object
     */
    protected Result actionOutcome;

    /**
     * Action constructor.
     * 
     * Sets instance variables for an action on a single file
     * 
     * @param task
     * @param targetLocation
     * @param runOnCollection 
     */
    public Action(String task, File targetLocation, boolean runOnCollection) {
       taskClass = task;
       inputTarget = targetLocation;
       collectionMode = runOnCollection;
       actionOutcome = new Result("Action outcome");
       EPUB_EXTENSIONS[0] = "epub";
    }
    
    /**
     * Action constructor
     * 
     * Sets instance variables for an action on a file to be saved into another
     * location.
     * 
     * @param task
     * @param inputTargetLocation
     * @param outputTargetLocation
     * @param runOnCollection 
     */
    public Action(
            String task, File inputTargetLocation, File outputTargetLocation, 
            boolean runOnCollection) {
        
       taskClass = task;
       inputTarget = inputTargetLocation;
       outputTarget = outputTargetLocation;
       collectionMode = runOnCollection;
       EPUB_EXTENSIONS[0] = "epub";
       resetResult();
    }
    
    /**
     * Callback for performing an action.
     * 
     * If this is not overridden in an implementing class then an
     * UnsupportedAction exception will be raised.
     * 
     * @throws Exception 
     */
    @Override
    public void doAction() throws Exception {
        throw new UnsupportedOperationException(
                "Use Action subclass to perform action");  
    }
    
    /**
     * Returns the task class name
     * 
     * @return 
     */
    public String getTask() {
        return taskClass;
    }
    
    /**
     * Returns the result of the action after it has been performed.
     * 
     * @return 
     */
    @Override
    public Result getResult() {
        return actionOutcome;
    }
    
    /**
     * Resets the result to a new result.
     */
    public void resetResult() {
        actionOutcome = new Result("Action outcome");
    }
    
    /**
     * Adds a message about the action into the action result, so that it
     * may be presented to the user.
     * 
     * If the action is running in collection mode, then the string used in
     * the result will be pluralized.
     * 
     * The result will be an info message, that includes the input location,
     * and the name of the action.
     */
    protected void reportIntention() { 
        String pluralizedIntent = "ePub:";
        if (collectionMode) pluralizedIntent = "ePubs Folder:";
        
        getResult().addMessage(
                new Message(
                    "info", pluralizedIntent 
                            + " " 
                            + inputTarget.getAbsolutePath()   
                )
        );
    }
    
    /**
     * Builds an object array of target files.
     * 
     * When in single mode, this will be the input file.
     * 
     * When in collection mode, this will be a set of epubs from a folder.
     * 
     * @return 
     */
    protected Object[] targetFiles() {
        Object[] files = new Object[1];
        files[0] = inputTarget;
        
        if (collectionMode) { 
          files = FileUtils
                  .listFiles(inputTarget, EPUB_EXTENSIONS, false)
                  .toArray();
        }
        
        return files;
    }
    
}
