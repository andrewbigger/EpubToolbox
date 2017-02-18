package com.biggerconcept.epubtoolbox.services;

import java.util.HashMap;
import java.util.Map;

public class TooltipService {
    private final Map TOOL_TIPS = new HashMap();
    
    public TooltipService() {
        populateTips();
    }
    
    private void populateTips() {
        TOOL_TIPS.put("unpackButton", "Unpack ePub");
        TOOL_TIPS.put("packButton", "Pack ePub");

        TOOL_TIPS.put("pickButton", "Copy ePub(s) from folder to another folder");
        TOOL_TIPS.put("epubCheckButton", "Run ePubCheck");
        TOOL_TIPS.put("imageCheckButton", "Run Image size check");
        TOOL_TIPS.put("sizeCheckButton", "Run File size check");
        TOOL_TIPS.put("allCheckButton", "Run all checks");

        TOOL_TIPS.put("recordButton", "Record transaction");
        TOOL_TIPS.put("stopButton", "Stop recording");
        TOOL_TIPS.put("playButton", "Replay recorded transaction");

        TOOL_TIPS.put("repeatButton", "Repeat last action");

        TOOL_TIPS.put("clearConsoleButton", "Remove messages from console");
        TOOL_TIPS.put("runOnCollectionChoice", "Run action on folder of epubs?");
        TOOL_TIPS.put("exportLogButton", "Save messages to text file");
    }
    
    public String getTip(String componentName) {
        String tip = (String) TOOL_TIPS.get(componentName);
        return tip;
    }
    
}
