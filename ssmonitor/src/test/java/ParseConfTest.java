


import org.junit.Before;
import org.junit.Test;
import ssmonitor.file.ParseConf;
import static org.junit.Assert.*;


public class ParseConfTest {
    
    public ParseConfTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    
    
    
    @Test
    public void invalidFileReturnsNegOne() {
        ParseConf.readConf("./src/main/resources/invalidExampleConf.conf");
        assertEquals(-1,ParseConf.getStatusFlag());
    }
    
    @Test
    public void invalidFileNameReturnsNegTwo() {
        ParseConf.readConf("./src/main/resources/inexistent.conf");
        assertEquals(-2,ParseConf.getStatusFlag());
    }
    
    @Test
    public void commentReturnsZero() {
        
        ParseConf.readConf("./src/main/resources/commentConf.conf");
        assertEquals(0,ParseConf.getStatusFlag());
    }
    
    
}
