package GUI.iss8.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import GUI.iss8.data.AirThermometer;
import GUI.iss8.data.Anemometer;
import GUI.iss8.data.Barometer;
import GUI.iss8.data.GroundThermometers;
import GUI.iss8.data.RelHumidity;
import GUI.iss8.data.WeatherData;


/**
 * This class tests the different classes' methods to ensure that each work as intended.
 * @author Dean Kelley
 * 
 * Added tests to meet 90% coverage. Added test for WeatherData.
 * @author Yolanda Xu
 * @version Spring 2020
 * 
 * Added more tests to acheive 100% coverage of Anemometer
 * @author Dean Kelley
 * @version Spring 2020
 */
public class SensorUnitTest {
	private final Anemometer myAnemometer = new Anemometer();
	private final AirThermometer myAirThermometer = new AirThermometer();
	private final Barometer myBarometer = new Barometer();
	private final GroundThermometers myGroundThermometers = new GroundThermometers();
	private final RelHumidity myHumidity = new RelHumidity();
	private final WeatherData myData = new WeatherData();
	
	/**
	 * Tests wind speed and direction sensor.
	 */
	@Test
	void anemometerTest() {
		myAnemometer.setWind(true);
		assertEquals("Results should match.", GUI.iss8.res.R.Integers.WINDSPEEDTEST, myAnemometer.getWindSpeed());
		assertSame("Results should match.", GUI.iss8.res.R.Strings.WINDDIRTEST, myAnemometer.getWindDirection());
		myAnemometer.setWind(false);
		myAnemometer.setWindSpeed(-10);
		myAnemometer.setWindAngle(11);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 0, myAnemometer.getWindIndex(), 0);
		myAnemometer.setWindAngle(-11);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 0, myAnemometer.getWindIndex());
		myAnemometer.setWindAngle(40);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 1, myAnemometer.getWindIndex());
		myAnemometer.setWindAngle(90);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 2, myAnemometer.getWindIndex());
		myAnemometer.setWindAngle(140);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 3, myAnemometer.getWindIndex());
		myAnemometer.setWindAngle(170);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 4, myAnemometer.getWindIndex());
		myAnemometer.setWindAngle(191);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 4, myAnemometer.getWindIndex());
		myAnemometer.setWindAngle(-11);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 0, myAnemometer.getWindIndex());
		myAnemometer.setWindAngle(-40);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 7, myAnemometer.getWindIndex());
		myAnemometer.setWindAngle(-90);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 6, myAnemometer.getWindIndex());
		myAnemometer.setWindAngle(-140);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 5, myAnemometer.getWindIndex());
		myAnemometer.setWindAngle(169);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 4, myAnemometer.getWindIndex());
		myAnemometer.setWindAngle(-169);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 4, myAnemometer.getWindIndex());
		myAnemometer.setWindAngle(-191);
		myAnemometer.setWind(false);
		assertEquals("Results should match.", 4, myAnemometer.getWindIndex());

	
	}
	
	/**
	 * Tests air temperature sensor.
	 */
	@Test
	void airTempTest() {
		myAirThermometer.setTemp(true);
		assertEquals("Results should match.", GUI.iss8.res.R.Integers.AIRGROUNDTEMPTEST, 
				myAirThermometer.getAirTemperature());
	}
	
	/**
	 * Tests air temperature sensor.
	 */
	@Test
	void groundTempTest() {
		myGroundThermometers.setTemp(true);
		assertEquals("Results should match.", GUI.iss8.res.R.Integers.AIRGROUNDTEMPTEST, 
				myGroundThermometers.getTemperature());
	}
	
	/**
	 * Tests barometer sensor
	 */
	@Test
	void pressureTest() {
		myBarometer.setPressure(true);
		assertEquals("Results should match.", GUI.iss8.res.R.Doubles.PRESSURETEST, myBarometer.
				getPressure(), 0);
		myBarometer.setPressureValue(GUI.iss8.res.R.Doubles.PRESSURETEST);
		assertEquals("Results should match.", GUI.iss8.res.R.Doubles.PRESSURETEST, myBarometer.
				getPressure(), 0);
	}
	
	/**
	 * Tests humidity sensor.
	 */
	@Test
	void humidityTest() {
		myHumidity.setHumidity(true);
		assertEquals(GUI.iss8.res.R.Integers.HUMIDITYTEST, myHumidity.getHumidity());
	}
	
	/**
	 * Tests wind speed and direction sensor.
	 */
	@Test
	void weatherDataTest() {
		assertTrue(myData.getAirTemperatures() >= -40 && myData.getAirTemperatures() <= 65);
		assertTrue(myData.getGroundThermometerTemperature() >= -40 && myData.getGroundThermometerTemperature() <= 65);
		assertTrue(myData.getWindSpeed() >= 0 && myData.getWindSpeed() <= 200);
		assertTrue(myData.getWindDirection().equals("N"));
		assertTrue(myData.getPressure() >= 16.0 && myData.getPressure() <= 32.5);
		assertTrue(myData.getRainfall() >= 0);
		assertTrue(myData.getHumidity() >= 0 && myData.getHumidity() <= 100);
		myData.updateData();
	}
	
	
//	@Test
//	void testExpectedException() {
//	 
//	  Assertions.assertThrows(NumberFormatException.class, () -> {
//	    Integer.parseInt("One");
//	  });
//	 
//	}
}
