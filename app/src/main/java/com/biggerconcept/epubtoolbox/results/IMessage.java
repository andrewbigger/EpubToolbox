package com.biggerconcept.epubtoolbox.results;

public interface IMessage {
    public String getMessage();
    public boolean isError();
    public boolean isWarning();
    public boolean isInfo();
}
