package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.utilities.Utility;
import java.io.File;

/**
 * Class representing a utility action that modifies a given EPUB file
 * 
 * @author Andrew Bigger
 */
public class UtilityAction extends Action implements IAction {
    
    /**
     * Constructor for a utility action
     * 
     * This will call the constructor of Action superclass
     * 
     * @param task
     * @param inputTargetLocation
     * @param outputTargetLocation
     * @param runOnCollection 
     */
    public UtilityAction(
            String task, File inputTargetLocation, 
            File outputTargetLocation, boolean runOnCollection) {
        
        super(task, inputTargetLocation, outputTargetLocation, runOnCollection);
    }
    
    /**
     * Callback for executing the action.
     * 
     * This will call reportIntention, to ensure that the intention of the
     * action is added to the action result.
     * 
     * Then the utility class will be instantiated and executed.
     * 
     * @throws Exception 
     */
    @Override
    public void doAction() throws Exception {
        reportIntention();
        
        for (Object currentFile : targetFiles()) {
            Utility task = createUtility(
                    taskClass, 
                    (File) currentFile,
                    outputTarget
            );
            
            run(task);
        }
        
    }
    
    /**
     * Creates a utility instance for the given task and file
     * 
     * @param taskClass
     * @param currentFile
     * @return 
     */
    protected Utility createUtility(
            String taskClass, File currentFile, File outputLocation) {
        
        return ActionFactory.createUtility(taskClass, currentFile, outputLocation);
    }
   
    /**
     * Runs the utility.
     * 
     * Calls doJob on the task.
     * 
     * Once the utility has finished executing, then the result of the task
     * will be added to the action result so that it can be reported back to 
     * the user.
     * 
     * @param task
     * @throws Exception 
     */
    protected void run(Utility task) throws Exception {
        task.doJob();
        
        getResult().addResult(task.getResult());
    }

}
