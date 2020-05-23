package tst;

import GUI.WeatherStationIntegrater;
import GUI.WirelessConsole;
import GUI.iss7.sensorSuite.SensorSuite;
import GUI.weatherStations.WeatherStation4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeatherStation4Test {

    WirelessConsole c;
    WeatherStationIntegrater in;
    WeatherStation4 ws4;
    SensorSuite ss;

    @BeforeEach
    void setUp() throws Exception {
        c = new WirelessConsole();
        in = new WeatherStationIntegrater(c);
        ws4 = new WeatherStation4(in);
        ss = new SensorSuite();
        ss.run();
        ws4.run();
    }

    @Test
    void testUpdateList() throws Exception {
        Method method = WeatherStation4.class.getDeclaredMethod("updateList");
        method.setAccessible(true);
        WeatherStation4 ws4c = new WeatherStation4(in);
        List<Double> setup = (List<Double>) method.invoke(ws4c);
        assertEquals(4, setup.size());
    }
    
    
    @Test
    void testSetUp() throws Exception, IllegalAccessException {
        Method method = WeatherStation4.class.getDeclaredMethod("setUp");
        method.setAccessible(true);
        boolean setup = (boolean) method.invoke(ws4);
        assertTrue(setup);
    }

}