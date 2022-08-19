package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.results.Result;

public interface IAction {
    public void doAction() throws Exception;
    public Result getResult();
}
