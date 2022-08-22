package com.biggerconcept.epubtoolbox.results;

/**
 * Class representing a result message
 * 
 * @author abigger
 */
public class Message implements IMessage {
    /**
     * Message contents
     */
    private final String content;
    
    /**
     * Boolean indicating whether the message should be reported as an error.
     */
    private boolean error;
    
    /**
     * Boolean indicating whether the message should be reported as a warning.
     */
    private boolean warning;
    
    /**
     * Boolean indicating whether the message should be reported as info.
     */
    private boolean info;
    
    /**
     * Constructor for a message.
     * 
     * This takes a type and a message. The type can be error, warning or info.
     * 
     * When the message is error or warning, then the corresponding level
     * boolean is set.
     * 
     * The content is set to the value of the message parameter.
     * 
     * @param type
     * @param message 
     */
    public Message(String type, String message) {
        switch(type) {
            case "error":
                error = true;
                break;
            case "warning":
                warning = true;
                break;
            default:
                info = true;
        }
        content = message;
    }
    
    /**
     * Message getter.
     * 
     * Returns the contents of the message.
     * 
     * @return 
     */
    @Override
    public String getMessage() {
        return content;
    }
    
    /**
     * Error status getter.
     * 
     * Returns the error boolean value. If this is true it indicates that
     * this message should be presented as an error.
     * 
     * @return 
     */
    @Override
    public boolean isError() {
        return error;
    }
    
    /**
     * Warning status getter.
     * 
     * Returns the warning boolean value. If this is true it indicates that
     * this message should be presented as a warning.
     * 
     * @return 
     */
    @Override
    public boolean isWarning() {
        return warning;
    }
    
    /**
     * Info status getter.
     * 
     * Returns the info boolean value. If this is true it indicates that this
     * message should be presented as an info message.
     * 
     * @return 
     */
    @Override
    public boolean isInfo() {
        return info;
    }
}
