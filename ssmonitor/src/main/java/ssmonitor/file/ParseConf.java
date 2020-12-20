
package ssmonitor.file;
import ssmonitor.gui.GuiComponent;
import ssmonitor.sysinfo.RTExecutors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javafx.scene.Node;



/**
 * This class parses the configuration file.
 * @author ruby
 */
public class ParseConf {
    private static String fileName;
    final private static ArrayList<Node> guiNodes = new ArrayList<>();
    private static int statusFlag;
    final private static List<String> sysInfoComponents = Arrays.asList(
            "cpu_usage", "drive_memory_percentage", "drive_memory_total", "ram_usage_percentage", "ram_free", "ram_total", "os.name", "os.version", "user.name", "os.arch");
    final private static List<String> presentationTypes = Arrays.asList("linechart", "progressbar", "text");

    
    /**
     * Reads the configuration file.
     * @param filePath the file to be read
     */
    public static void readConf(final String filePath, int testFlag) {
        
        try {
            File confFile = new File(filePath);
            fileName = filePath;
            Scanner reader = new Scanner(confFile);
            while (reader.hasNextLine()) {
                
                String line = reader.nextLine();
                parseLine(line, 0);
            }
            
            reader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Please choose a valid filename.");  
            statusFlag = -2;
            if (!(testFlag == 1)) {
                RTExecutors.shutdownAll();
            }
            
            
            
        }
    }
    
    /**
     * Parses a given configuration file line and issues a request to create
     * new nodes.
     * @param line A line from the configuration file
     */
    public static void parseLine(String line, int testFlag) {

        if (line.startsWith("//") || line.trim().length() == 0) {
            return;
        } else {
            
            String sysInfoComponent = null;
            String textLabel = null;
            String presentationType = null;
            String refreshRate = null;
            if (line.startsWith("|")) {
                line = line.substring(1);
            }
            String[] parameters = line.split("\\|", 0);
            
            // parsing the configuration and checking parameter validity
            for (String parameter : parameters) {
                
                if (parameter.startsWith("$")) {
                    sysInfoComponent = parameter.substring(1);
                    checkParameter("sysInfoComponent", sysInfoComponent);
                } else if ((parameter.startsWith("%"))) {
                    textLabel = parameter.substring(1);
                } else if (parameter.contains("presentation_type=")) {
                    presentationType = parameter.replace("presentation_type=", "");
                    checkParameter("presentationType", presentationType);
                } else if (parameter.contains("refresh_rate=")) {
                    refreshRate = parameter.replace("refresh_rate=", "");
                    checkParameter("refreshRate", refreshRate);
                } else {
                    System.out.println("Invalid configuration file: parameter not recognized.");
                    statusFlag = -1;
                    if (!(testFlag==1)) {
                        RTExecutors.shutdownAll();
                    }
                    
                }
            }
            
            if (Objects.isNull(sysInfoComponent) && (!Objects.isNull(presentationType) || !Objects.isNull(refreshRate))) {
                System.out.println("Invalid configuration file: sysInfoComponent parameters without sysInfoComponent.");
                statusFlag = -1;
                if (!(testFlag==1)) {
                        RTExecutors.shutdownAll();
                    }
            } else if (Objects.isNull(sysInfoComponent) && Objects.isNull(textLabel)) {
                System.out.println("Invalid configuration file: no label or sysInfoComponent provided.");
                statusFlag = -1;
                if (!(testFlag==1)) {
                        RTExecutors.shutdownAll();
                    }
                
            }
            int refreshRateAsInt = 500;
            if (!(Objects.isNull(refreshRate))) {
                refreshRateAsInt = Integer.parseInt(refreshRate);
            }
            if (!(testFlag==1)) {
                GuiComponent.constructNode(sysInfoComponent, textLabel, presentationType, refreshRateAsInt);
            }
            
            
        } 
    }
     
    public static void parseGuiParameters(String[] guiParameters, boolean modifyFile) {
        String confLine = "";
        if (!Objects.isNull(guiParameters[0])) {
            confLine = confLine + "|$" + guiParameters[0];
        }
        if (!Objects.isNull(guiParameters[1])) {
            confLine = confLine + "|presentation_type=" + guiParameters[1];
        }
        if (!guiParameters[2].isEmpty()) {
            confLine = confLine + "|refresh_rate=" + guiParameters[2];
        }
        if (!guiParameters[3].isEmpty()) {
            confLine = confLine + "|%" + guiParameters[3];
        }
        
        parseLine(confLine, 0);
        if (modifyFile) {
            writeLine(confLine);
        }
    }
    
    public static void writeLine(String confLine) {
        File confFile = new File(fileName);
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write(confLine);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error - did you delete the configuration file?");
            statusFlag = -3;
            RTExecutors.shutdownAll();
        }
    }
    
    /**
     * Checks whether a given parameter is valid.
     * @param parameterType The type of parameter being checked
     * @param parameterValue The value of the parameter being checked
     */
    public static void checkParameter(String parameterType, String parameterValue) {
        
        if (parameterType.equals("presentationType") && !presentationTypes.contains(parameterValue)) {
            System.out.println("Invalid configuration file: presentation_type value not recognized.");
            statusFlag = -1;
            RTExecutors.shutdownAll();
        } else if (parameterType.equals("refreshRate")) {
            int parameterValueAsInt = -1;
            try {
                parameterValueAsInt = Integer.parseInt(parameterValue);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid configuration file: refresh_rate value not a number.");
                statusFlag = -1;
                RTExecutors.shutdownAll();
                return;
            }
            if (parameterValueAsInt < 0) {
                System.out.println("Invalid configuration file: refresh_rate value less than zero.");
                statusFlag = -1;
                RTExecutors.shutdownAll();
                
            }
        } else if ((parameterType.equals("sysInfoComponent") || parameterType.equals("text")) && !sysInfoComponents.contains(parameterValue)) {
            System.out.println("Invalid configuration file: sysinfocomponent not recognized.");
            statusFlag = -1;
            RTExecutors.shutdownAll();
            
        }
    }

//    public static ArrayList<Node> getNodes() {
//        return guiNodes;
//    }
    
    
    public static int getStatusFlag() {
        return statusFlag;
    }
    
    public static List<String> getSysInfoComponents() {
        return sysInfoComponents;
    }
    
}
