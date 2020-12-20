


import ssmonitor.file.ParseConf;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class ParseConfTest {
    
    public ParseConfTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    
    
    
    
   

//    @Test
//    public void confLineCreatesNode() {
//        ParseConf.parseLine("$cpu_usage|presentation_type=linechart", 1);
//        assertEquals(ParseConf.getNodes().size(), 1);
//    }

    
    
    @Test
    public void invalidConfigurationReturnsNegOne() {
        ParseConf.parseLine("invalid conf", 1);
        assertEquals(-1,ParseConf.getStatusFlag());
    }
    @Test
    public void invalidFileNameReturnsNegTwo() {
        ParseConf.readConf("./src/main/resources/inexistent.conf",1);
        assertEquals(-2,ParseConf.getStatusFlag());
    }
    
}

