package com.biggerconcept.epubtoolbox.results;

import java.util.ArrayList;

/**
 * Interface for a Result class
 * 
 * @author abigger
 */
public interface IResult {
    /**
     * Method for adding a result to the result.
     * 
     * This is used for merging results together as is the case for reporting
     * an action that includes multiple tasks.
     * 
     * @param res 
     */
    public void addResult(Result res);
    
    /**
     * Method for adding a message to the result.
     * 
     * Results can have multiple messages within them. This should append the 
     * message onto the end of the messages array.
     * 
     * @param newMessage 
     */
    public void addMessage(Message newMessage);
    
    /**
     * Method for returning the result title.
     * 
     * The title is used to group results in the console. This should summarize
     * the action or task.
     * 
     * @return 
     */
    public String getTitle();
    
    /**
     * Method for accessing results.
     * 
     * A result may have multiple results within it. This happens when the 
     * result has been merged with other results.
     * 
     * This should return all of the results.
     * 
     * @return 
     */
    public ArrayList<Result> getResults();
    
    /**
     * Message accessor.
     * 
     * This should return all of the message objects back to the caller. A
     * result may have multiple messages.
     * 
     * @return 
     */
    public ArrayList<Message> getMessages();
    
    /**
     * Boolean for determining whether the result should be reported as a
     * failure.
     * 
     * This method should iterate through all of the results in the result and
     * return true if any of the results are in error state.
     * 
     * As results may have multiple results of their own, your logic should
     * ensure that any one of them makes this method return true.
     * 
     * If all results are successful, then this should return false.
     * 
     * @return 
     */
    public boolean isFail();
    
    /**
     * Boolean for determining whether the result should be reported as a 
     * warning.
     * 
     * This method should iterate through all of the results in the result and
     * return true if any of them are in warning state.
     * 
     * As results may have multiple results of their own, your logic should
     * ensure that any result of warning state would make this return true.
     * 
     * If all results are not warning, then this should return false.
     * 
     * @return 
     */
    public boolean isWarn();
    
    /**
     * Boolean for determining whether the result should be reported as as pass.
     * 
     * This method should iterate through all of the results in the result and 
     * return true if all of them are not error or warning.
     * 
     * If any result is warning or error, then this should return false.
     * 
     * @return 
     */
    public boolean isPass();
    
    /**
     * Setter for the result title.
     * 
     * Accepts a string and sets the result instance variable as its value.
     * 
     * @param title 
     */
    public void setTitle(String title);
}
