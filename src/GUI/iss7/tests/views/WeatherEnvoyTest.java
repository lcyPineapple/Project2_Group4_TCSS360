package GUI.iss7.tests.views;

import GUI.iss7.sensorSuite.SensorSuite;
import GUI.iss7.views.WeatherEnvoy;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests the weather envoy class.
 */
class WeatherEnvoyTest {
	
	/**
	 * Checks if the weather envoy class creates a readable file.
	 */
	@Test
	void testUpdate() throws Exception {
		final SensorSuite sensorSuite = new SensorSuite();
		final WeatherEnvoy envoy = new WeatherEnvoy(sensorSuite);
		envoy.update();
		assertTrue(Files.isReadable(Path.of("sensor-suite-data.txt")));
	}
}
