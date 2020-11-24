
package ssmonitor.sysinfo;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;


public class SysInfo {
    
    public SysInfo(){
        
    }

    public static long getFreeMemory() {
        Runtime r = Runtime.getRuntime();
        var memory = r.freeMemory();
        return memory;
    }
    
    public static double getCpuLoad() {
        OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        
        return bean.getSystemCpuLoad();
    }
    
    

}


