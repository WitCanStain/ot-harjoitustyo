
package ssmonitor.gui;
import ssmonitor.sysinfo.SysInfo;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Properties;
import javafx.scene.text.Text;
import ssmonitor.sysinfo.RTExecutors;


/**
 * Methods in this class create new nodes for the javaFX stage.
 * @author ruby
 */
public class GuiComponent {
    private static ArrayList<Node> guiNodes = new ArrayList<Node>();
    
    
    
    /**
     * Issues a request to create a new node according given parameters.
     * @param sysInfoComponent The information the user wants to see
     * @param textLabel Possible text to accompany the information
     * @param presentationType The way in which the information is displayed
     * @param refreshRate How often the information should be updated
     */
    public static void constructNode(String sysInfoComponent, String textLabel, String presentationType, int refreshRate) {
        Text label = new Text (textLabel);
//        label.setWrapText(true);
        
        if (Objects.isNull(sysInfoComponent) && !Objects.isNull(textLabel)) {
            guiNodes.add(label);
        } else if (!Objects.isNull(presentationType)) {
            if (presentationType.equals("linechart")) {
                guiNodes.add(new HBox(label, RTNodes.lineChart(sysInfoCallDecider(sysInfoComponent), refreshRate)));
            } else if (presentationType.equals("progressbar")) {
                guiNodes.add(new HBox(label, RTNodes.progressBar(sysInfoCallDecider(sysInfoComponent), refreshRate)));
            } else if (presentationType.equals("text")) {
                
                if (System.getProperties().keySet().contains(sysInfoComponent)) {
                    guiNodes.add(new HBox(label, new Text(SysInfo.getSystemProperty(sysInfoComponent))));
                } else {
                    guiNodes.add(new HBox(label, RTNodes.text(sysInfoCallDecider(sysInfoComponent), refreshRate)));
                }
            }
        } else if (Objects.isNull(presentationType)) {
            guiNodes.add(new HBox(label, new Label(SysInfo.getSystemProperty(sysInfoComponent))));
        }   
        
    }
    /**
     * Checks which information type should be fetched.
     * @param sysInfoComponent The information the user wants to see
     * @return A numerical code for the desired information
     */
    private static int sysInfoCallDecider(String sysInfoComponent) {
        if (sysInfoComponent.equals("cpu_usage")) {
            return 1;
        } else if (sysInfoComponent.equals("drive_memory_percentage")) {
            return 2;
        } else if (sysInfoComponent.equals("drive_memory_total")) {
            return 3;
        } else if (sysInfoComponent.equals("ram_total")) {
            return 4;
        } else if (sysInfoComponent.equals("ram_free")) {
            return 5;
        } else if (sysInfoComponent.equals("ram_usage_percentage")) {
            return 6;
        } else {
            System.out.println("Invalid configuration file: sysInfoComponent not numerical.");
            RTExecutors.shutdownAll();
            return 0;
        }
    }
    
    
    
    public static ArrayList<Node> getNodes() {
        return guiNodes;
    }
    
    public static Node getLastNode() {
        return guiNodes.get(guiNodes.size() - 1);
    }
    
    
}
