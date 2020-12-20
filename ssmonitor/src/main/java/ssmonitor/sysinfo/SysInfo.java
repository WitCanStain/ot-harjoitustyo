
package ssmonitor.sysinfo;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;


/**
 * This class contains methods to return system information.
 * @author ruby
 */
public class SysInfo {
    private static File file = new File("/");
    private static OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    
    public SysInfo(){
        
    }
    
    /**
     * Forks the request for system information to the methods responsible.
     * @param sysInfoCall The information the user wants to see
     * @return The fetched information
     */
    public static Object getSysInfo(int sysInfoCall) {
        
        switch (sysInfoCall) {
            case 1:
                return getCPULoad();
            case 2:
                return getDriveMemoryPc();
            case 3:
                return getDriveMemoryTotalGB();
            case 4:
                return getTotalRAMMemory();
            case 5:
                return getFreeRAMMemory();
            case 6:
                return getRAMUsePc();
        }
        return -1;
    }
    
    
    public static String getSystemProperty(String systemProperty) {
        
        return System.getProperty(systemProperty);
    }
    
    public static Set getSystemProperties() {
        return System.getProperties().keySet();
    }
    
    
    public static double getDriveMemoryTotalGB() {
        return file.getTotalSpace() / 1073741824.0; // 1024^3
    }
    
    public static double getDriveMemoryPc() {
        return file.getFreeSpace() / (double) file.getTotalSpace();
    }
    
    public static double getTotalRAMMemory() {
        try (final Stream<String> lines = Files.lines(Paths.get("/proc/meminfo"))) {
        List<String> list = lines.limit(2).collect(toList());
        try {
            int totalMemory = Integer.parseInt(list.get(0).split("\\s+")[1]);
            return totalMemory / 1073741824.0;
        } catch (NumberFormatException e) {
            System.out.println("Something went wrong!");
        }
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
        return -1.0;
    }
    
    public static double getFreeRAMMemory() {
        try (final Stream<String> lines = Files.lines(Paths.get("/proc/meminfo"))) {
        List<String> list = lines.limit(2).collect(toList());
        try {
            int freeMemory = Integer.parseInt(list.get(1).split("\\s+")[1]);
            return freeMemory / 1073741824.0;
        } catch (NumberFormatException e) {
            System.out.println("Something went wrong!");
        }
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
        return -1.0;
    }
    
    public static double getCPULoad() {
        return bean.getSystemCpuLoad();
    }
    
    public static double getRAMUsePc() {
        try (final Stream<String> lines = Files.lines(Paths.get("/proc/meminfo"))) {
        List<String> list = lines.limit(2).collect(toList());
        try {
            int totalMemory = Integer.parseInt(list.get(0).split("\\s+")[1]);
            int freeMemory = Integer.parseInt(list.get(1).split("\\s+")[1]);
            return 1 - freeMemory / (double) totalMemory;
        } catch (NumberFormatException e) {
            System.out.println("Something went wrong!");
        }
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
        return -1.0;
    }
}

