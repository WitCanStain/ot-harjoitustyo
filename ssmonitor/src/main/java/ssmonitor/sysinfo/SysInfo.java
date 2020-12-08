
package ssmonitor.sysinfo;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.io.File;

/**
 * This class contains methods to return system information.
 * @author ruby
 */
public class SysInfo {
    private static File file = new File("/");
    
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
        }
        return -1;
    }
    
    
    public static String getSystemProperty(String systemProperty) {
        
        return System.getProperty(systemProperty);
    }
    
    
    public static double getDriveMemoryTotalGB() {
        
        double totalMemoryGB = file.getTotalSpace() / 1073741824.0; // 1024^3
        return totalMemoryGB;
    }
    
    public static double getDriveMemoryPc() {
        
        double freeMemory = file.getFreeSpace();
        double totalMemory = file.getTotalSpace();
        
        return freeMemory / totalMemory;
    }
    
    public static double getCPULoad() {
        OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        
        return bean.getSystemCpuLoad();
    }
    

}


