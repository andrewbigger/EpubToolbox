package com.biggerconcept.epubtoolbox.actions;

import com.biggerconcept.epubtoolbox.checkers.Checker;
import com.biggerconcept.epubtoolbox.checkers.EpubChecker;
import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;
import java.lang.reflect.Field;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Matchers;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CheckerActionTest {
    private final File targetFile = new File("path/to/fake.epub");
    private final EpubChecker checker = mock(EpubChecker.class);
    private final Result actionOutcome = mock(Result.class);
    private final Result taskOutcome = new Result("Checker action outcome");
    private final CheckerAction instance = new CheckerAction(
            "EpubChecker", targetFile, false);
    
    public CheckerActionTest() {
    }
    
    @Before
    public void setup() {
      doReturn(taskOutcome).when(checker).getResult();
    }
    
    @Test
    public void testDoAction() throws Exception {
        CheckerAction instanceSpy = spy(instance);
        
        doReturn(checker).when(instanceSpy).createChecker(
                Matchers.anyString(), Matchers.anyObject());
        
        instanceSpy.doAction();
        
        verify(instanceSpy, times(1)).run(checker);
    }
    
    @Test
    public void testCreateChecker_builds_checker() throws Exception {
        System.out.println("#createChecker gets checker from action factory");
        
        Checker result = instance.createChecker("Epub Checker", targetFile);
        
        assertTrue(result.getClass() == EpubChecker.class);
    }
    
    @Test
    public void testRun_calls_Setup() throws Exception {
        System.out.println("#run sets up checker");
        
        instance.run(checker);
        verify(checker, times(1)).setup();
    }
    
    @Test
    public void testRun_calls_Check() throws Exception {
        System.out.println("#run runs checker");
        
        instance.run(checker);
        verify(checker, times(1)).check();
    }
    
    @Test
    public void testRun_calls_tearDown() throws Exception {
        System.out.println("#run tears down checker");
        
        instance.run(checker);
        verify(checker, times(1)).tearDown();
    }
    
    @Test
    public void testRun_addsResults() throws Exception {
        System.out.println("#run merges task results with action results");
        
        Field actionOutcomeStub = Action.class
                .getDeclaredField("actionOutcome");
        actionOutcomeStub.setAccessible(true);
        actionOutcomeStub.set(instance, actionOutcome);
        
        instance.run(checker);
        verify(actionOutcome).addResult(Matchers.anyObject());
    }
    
}
