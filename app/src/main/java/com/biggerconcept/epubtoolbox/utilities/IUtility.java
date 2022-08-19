package com.biggerconcept.epubtoolbox.utilities;

import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;

public interface IUtility {    
    public void doJob() throws Exception;
    public Result getResult() throws Exception;
    public void setWorkDir(File workDir);
}