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

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeatherStation4Test {

    WirelessConsole c;
    WeatherStationIntegrater in;
    WeatherStation4 ws4;

    @BeforeEach
    void setUp() throws Exception {
        c = new WirelessConsole();
        in = new WeatherStationIntegrater(c);
        ws4 = new WeatherStation4(in);
        ws4.run();

        /*
         * Give the SensorSuite contained in WeatherStation4 a few seconds 
         * to write data to output4.txt
         * 
         * Otherwise, if an attempt is made to invoke the updateList()
         * method too soon, a NoSuchElementException will be thrown since
         * no information has been written to output4.txt.
         */
        var timeA = System.currentTimeMillis();
        while (System.currentTimeMillis() - timeA <= 3000) {};
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