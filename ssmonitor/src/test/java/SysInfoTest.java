
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ssmonitor.sysinfo.SysInfo;
import ssmonitor.sysinfo.RTExecutors;
import javafx.scene.chart.LineChart;
import ssmonitor.gui.RTNodes;
import ssmonitor.gui.Main;


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
        double value = SysInfo.getDriveMemory();
        assertTrue(value <= 1 && value >= 0);
    }
    
    @Test
    public void cpuLoadReturnsPercentage() {
        double value = SysInfo.getCpuLoad();
        assertTrue(value <= 1 && value >= 0);
    }

    /* this test cannot be run because it requires the JavaFX runtime to be
    started. One solution would be to force the runtime to start using JFXPanel,
    but I cannot find a way to import the relevant package. If I try to start
    the runtime by starting the application, it never exits the main method and
    so the test is never run. Therefore, I can't find a good way to test all 
    the functionality in the RTExecutors class.
    */   
//    @Test
//    public void executorThreadisCreated() {

//        LineChart lineChart = RTNodes.lineChart("cpu_usage");
//        assertTrue(RTExecutors.getExecutorServices().size() == 1);
//    }
    
    @Test
    public void inititalExecutorsNone() {
        assertTrue(RTExecutors.getExecutorServices().size() == 0);
    }
    
    
}
