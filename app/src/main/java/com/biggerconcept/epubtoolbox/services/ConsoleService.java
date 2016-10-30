package com.biggerconcept.epubtoolbox.services;

import com.biggerconcept.epubtoolbox.results.Message;
import com.biggerconcept.epubtoolbox.results.Result;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class ConsoleService {
    private final TreeView<String> console;
    private TreeItem<String> consoleMessages;
    
    public ConsoleService(TreeView<String> console) {
        this.console = console;
        this.initialize();
    }
    
    private void initialize() {
        consoleMessages = new TreeItem<> ("Outputs");
        consoleMessages.setExpanded(true);
        
        console.setRoot(consoleMessages);
        console.setShowRoot(false);
    }
    
    public void clear() {
        initialize();
    }
    
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
    
    public void out(Result res, String title) {
        out(res, title, true);
    }
    
    public void out(Message msg, boolean expanded) {
        TreeItem<String> newItem = outItem(msg);
        newItem.setExpanded(expanded);
        
        consoleMessages.getChildren().add(newItem);
    }
    
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
