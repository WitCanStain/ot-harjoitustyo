
package ssmonitor.file;
import ssmonitor.gui.GuiComponent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;



public class ParseConf {
    private String fileName;
    private static ArrayList<Node> guiNodes = new ArrayList<>();
    private static int statusFlag;
    private static List<String> sysInfoComponents = Arrays.asList("cpu_usage","system_memory");

    public static void readConf(final String fileName) {
        try {
            File confFile = new File(fileName);
            Scanner reader = new Scanner(confFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                statusFlag = parseLine(line);
                if (statusFlag == -1) {
                    System.out.println("Invalid configuration file.");
                    System.out.println(line);
                    break; //quit
                }
            }

              reader.close();
            } catch (FileNotFoundException e) {
            System.out.println("Please choose a valid filename.");  
            statusFlag = -2; // quit
            }
    }

    public static int parseLine(final String line) {

        if (line.startsWith("//") || line.trim().length() == 0) {
            return 0;
        } else if (line.startsWith("$")) {
            
            String sysInfoComponent = null;
            String textLabel = null;
            
            String[] args = line.substring(1).split("\\|",0);
            
            // checking whether all of the parameters are valid inputs
            for (String parameter : args) {
                
                
                if (sysInfoComponents.contains(parameter)) {
                    
                    sysInfoComponent = parameter;
                } else if ((parameter.startsWith("%"))) {
                    textLabel = parameter.substring(1);
                } else {
                   System.out.println(parameter);
                   return -1;
                }
            }
            
            GuiComponent guiComponent = new GuiComponent(args[0],textLabel);
            guiNodes.add(guiComponent.getNode());

            return 1;
        } else {
            System.out.println(line);
            return -1;
        }
    }

    public static ArrayList<Node> getNodes() {
        return guiNodes;
    }
    
    
    public static int getStatusFlag() {
        return statusFlag;
    }
    
}
