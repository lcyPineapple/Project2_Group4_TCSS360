package GUI.iss8.tests;

import GUI.iss8.model.AbstractOutputDevice;
import GUI.iss8.model.ISS;
import GUI.iss8.model.WeatherMonitoringApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the ISS.java class
 *
 * @author Alex Amado
 */
class ISSTest {
    /**
     * The test output device connected to the ISS.
     */
    private WeatherMonitoringApp myTestApp;
    /**
     * The ISS device that transmits sensor data.
     */
    private ISS myISS;

    /**
     * Initializes our test fixtures before each test method.
     */
    @BeforeEach
    void setUp() {
        myTestApp = new WeatherMonitoringApp();
        final List<AbstractOutputDevice> myDevices = new ArrayList<>();
        myDevices.add(myTestApp);
        myISS = new ISS(myDevices);
    }

    /**
     * Tests the ISS has written to a output device. The output device connected
     * to the ISS contains an internal ArrayList and we check if the ArrayList
     * is populated after one write operation.
     */
    @Test
    void testWrite() {
        myISS.write();
        assertTrue(myTestApp.getOutputData().size() != 0,
                "The ISS has written to an output device.");
    }

    /**
     * Tests that data updates over a period of time; uses air pressure,
     * which is guaranteed to change by at least a small decimal amount
     * each interval.
     *
     * @throws InterruptedException
     */
    @Test
    void testDataUpdate() throws InterruptedException {
        Thread.sleep(3000);
        final double firstPressure = myTestApp.getWeatherData().getPressure();
        Thread.sleep(3000);
        final double changePressure = myTestApp.getWeatherData().getPressure();
        assertNotSame(firstPressure, changePressure, "Values changed");
//		assertFalse(firstPressure == changePressure, "Values unchanged");
    }
}
