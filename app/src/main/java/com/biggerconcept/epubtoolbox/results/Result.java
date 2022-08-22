package com.biggerconcept.epubtoolbox.results;

import java.util.ArrayList;

/**
 * Class representing a check or action result.
 * 
 * @author abigger
 */
public class Result implements IResult {
    /**
     * Array list of result messages.
     */
    private final ArrayList<Message> messages;
    
    /**
     * Array list of results 
     */
    private final ArrayList<Result> results;
    
    /**
     * Result description.
     */
    private String title;
    
    /**
     * Error flag, true if result should be presented as an error.
     */
    private boolean error;
    
    /**
     * Warning flag, true if result should be presented as a warning.
     */
    private boolean warn;
    
    /**
     * Result constructor.
     * 
     * Sets the title of the result to the given string.
     * 
     * Then initializes the messages and results ArrayLists with new 
     * ArrayList instances.
     * 
     * Finally it sets the error and warning flags to false by default. This
     * will be changed if the status is changed by the caller.
     * 
     * @param title 
     */
    public Result(String title) {
        this.title = title;
        messages = new ArrayList();
        results = new ArrayList();
        error = false;
        warn = false;
    }
    
    /**
     * Add a result to the result.
     * 
     * This essentially merges results together while keeping them independent
     * for reporting in the console.
     * 
     * If the title of the result is empty then the results instance variable
     * will remain as it is.
     * 
     * Otherwise the given result will be added to the results instance 
     * variable, in effect merging the result.
     * 
     * @param res 
     */
    @Override
    public void addResult(Result res) {
        if (res.getTitle().isEmpty()) return;
        results.add(res);
    }
    
    /**
     * Adds message to result messages.
     * 
     * This will append the given message to the result messages ArrayList.
     * 
     * @param newMessage 
     */
    @Override
    public void addMessage(Message newMessage) {
        messages.add(newMessage);
    }
    
    /**
     * Result title getter.
     * 
     * Returns the title of the result for display.
     * 
     * @return 
     */
    @Override
    public String getTitle() {
        return title;
    }
    
    /**
     * Result array getter.
     * 
     * Returns the results instance variable ArrayList.
     * 
     * @return 
     */
    @Override
    public ArrayList<Result> getResults() {
        return results;
    }
    
    /**
     * Messages getter.
     * 
     * Returns all result messages for display.
     * 
     * @return 
     */
    @Override
    public ArrayList<Message> getMessages() {
        return messages;
    }
    
    /**
     * Boolean flag to indicate whether the result should be reported as an
     * error.
     * 
     * If this is true, it means that some of the results are errors and 
     * therefore should be reported as a failure.
     * 
     * @return 
     */
    @Override
    public boolean isFail() {        
        for (Message m : messages) {
            if (m.isError()) { error = true; break; }
        }
        
        for (Result r : results) { 
            if (r.isFail()) { error = true; break; }
        }
        
        return error;
    }
    
    /**
     * Boolean flag to indicate whether the result should be reported as a
     * warning.
     * 
     * If this is true, it means that some of the results are warnings and
     * therefore should be reported as such. Warnings are not necessarily
     * failures.
     * 
     * @return 
     */
    @Override
    public boolean isWarn() {
        for (Message m : messages) {
            if (m.isWarning()) { warn = true; break; }
        }
        
        return warn;
    }
    
    /**
     * Boolean flag to determine whether the check passes.
     * 
     * This means that there are no errors in the results.
     * 
     * @return 
     */
    @Override
    public boolean isPass() {
        return !isFail();
    }
    
    /**
     * Result title setter.
     * 
     * Sets the title instance variable to the given string.
     * 
     * @param title 
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }
}
