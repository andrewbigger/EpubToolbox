package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.results.Message;
import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class Action implements _IAction {
    protected final String[] EPUB_EXTENSIONS = new String[1];
    protected File inputTarget;
    protected File outputTarget;
    protected String taskClass;
    protected boolean collectionMode;
    protected Result actionOutcome;

    public Action(String task, File targetLocation, boolean runOnCollection) {
       taskClass = task;
       inputTarget = targetLocation;
       collectionMode = runOnCollection;
       actionOutcome = new Result("Action outcome");
       EPUB_EXTENSIONS[0] = "epub";
    }
    
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
    
    @Override
    public void doAction() throws Exception {
        throw new UnsupportedOperationException(
                "Use Action subclass to perform action");  
    }
    
    public String getTask() {
        return taskClass;
    }
    
    @Override
    public Result getResult() {
        return actionOutcome;
    }
    
    public void resetResult() {
        actionOutcome = new Result("Action outcome");
    }
    
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
