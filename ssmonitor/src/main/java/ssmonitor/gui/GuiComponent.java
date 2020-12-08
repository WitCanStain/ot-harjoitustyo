
package ssmonitor.gui;
import ssmonitor.sysinfo.SysInfo;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import java.util.ArrayList;


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
        
        if (Objects.isNull(sysInfoComponent) && !Objects.isNull(textLabel)) {
            guiNodes.add(new Label(textLabel));
        } else if (!Objects.isNull(presentationType)) {
            if (presentationType.equals("linechart")) {
                guiNodes.add(new HBox(new Label(textLabel), RTNodes.lineChart(sysInfoCallDecider(sysInfoComponent), refreshRate)));
            } else if (presentationType.equals("progressbar")) {
                guiNodes.add(new HBox(new Label(textLabel), RTNodes.progressBar(sysInfoCallDecider(sysInfoComponent), refreshRate)));
            }
        } else if (Objects.isNull(presentationType)) {
            guiNodes.add(new HBox(new Label(textLabel), new Label(SysInfo.getSystemProperty(sysInfoComponent))));
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
        } else if (sysInfoComponent.equals("system_memory")) {
            return 2;
        } else if (sysInfoComponent.equals("total_memory")) {
            return 3;
        } else {
            System.out.println("Invalid configuration file: sysInfoComponent not numerical.");
            return 0;
        }
    }
    
    
    
    public static ArrayList<Node> getNodes() {
        return guiNodes;
    }
    
    
}
