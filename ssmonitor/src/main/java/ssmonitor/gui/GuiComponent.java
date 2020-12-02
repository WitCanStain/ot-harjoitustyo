
package ssmonitor.gui;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;



public class GuiComponent {
    
    private final String componentName;
    private String text = "";
    private Node node;
    public GuiComponent(String component) {
        this.componentName = component;
        nodeConstructor();
    }
    
    public GuiComponent(String component, String textLabel) {
        this.componentName = component;
        this.text = textLabel;
        nodeConstructor();
    }
    
    
    private void nodeConstructor() {
        
        if (componentName.equals("system_memory")) {
            node = RTNodes.progressBar(componentName);
        } else if (componentName.equals("cpu_usage")) {
            node = RTNodes.lineChart(componentName);
        }
        System.out.println(text);
        if(text != null && !text.isEmpty()) {
            node = new HBox(new Label(this.text), node);
            
        }
    }
    
    private Node nodeConstructorHelper() {
        
        if (componentName.equals("cpu_usage")) {
            return RTNodes.lineChart(componentName);
        } else if (componentName.equals("system_memory")) {
            return null;
            //return systemMemory();
        } else {
            return null;
        }
    }
    
    
    
    
    
    public String getName() {
        return this.componentName;
    }
    
    public Node getNode() {
        return node;
    }
    
    
}
