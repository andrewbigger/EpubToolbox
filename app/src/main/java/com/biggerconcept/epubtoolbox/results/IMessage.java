package com.biggerconcept.epubtoolbox.results;

/**
 * Interface for a toolbox console message.
 * 
 * @author abigger
 */
public interface IMessage {
    /**
     * Get Message is a method that returns the message as a string.
     * 
     * @return 
     */
    public String getMessage();
    
    /**
     * Boolean to indicate whether the message is an error.
     * 
     * Returns true if the message is of log level error, otherwise it will 
     * return false.
     *
     * @return 
     */
    public boolean isError();
    
    /**
     * Boolean to indicate whether the message is a warning.
     *
     * Returns true if the message is of log level warning, otherwise it will
     * return false.
     * 
     * @return 
     */
    public boolean isWarning();
    
    /**
     * Boolean to indicate whether the message is an info.
     * 
     * Returns true if the message is of log level info, otherwise it will
     * return false.
     * 
     * @return 
     */
    public boolean isInfo();
}
