package sensorSuite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import views.ConsoleReceiver;
import views.Observer;
import views.WeatherEnvoy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class SensorSuiteTest {
    @Test
    void testAttachBasicSensors() {
        final SensorSuite sensorS = new SensorSuite();
        int sizeS = sensorS.getSensors().size();
        assertEquals(4,  sizeS);
    }

    @Test
    void testAddObserver() {
        SensorSuite sensorSuite = new SensorSuite();
        assertTrue(sensorSuite.getObservers().isEmpty());

        // test the first observer, Console Receiver
        Observer consoleReceiver = new ConsoleReceiver(sensorSuite);
        sensorSuite.addObserver(consoleReceiver);
        assertEquals(1, sensorSuite.getObservers().size());
        assertEquals(consoleReceiver, sensorSuite.getFirstObserver());

        // test the second observer, Weather Envoy
        Observer weatherEnvoy = new WeatherEnvoy(sensorSuite);
        sensorSuite.addObserver(weatherEnvoy);
        assertEquals(2, sensorSuite.getObservers().size());
        assertEquals(weatherEnvoy, sensorSuite.getSecondObserver());
    }

    @Test
    void testNotifyAllObservers() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
    	PrintStream ps = new PrintStream(os);
    	System.setOut(ps);

    	SensorSuite sensorSuite = new SensorSuite();
    	sensorSuite.addObserver(new ConsoleReceiver(sensorSuite));
    	sensorSuite.notifyAllObservers();

    	assertEquals("Wireless Vantage Pro2 Console Receivers\n\n", os.toString());
    }
}
