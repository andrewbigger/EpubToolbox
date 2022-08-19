package com.biggerconcept.epubtoolbox.results;

import java.util.ArrayList;

public class Result implements IResult {
    private final ArrayList<Message> messages;
    private final ArrayList<Result> results;
    private String title;
    private boolean error;
    private boolean warn;
    
    public Result(String title) {
        this.title = title;
        messages = new ArrayList();
        results = new ArrayList();
        error = false;
        warn = false;
    }
    
    @Override
    public void addResult(Result res) {
        if (res.getTitle().isEmpty()) return;
        results.add(res);
    }
    
    @Override
    public void addMessage(Message newMessage) {
        messages.add(newMessage);
    }
    
    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public ArrayList<Result> getResults() {
        return results;
    }
    
    @Override
    public ArrayList<Message> getMessages() {
        return messages;
    }
    
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
    
    @Override
    public boolean isWarn() {
        for (Message m : messages) {
            if (m.isWarning()) { warn = true; break; }
        }
        
        return warn;
    }
    
    @Override
    public boolean isPass() {
        return !isFail();
    }
    
    @Override
    public void setTitle(String title) {
        this.title = title;
    }
}
