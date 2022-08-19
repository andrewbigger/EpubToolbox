package com.biggerconcept.epubtoolbox.results;

public class Message implements IMessage {
    private final String content;
    private boolean error;
    private boolean warning;
    private boolean info;
    
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
    
    @Override
    public String getMessage() {
        return content;
    }
    
    @Override
    public boolean isError() {
        return error;
    }
    
    @Override
    public boolean isWarning() {
        return warning;
    }
    
    @Override
    public boolean isInfo() {
        return info;
    }
}
