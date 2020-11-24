
package ssmonitor.file;
import ssmonitor.gui.GuiComponent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.scene.Node;


public class ParseConf {
    private String fileName;
    private static ArrayList<Node> guiNodes = new ArrayList<Node>();
    private static int statusFlag;

    public static void readConf(final String fileName) {
        try {
            File confFile = new File(fileName);
            Scanner reader = new Scanner(confFile);
            while (reader.hasNextLine()) {
                String confLine = reader.nextLine();
                statusFlag = parseLine(confLine);
                if (statusFlag == -1) {
                    System.out.println("Invalid configuration file.");
                }
            }

              reader.close();
            } catch (FileNotFoundException e) {
            System.out.println("Please choose a valid filename.");  
            statusFlag = -2;
            }
    }

    public static int parseLine(final String confLine) {

        if (confLine.startsWith("//")) {
            return 0;
        } else if (confLine.startsWith("$cpu_usage")) {
            System.out.println("hoi");
            GuiComponent cpu_usage = new GuiComponent(); 
            guiNodes.add(cpu_usage.lineChart());
            System.out.println("hei");
            return 1;
        } else {
            return -1;
        }
    }
    
    public static ArrayList<Node> getNodes() {
        System.out.println(guiNodes.size());
        return guiNodes;
    }
    
    
    public static int getStatusFlag() {
        return statusFlag;
    }
    
}
