package com.biggerconcept.epubtoolbox.checkers;

import com.adobe.epubcheck.api.EpubCheck;
import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;
import java.lang.reflect.Field;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EpubCheckerTest {    
    private final EpubChecker instance = new EpubChecker(new File("path/to/epub"));
    private final EpubCheck epcLib = mock(EpubCheck.class);
    
    public void setUp() 
            throws IllegalArgumentException, IllegalAccessException, 
            NoSuchFieldException {
    }
    
    @Test
    public void testCheck() throws Exception {
        System.out.println("#check delegates validation to epubcheck lib");
        
        Field epubCheckStub = EpubChecker.class.getDeclaredField("epc");
        epubCheckStub.setAccessible(true);
        epubCheckStub.set(instance, epcLib);
        
        instance.check();
        verify(epcLib).validate();
    }
    
    @Test
    public void testGetResult() {
        System.out.println("#getResult returns result object");
        
        Result outcome = new Result("Epub check outcome");
        instance.checkOutcome = outcome;
        
        assertEquals(outcome, instance.getResult());
    }
}
