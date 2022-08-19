package com.biggerconcept.epubtoolbox.results;

import java.util.ArrayList;

public interface IResult {
    public void addResult(Result res);
    public void addMessage(Message newMessage);
    public String getTitle();
    public ArrayList<Result> getResults();
    public ArrayList<Message> getMessages();
    public boolean isFail();
    public boolean isWarn();
    public boolean isPass();
    public void setTitle(String title);
}
