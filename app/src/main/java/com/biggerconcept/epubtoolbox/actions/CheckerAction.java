package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.checkers.Checker;
import java.io.File;

/**
 * Class representing an action that runs a check on the given EPUB file
 * 
 * @author abigger
 */
public class CheckerAction extends Action implements IAction {
    /**
     * Constructor that builds a new Checker Action
     * 
     * Calls constructor in Action superclass.
     * 
     * @param task
     * @param targetLocation
     * @param runOnCollection 
     */
    public CheckerAction(
            String task, File targetLocation, boolean runOnCollection) {
        
        super(task, targetLocation, runOnCollection);
    }
    
    /**
     * Callback to execute the check
     * 
     * This will add the intention of the action to the action result.
     * 
     * Then for all EPUB files, the check will be executed.
     * 
     * @throws Exception 
     */
    @Override
    public void doAction() throws Exception {
        reportIntention();
        
        for (Object currentFile : targetFiles()) {
            Checker task = createChecker(taskClass, (File) currentFile);
            run(task);
        }

    }
    
    /**
     * Creates a check instance for the given task and file
     * 
     * @param taskClass
     * @param currentFile
     * @return 
     */
    protected Checker createChecker(String taskClass, File currentFile) {
        return ActionFactory.createChecker(taskClass, currentFile);
    }
   
    /**
     * Runs the check
     * 
     * Calls the setup, check and tearDown hooks that should be implemented
     * in each check.
     * 
     * Once the check has executed, then the task result is added to the action
     * result so it can be reported back to the user.
     * 
     * @param task
     * @throws Exception 
     */
    protected void run(Checker task) throws Exception {
        task.setup();
        task.check();
        task.tearDown();
        
        getResult().addResult(task.getResult());
    }
     
}
