package weatherstation6.test;/*
 * A test case demonstrating the warning function of the
 * SolarRadiation class.
 */

import org.junit.Assert;
import org.junit.Test;

import weatherstation6.src.WeatherData.Sensor;
import weatherstation6.src.WeatherData.SolarRadiation;
/**
 * @author Paras Sharma
 *
 */
public class SolarRadiationTest {
	/**
	 * Test method for {@link WeatherData.UltraViolet#alarmWarning(double)}.
	 */
	@Test
	public void testAlarmWarning() {
		String expected = "WARNING!, Higher Radiations than usual";
		SolarRadiation test = new SolarRadiation(Sensor.OUTSIDE);
		String actual = test.alarmWarning(200);
		Assert.assertEquals(expected, actual);
	}

}

