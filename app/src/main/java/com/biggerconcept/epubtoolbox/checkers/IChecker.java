package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;

public interface IChecker {    
    public void setup() throws Exception;
    
    public void check() throws Exception;
    
    public void tearDown() throws Exception;
    
    public Result getResult() throws Exception;
    
    public void setWorkDir(File workDir);
}
