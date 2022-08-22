package com.biggerconcept.epubtoolbox.services;

import com.biggerconcept.epubtoolbox.results.Message;
import com.biggerconcept.epubtoolbox.results.Result;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Service for managing the toolbox status console.
 * 
 * @author abigger
 */
public class ConsoleService {
    /**
     * Tree view for holding console messages grouped by action.
     */
    private final TreeView<String> console;
    
    /**
     * Set of tree view items that hold console messages.
     */
    private TreeItem<String> consoleMessages;
    
    /**
     * Constructor for console service.
     * 
     * This initializes the component with the given console tree view and
     * then calls the initialize method to set up the console tree view.
     * 
     * @param console 
     */
    public ConsoleService(TreeView<String> console) {
        this.console = console;
        this.initialize();
    }
    
    /**
     * Initializes the console view by adding the root tree view item
     * and setting expanded to true.
     */
    private void initialize() {
        consoleMessages = new TreeItem<> ("Outputs");
        consoleMessages.setExpanded(true);
        
        console.setRoot(consoleMessages);
        console.setShowRoot(false);
    }
    
    /**
     * Clears the console by calling initialize, which returns it to its
     * default state.
     */
    public void clear() {
        initialize();
    }
    
    /**
     * Adds a tree view item for a result the console view.
     * 
     * @param res
     * @param title
     * @param expanded 
     */
    public void out(Result res, String title, boolean expanded) {        
        TreeItem<String> newItem = outItem(res, title);
        
        if (res.isPass()) {
            newItem.getChildren().add(
                    outItem(
                        new Message("info", 
                                title + " completed successfully.")));
        } else {
            newItem.getChildren().add(
                    outItem(
                        new Message("error", 
                                title + " completed with errors.")));
        }
        
        newItem.setExpanded(expanded);
        
        consoleMessages.getChildren().add(newItem);
    }
    
    /**
     * Adds a tree view item for a result to the console view.
     * 
     * @param res
     * @param title 
     */
    public void out(Result res, String title) {
        out(res, title, true);
    }
    
    /**
     * Adds a tree view item for a result to the console view.
     * 
     * @param msg
     * @param expanded 
     */
    public void out(Message msg, boolean expanded) {
        TreeItem<String> newItem = outItem(msg);
        newItem.setExpanded(expanded);
        
        consoleMessages.getChildren().add(newItem);
    }
    
    /**
     * 
     * @param msg 
     */
    public void out(Message msg) {
        out(msg, true);
    }
    
    public void err(String actionName, Exception e) {
        Result error = new Result("Error Details");
        error.addMessage(new Message(
                "error", e.toString()));
        
        this.out(error, actionName + " failed because: " + e.getMessage(), false);
    }
    
    @Override
    public String toString() {
        return nodeValue(consoleMessages, "");
    }
    
    private String nodeValue(TreeItem<String> node, String indent) {
        String nodeValue = indent + node.getValue();
        
        nodeValue += "\n";
        
        nodeValue = node
                .getChildren()
                .stream()
                .map((childNode) -> nodeValue(childNode, indent + "\t"))
                .reduce(nodeValue, String::concat);
        
        return nodeValue;
    }
    
    protected TreeItem<String> outItem(Result rslt, String title) {
        TreeItem<String> result = new TreeItem<>(title);
        
        rslt.getMessages().stream().forEach((msg) -> {
            result.getChildren().add(outItem(msg));
        });
        
        rslt.getResults().stream().map((nestedResult) -> 
                outItem(nestedResult, nestedResult.getTitle()))
                .forEach((subItems) -> {
            result.getChildren().add(subItems);
        });

        result.setExpanded(true);
        
        return result;
    }
    
    protected TreeItem<String> outItem(Message msg) {
        TreeItem<String> newItem = new TreeItem<>(msg.getMessage());
        newItem.setExpanded(true);
        
        return newItem;
    }
}
