package tst;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import GUI.WeatherStationIntegrater;
import GUI.weatherStations.WeatherStation4;
import GUI.WirelessConsole;
import GUI.iss7.sensorSuite.SensorSuite;
import GUI.iss7.views.ConsoleReceiver;

class WeatherStation4Test {
	
	WirelessConsole c ;
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
	void testSetUp() throws Exception, IllegalAccessException {
//		WirelessConsole wc = new WirelessConsole();
//		WeatherStationIntegrater in = new WeatherStationIntegrater(wc);
//		WeatherStation4 ws4 = new WeatherStation4(in);
//		ws4.run();
		Method method = WeatherStation4.class.getDeclaredMethod("setUp");
		boolean setup = (boolean) method.invoke(ws4);
		assertTrue(setup);
	}
	
	@Test
	void testUpdateList() throws Exception {
		Method method = WeatherStation4.class.getDeclaredMethod("updateList");
		WeatherStation4 ws4c = new WeatherStation4(in);
		List<Double> setup = (List<Double>) method.invoke(ws4c);
		assertEquals(4, setup.size());
	}

}