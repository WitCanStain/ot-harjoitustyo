
package ssmonitor.sysinfo;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.io.File;


public class SysInfo {
    
    public SysInfo(){
        
    }

    public static long getFreeMemory() {
        Runtime r = Runtime.getRuntime();
        var memory = r.freeMemory();
        return memory;
    }
    
    public static double getDriveMemory() {
        File file = new File("/");
        double freeMemory = file.getFreeSpace();
        double totalMemory = file.getTotalSpace();
        System.out.println("free memory:" + freeMemory);
        return freeMemory / totalMemory;
    }
    
    public static double getCpuLoad() {
        OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        
        return bean.getSystemCpuLoad();
    }
    
    

}


