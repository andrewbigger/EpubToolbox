package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.results.Result;
import com.biggerconcept.epubtoolbox.utilities.UnpackUtility;
import com.biggerconcept.epubtoolbox.utilities.Utility;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Matchers;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UtilityActionTest {
    private final File targetInputFile = new File("path/to/fake.epub");
    private final File targetOutputFile = new File("path/to/fake/output");
    private final UnpackUtility utility = mock(UnpackUtility.class);
    private final Result actionOutcome = mock(Result.class);
    private final Result taskOutcome = new Result("Utility action outcome");
    private final UtilityAction instance = new UtilityAction(
            "UnpackUtility", targetInputFile, targetOutputFile, false);
        
    public UtilityActionTest() {
    }
    
    @Before
    public void setup() {
        doReturn(taskOutcome).when(utility).getResult();
    }
    
    @Test
    public void testDoAction_on_SingleFile() throws Exception {
        UtilityAction instanceSpy = spy(instance);
        
        doReturn(utility).when(instanceSpy).createUtility(
                Matchers.anyString(), Matchers.anyObject(), Matchers.anyObject());
        doReturn(actionOutcome).when(instanceSpy).getResult();
        
        instanceSpy.doAction();
        
        verify(instanceSpy, times(1)).run(utility);
    }
    
    @Test
    public void testCreateUtility_builds_utility() throws Exception {
        System.out.println("#createUtility gets utility from action factory");
        
        Utility result = instance.createUtility(
                "Unpack Utility", targetInputFile, targetOutputFile);
        
        assertTrue(result.getClass() == UnpackUtility.class);
    }
    
    @Test
    public void testRun_calls_DoJob() throws Exception {
        System.out.println("#run does job");
        
        instance.run(utility);
        verify(utility, times(1)).doJob();
    }
    
}
