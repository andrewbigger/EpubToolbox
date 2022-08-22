package com.biggerconcept.epubtoolbox.checkers;

import com.adobe.epubcheck.api.EpubCheck;
import com.biggerconcept.epubtoolbox.results.Message;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

/**
 * Action that executes the Adobe EPUB check tool
 * 
 * @author abigger
 */
public class EpubChecker extends Checker implements IChecker {
    /**
     * Byte array output stream that captures the output of the EPUB check tool
     */
    private final ByteArrayOutputStream outCapture = 
            new ByteArrayOutputStream();
    
    /**
     * Print stream for capturing the output of EPUB check tool
     */
    private final PrintStream outCaptureStream;
    
    /**
     * Print stream for capturing info messages from the EPUB check tool
     */
    private final PrintStream originalOutStream;
    
    /**
     * Print stream for capturing error messages from EPUB check tool
     */
    private final PrintStream originalErrStream;
    
    /**
     * EPUB check instance
     */
    private final EpubCheck epc;
    
    /**
     * Constructor for EPUB check.
     * 
     * Sets up new instance of EPUB check and output capture stream.
     * 
     * Also sets the System.out and System.err streams on the out and err 
     * stream instance variable.
     * 
     * @param inEpub 
     */
    public EpubChecker(File inEpub) {
        super(inEpub);
        epc = new EpubCheck(inEpub);
        
        outCaptureStream = new PrintStream(outCapture);
        
        originalOutStream = System.out;
        originalErrStream = System.err;
    }
    
    /** 
     * Runs EPUB check.
     * 
     * Adds the EPUB check results header to the result. Then hijacks system
     * out streams so that the output of the EPUB check can be put into the 
     * result.
     * 
     * If the validation is successful, then the info stream contents is added
     * to the result.
     * 
     * If the validation is unsuccessful then the error output stream is
     * added to the result.
     * 
     * @throws Exception 
     */
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
    
    /**
     * Temporarily capture the SystemOut and SystemErr streams to the output
     * capture stream.
     */
    private void hijackSystemStreams() {
        System.setOut(outCaptureStream);
        System.setErr(outCaptureStream);
    }
    
    /**
     * Flushes SystemOut and SystemError streams before consumption to ensure
     * that application log messages are not captured as part of the EPUB
     * check result.
     */
    private void flushStreams() {
        System.out.flush();
        System.err.flush();
    }
    
    /**
     * Stop capturing the SystemOut and SystemError streams.
     */
    private void unHijackSystemStreams() {
        System.setOut(originalOutStream);
        System.setErr(originalErrStream);
    }
    
}
