package com.biggerconcept.epubtoolbox.checkers;

import java.io.File;
import org.junit.Test;

public class CheckerTest {
    private final Checker instance = new Checker(
            new File("path/to/fake.epub"));
    
    public CheckerTest() {
    }   

    @Test(expected=UnsupportedOperationException.class)
    public void testCheck() throws Exception {
        System.out.println("#check fails on checker super class");
        instance.check();
    }
}
