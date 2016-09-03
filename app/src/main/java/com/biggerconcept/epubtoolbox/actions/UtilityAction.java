package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.utilities.Utility;
import java.io.File;

public class UtilityAction extends Action implements _IAction {
    
    public UtilityAction(
            String task, File inputTargetLocation, 
            File outputTargetLocation, boolean runOnCollection) {
        
        super(task, inputTargetLocation, outputTargetLocation, runOnCollection);
    }
    
    @Override
    public void doAction() throws Exception {
        reportIntention();
        
        for (Object currentFile : targetFiles()) {
            Utility task = createUtility(taskClass, (File) currentFile, outputTarget);
            run(task);
        }
        
    }
    
    protected Utility createUtility(
            String taskClass, File currentFile, File outputLocation) {
        
        return ActionFactory.createUtility(taskClass, currentFile, outputLocation);
    }
   
    protected void run(Utility task) throws Exception {
        task.doJob();
        
        getResult().addResult(task.getResult());
    }

}
