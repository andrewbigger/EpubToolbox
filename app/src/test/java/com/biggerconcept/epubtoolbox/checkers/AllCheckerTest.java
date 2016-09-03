package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Result;
import java.io.File;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class AllCheckerTest {
    private final AllChecker instance = new AllChecker(new File("path/to/epub"));
    private final Result checkResult = new Result("All check outcome");
    private final EpubChecker epubChecker = mock(EpubChecker.class);
    private final FileSizeChecker fileSizeChecker = mock(FileSizeChecker.class);
    private final ImageSizeChecker imageSizeChecker = mock(ImageSizeChecker.class);
    
    public AllCheckerTest() {
    }
    
    @Before
    public void setup() throws Exception {
        Checker[] checks = new Checker[3];
        checks[0] = epubChecker;
        checks[1] = fileSizeChecker;
        checks[2] = imageSizeChecker;
        
        Field checkStub = AllChecker.class.getDeclaredField(("validations"));
        checkStub.setAccessible(true);
        checkStub.set(instance, checks);
        
        doReturn(checkResult).when(epubChecker).getResult();
        doReturn(checkResult).when(fileSizeChecker).getResult();
        doReturn(checkResult).when(imageSizeChecker).getResult();
    }
    
    @Test
    public void testCheck_RunsEpubChecker() throws Exception {
        System.out.println("#check calls check on epub checker");
        
        instance.check();
        verify(epubChecker, Mockito.times(1)).check();
    }
    
    @Test
    public void testCheck_RunsFileSizeChecker() throws Exception {
        System.out.println("#check calls check on file size checker");
        
        instance.check();
        verify(fileSizeChecker, Mockito.times(1)).check();
    }
    
    @Test
    public void testCheck_RunsImageSizeChecker() throws Exception {
        System.out.println("#check calls check on image size checker");
        
        instance.check();
        verify(imageSizeChecker, Mockito.times(1)).check();
    }
    
    @Test
    public void testCheck_MergesTestResults() throws Exception {
        System.out.println("#check merges results of check");
        
        AllChecker instanceSpy = spy(instance);
        Result resultSpy = spy(new Result("All check outcome"));
        
        doReturn(resultSpy).when(instanceSpy).getResult();
        
        instanceSpy.check();
        
        verify(resultSpy, Mockito.times(3)).addResult(Mockito.anyObject());
    }
    
}
