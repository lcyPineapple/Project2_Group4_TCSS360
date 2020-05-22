package tst;

import GUI.WirelessConsole;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusBarTest {

    @Test
    void testCoolAndNotHumid() throws Exception {
        Method method = WirelessConsole.class.getDeclaredMethod("updateBottomStatusPanel", double.class, double.class);
        WirelessConsole wc = new WirelessConsole();
        String st = (String) method.invoke(wc, 0.0, 0.0);
        assertEquals("Cool and Not Humid. Temperature is 0.0 degrees.", st);
    }

    @Test
    void testWarmAndHumid() throws Exception {
        Method method = WirelessConsole.class.getDeclaredMethod("updateBottomStatusPanel", double.class, double.class);
        WirelessConsole wc = new WirelessConsole();
        String st = (String) method.invoke(wc, 60.0, 80.0);
        assertEquals("Warm and Humid. Temperature is 80.0 degrees.", st);
    }

    @Test
    void testHotAndVeryHumid() throws Exception {
        Method method = WirelessConsole.class.getDeclaredMethod("updateBottomStatusPanel", double.class, double.class);
        WirelessConsole wc = new WirelessConsole();
        String st = (String) method.invoke(wc, 70.0, 90.0);
        assertEquals("Hot and Very Humid. Temperature is 90.0 degrees.", st);
    }

}
