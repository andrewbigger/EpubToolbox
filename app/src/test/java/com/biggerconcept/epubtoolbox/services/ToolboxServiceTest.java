package com.biggerconcept.epubtoolbox.services;

import com.biggerconcept.epubtoolbox.actions.Action;
import com.biggerconcept.epubtoolbox.actions._IAction;
import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ToolboxServiceTest {
    private ToolboxService instance;
    private File fakeEpub;
    private Action task;
    
    @Before
    public void setUp() {
        instance = new ToolboxService();
        fakeEpub = new File("path/to/fake/epub");
        task = new FakeAction("SomeAction", fakeEpub, false);
    }
    
    private class FakeAction extends Action implements _IAction {
        
        public FakeAction(
                String task, File targetLocation, boolean runOnCollection) {
            super(task, targetLocation, runOnCollection);
        }
        
        public FakeAction(
                String task, File targetLocation, File outTargetLocation, boolean runOnCollection) {
            super(task, targetLocation, outTargetLocation, runOnCollection);
        }
        
        @Override
        public void doAction() throws Exception {
            // do nothing
        }
        
        @Override
        public void reportIntention() {
            // say nothing
        }
        
    }
    
    @Test
    public void testPerformAction_PerformsTask() throws Exception {
        System.out.println("#performAction calls perform on task");
        Action taskSpy = mock(Action.class);
        
        instance.performAction(taskSpy);
        
        verify(taskSpy).doAction();
    }
}
