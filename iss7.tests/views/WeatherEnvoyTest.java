package views;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import sensorSuite.SensorSuite;

/**
 * This class tests the weather envoy class.
 */
class WeatherEnvoyTest {
	
	/**
	 * Checks if the weather envoy class creates a readable file.
	 */
	@Test
	void testUpdate() {
		final SensorSuite sensorSuite = new SensorSuite();
		final WeatherEnvoy envoy = new WeatherEnvoy(sensorSuite);
		envoy.update();
		assertTrue(Files.isReadable(Path.of("sensor-suite-data.txt")));
	}
}
