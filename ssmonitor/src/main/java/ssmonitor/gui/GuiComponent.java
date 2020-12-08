
package ssmonitor.gui;
import ssmonitor.sysinfo.SysInfo;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import java.util.ArrayList;



public class GuiComponent {
    private static ArrayList<Node> guiNodes = new ArrayList<Node>();
    
    
    
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
    
    private static int sysInfoCallDecider(String sysInfoComponent) {
        if (sysInfoComponent.equals("cpu_usage")) {
                return 1;
            } else if (sysInfoComponent.equals("system_memory")) {
                return 2;
            } else if (sysInfoComponent.equals("total_memory")) {
                return 3;
            } else {
                System.out.println("Invalid configuration file: sysInfoComponent not numerical.");
                System.exit(0);
                return 0;
            }
    }
    
    
    
    public static ArrayList<Node> getNodes() {
        return guiNodes;
    }
    
    
}
