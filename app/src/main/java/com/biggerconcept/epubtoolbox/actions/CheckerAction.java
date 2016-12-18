package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.checkers.Checker;
import java.io.File;

public class CheckerAction extends Action implements _IAction {
    
    public CheckerAction(
            String task, File targetLocation, boolean runOnCollection) {
        
        super(task, targetLocation, runOnCollection);
    }
    
    @Override
    public void doAction() throws Exception {
        reportIntention();
        
        for (Object currentFile : targetFiles()) {
            Checker task = createChecker(taskClass, (File) currentFile);
            run(task);
        }

    }
    
    protected Checker createChecker(String taskClass, File currentFile) {
        return ActionFactory.createChecker(taskClass, currentFile);
    }
   
    protected void run(Checker task) throws Exception {
        task.setup();
        task.check();
        task.tearDown();
        
        getResult().addResult(task.getResult());
    }
     
}
