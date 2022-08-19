package com.biggerconcept.epubtoolbox.services;

import com.biggerconcept.epubtoolbox.actions.Action;
import com.biggerconcept.epubtoolbox.actions.IAction;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
    
    private class FakeAction extends Action implements IAction {
        
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
    public void testGetLastAction() {
        System.out.println("#getLastAction returns last executed action");
        instance.setLastAction(task);
        assertEquals(task, instance.getLastAction());
    }

    @Test
    public void testSetLastAction()
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("#setLastAction allows lastAction to be set");

        instance.setLastAction(task);
        Field lastAction = ToolboxService.class.getDeclaredField("lastAction");
        lastAction.setAccessible(true);

        assertEquals(task, (Action) lastAction.get(instance));
    }
    
    @Test
    public void testHasLastAction_WhenLastActionIsPresent() {
        System.out.println("#hasLastAction returns false when lastAction is null");
        instance.setLastAction(task);
        assertTrue(instance.hasLastAction());
    }

    @Test
    public void testHasLastAction_WhenLastActionIsNull() {
        System.out.println("#hasLastAction returns false when lastAction is null");
        assertFalse(instance.hasLastAction());
    }

    @Test
    public void testGetRecording() {
        System.out.println("#getRecording returns recording array");
        ArrayList<Action> expResult = new ArrayList();

        assertEquals(expResult, instance.getRecording());
    }

    @Test
    public void testAddToRecording() {
        System.out.println("#addToRecording will add action to recording list");
        instance.addToRecording(task);
        assertEquals(task, (Action) instance.getRecording().toArray()[0]);
    }

    @Test
    public void testClearRecording() {
        System.out.println("#clearRecording will discard all actions in record list");

        for (int i = 0; i < 10; i++) instance.addToRecording(task);
        instance.clearRecording();

        assertTrue(instance.getRecording().isEmpty());
    }

    @Test
    public void testHasRecording_WhenThereIsARecording() {
        System.out.println("#hasRecording when there is one");

        for (int i = 0; i < 3; i++) instance.addToRecording(task);

        assertTrue(instance.hasRecording());
    }

    @Test
    public void testHasRecording_WhenThereIsNotARecording() {
        System.out.println("#hasRecording when there is not one");
        instance.clearRecording();
        assertFalse(instance.hasRecording());
    }

    @Test
    public void testIsRecording_DefaultIsNotInProgress() {
        System.out.println("#isRecording is false by default");
        assertFalse(instance.isRecording());
    }

    @Test
    public void testIsRecording_WhenRecordingIsInProgress() {
        System.out.println("#isRecording is true when in progress");
        instance.setRecordingStatus(true);
        assertTrue(instance.isRecording());
    }

    @Test
    public void testIsRecording_WhenRecordingIsNotInProgress() {
        System.out.println("#isRecording is false when not in progress");
        instance.setRecordingStatus(false);
        assertFalse(instance.isRecording());
    }

    @Test
    public void testSetRecordingStatus_Truth()
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        System.out.println("#setRecordingStatus sets rec field");

        instance.setRecordingStatus(true);
        Field recState = ToolboxService.class.getDeclaredField("rec");
        recState.setAccessible(true);

        assertTrue((boolean) recState.get(instance));
    }

    @Test
    public void testSetRecordingStatus_False()
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        System.out.println("#setRecordingStatus sets rec field");

        instance.setRecordingStatus(false);
        Field recState = ToolboxService.class.getDeclaredField("rec");
        recState.setAccessible(true);

        assertFalse((boolean) recState.get(instance));
    }
    
    @Test
    public void testPerformAction_PerformsTask() throws Exception {
        System.out.println("#performAction calls perform on task");
        Action taskSpy = mock(Action.class);
        
        instance.performAction(taskSpy);
        
        verify(taskSpy).doAction();
    }
    
    @Test
    public void testPerformAction_SetsLastActionToPerformedTask() throws Exception {
        System.out.println("#performAction sets last action to performed task");
        instance.performAction(task);
        assertEquals(instance.getLastAction(), task);
    }

    @Test
    public void testPerformAction_AddsPerformedTaskToRecordingIfRecording() throws Exception {
        System.out.println("#performAction adds performed task to recording if recording");

        instance.setRecordingStatus(true);
        instance.performAction(task);

        assertEquals(instance.getRecording().toArray()[0], task);
    }

    @Test
    public void testPerformAction_DoesNotAddPerformedTaskToRecordingIfRecording() throws Exception {
        System.out.println("#performAction does not performed task to recording if not recording");

        instance.setRecordingStatus(false);
        instance.performAction(task);

        assertTrue(instance.getRecording().isEmpty());
    }
}
