
import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ssmonitor.sysinfo.SysInfo;
import ssmonitor.sysinfo.RTExecutors;


public class SysInfoTest {
    
    public SysInfoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void driveMemoryReturnsValue() {
        double value = SysInfo.getDriveMemoryPc();
        assertTrue(value <= 1 && value >= 0);
    }
    
    @Test
    public void cpuLoadReturnsPercentage() {
        double value = SysInfo.getCPULoad();
        assertTrue(value <= 1 && value >= 0);
    }
    
    @Test
    public void freeRAMLessThanTotalRAM() {
        assertTrue(SysInfo.getFreeRAMMemory()<SysInfo.getTotalRAMMemory());
    }
    
    @Test
    public void getSysInfoReturnsValue() {
        Double value = (double) SysInfo.getSysInfo(1);
        assertTrue(!Objects.isNull(value));
        value = (double) SysInfo.getSysInfo(2);
        assertTrue(!Objects.isNull(value));
        value = (double) SysInfo.getSysInfo(3);
        assertTrue(!Objects.isNull(value));
        value = (double) SysInfo.getSysInfo(4);
        assertTrue(!Objects.isNull(value));
        value = (double) SysInfo.getSysInfo(5);
        assertTrue(!Objects.isNull(value));
        value = (double) SysInfo.getSysInfo(6);
        assertTrue(!Objects.isNull(value));
    }
    
    @Test
    public void getDriveMemoryGBReturnsValueGreaterThanOne() {
        assertTrue(SysInfo.getDriveMemoryTotalGB()>0);
    }
    
    @Test
    public void getDriveMemoryPcReturnsPercentage() {
        assertTrue(SysInfo.getDriveMemoryPc()>=0 && SysInfo.getDriveMemoryPc()<=1);
    }
    
    @Test
    public void inititalExecutorsNone() {
        assertTrue(RTExecutors.getExecutorServices().isEmpty());
    }
    
    
}
