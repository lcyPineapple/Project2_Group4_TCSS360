package tst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import GUI.Compass;

public class CompassTest {

    public static Compass testedCompass;
    
    @BeforeAll
    public void initializeCompass() {
        var k = new Compass();
    }
    
    @Test
    void test() {
        fail("Not yet implemented");
    }

}
