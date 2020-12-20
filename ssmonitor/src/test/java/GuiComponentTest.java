
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ssmonitor.file.ParseConf;
import ssmonitor.gui.GuiComponent;
import javafx.scene.Node;
/**
 *
 * @author ruby
 */
public class GuiComponentTest {
    
    public GuiComponentTest() {
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

    @Test
    public void textParameterCreatesNode() {
        GuiComponent.constructNode(null, "test", null, 0);
        assertTrue(GuiComponent.getLastNode() instanceof Node);
    }
    
    @Test
    public void sysInfoCallDeciderReturnsCorrectValue() {
        assertEquals(GuiComponent.sysInfoCallDecider("cpu_usage"), 1);
        assertEquals(GuiComponent.sysInfoCallDecider("drive_memory_percentage"), 2);
        assertEquals(GuiComponent.sysInfoCallDecider("drive_memory_total"), 3);
        assertEquals(GuiComponent.sysInfoCallDecider("ram_total"), 4);
        assertEquals(GuiComponent.sysInfoCallDecider("ram_free"), 5);
        assertEquals(GuiComponent.sysInfoCallDecider("ram_usage_percentage"), 6);
        
    }
    
}
