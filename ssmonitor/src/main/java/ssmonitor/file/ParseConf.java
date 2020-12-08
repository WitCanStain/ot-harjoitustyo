
package ssmonitor.file;
import ssmonitor.gui.GuiComponent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javafx.scene.Node;




public class ParseConf {
    private String fileName;
    final private static ArrayList<Node> guiNodes = new ArrayList<>();
    private static int statusFlag;
    final private static List<String> sysInfoComponents = Arrays.asList(
            "cpu_usage", "system_memory", "total_memory", "os.name", "os.version", "user.name", "os.arch"
    );
    final private static List<String> presentationTypes = Arrays.asList("linechart","progressbar");

    public static void readConf(final String fileName) {
        try {
            File confFile = new File(fileName);
            Scanner reader = new Scanner(confFile);
            while (reader.hasNextLine()) {
                
                String line = reader.nextLine();
                parseLine(line);
            }
            
            reader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Please choose a valid filename.");  
            statusFlag = -2; // quit
            System.exit(0);
        }
    }

    public static void parseLine(final String line) {

        if (line.startsWith("//") || line.trim().length() == 0) {
            return;
        } else {
            
            String sysInfoComponent = null;
            String textLabel = null;
            String presentationType = null;
            String refreshRate = null;
            
            String[] parameters = line.split("\\|", 0);
            // parsing the configuration and checking parameter validity
            for (String parameter : parameters) {
                if (parameter.startsWith("$")) {
                    sysInfoComponent = parameter.substring(1);
                    checkParameter("sysInfoComponent", sysInfoComponent);
                    System.out.println(sysInfoComponent);
                } else if ((parameter.startsWith("%"))) {
                    textLabel = parameter.substring(1);
                } else if (parameter.contains("presentation_type=")) {
                    presentationType = parameter.replace("presentation_type=","");
                    checkParameter("presentationType", presentationType);
                } else if (parameter.contains("refresh_rate=")) {
                    refreshRate = parameter.replace("refresh_rate=","");
                    checkParameter("refreshRate", refreshRate);
                } else {
                    System.out.println("Invalid configuration file: parameter not recnognized.");
                    System.exit(0);
                }
            }
            
            if (sysInfoComponent == null && (!Objects.isNull(presentationType) || !Objects.isNull(refreshRate))) {
                System.out.println("Invalid configuration file: sysInfoComponent parameters without sysInfoComponent.");
                System.exit(0);
            } else if (sysInfoComponent == null && textLabel == null) {
                System.out.println("Invalid configuration file: no label or sysInfoComponent provided.");
                System.exit(0);
            }
            int refreshRateAsInt = 500;
            if (!(Objects.isNull(refreshRate))) { refreshRateAsInt = Integer.parseInt(refreshRate); }
            GuiComponent.constructNode(sysInfoComponent, textLabel, presentationType, refreshRateAsInt);
            return;
        } 
    }
    
    public static void checkParameter(String parameterType, String parameterValue) {
        
        if (parameterType.equals("presentationType") && !presentationTypes.contains(parameterValue)) {
                System.out.println("Invalid configuration file: presentation_type value not recognized.");
                System.exit(0);
        } else if (parameterType.equals("refreshRate")) {
            int parameterValueAsInt = -1;
            try {
                parameterValueAsInt = Integer.parseInt(parameterValue);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid configuration file: refresh_rate value not a number.");
                System.exit(0);
            }
            if (parameterValueAsInt < 0) {
                System.out.println("Invalid configuration file: refresh_rate value less than zero.");
                System.exit(0);
            }
        } else if (parameterType.equals("sysInfoComponent") && !sysInfoComponents.contains(parameterValue)) {
            System.out.println("Invalid configuration file: sysinfocomponent not recognized.");
            System.exit(0);
        }
    }

    public static ArrayList<Node> getNodes() {
        return guiNodes;
    }
    
    
    public static int getStatusFlag() {
        return statusFlag;
    }
    
}
