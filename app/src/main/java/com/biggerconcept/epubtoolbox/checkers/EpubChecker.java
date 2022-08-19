package com.biggerconcept.epubtoolbox.checkers;

import com.adobe.epubcheck.api.EpubCheck;
import com.biggerconcept.epubtoolbox.results.Message;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

public class EpubChecker extends Checker implements IChecker {
    private final ByteArrayOutputStream outCapture = new ByteArrayOutputStream();
    private final PrintStream outCaptureStream;
    
    private final PrintStream originalOutStream;
    private final PrintStream originalErrStream;
    
    private final EpubCheck epc;
    
    public EpubChecker(File inEpub) {
        super(inEpub);
        epc = new EpubCheck(inEpub);
        
        outCaptureStream = new PrintStream(outCapture);
        
        originalOutStream = System.out;
        originalErrStream = System.err;
    }
       
    @Override
    public void check() throws Exception { 
        getResult().setTitle("Epub Check Results");
        
        hijackSystemStreams();
        
        boolean isPass = epc.validate();
        
        flushStreams();
        unHijackSystemStreams();       
        
        if (isPass) {
            getResult().addMessage(new Message("info", outCapture.toString()));
        }
        else {
            getResult().addMessage(new Message("error", outCapture.toString()));
        }
    }
    
    private void hijackSystemStreams() {
        System.setOut(outCaptureStream);
        System.setErr(outCaptureStream);
    }
    
    private void flushStreams() {
        System.out.flush();
        System.err.flush();
    }
    
    private void unHijackSystemStreams() {
        System.setOut(originalOutStream);
        System.setErr(originalErrStream);
    }
    
}
