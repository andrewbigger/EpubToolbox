package com.biggerconcept.epubtoolbox.services;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for managing UI tool tips.
 * 
 * @author abigger
 */
public class TooltipService {
    /**
     * Map of attributes and tool tip text.
     */
    private final Map TOOL_TIPS = new HashMap();
    
    /**
     * Constructor for the tool tip service.
     * 
     * This will build a map of UI components that should be annotated
     * with tool tips and their value.
     */
    public TooltipService() {
        populateTips();
    }
    
    /**
     * Registers component tool tips with the service tool tip map.
     */
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
    
    /**
     * Retrieves a tool tip for a given component by name.
     * 
     * When the tool tip is not registered, then an empty string will be
     * returned.
     * 
     * @param componentName
     * @return 
     */
    public String getTip(String componentName) {
        String tip = (String) TOOL_TIPS.get(componentName);
        return tip;
    }
    
}
