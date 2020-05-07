/*
 * WindSensor Test class for Weather Station TCSS 360 			
 *  
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.sensorsTests;

import static org.junit.jupiter.api.Assertions.*;		

import org.junit.jupiter.api.Test;

import GUI.sensorSuite1.controller.Controller;
import GUI.sensorSuite1.sensors.WindSensor;

/**
 * 
 * @author Melinda Tran
 * @author Gregory Hablutzel
 * @version 1.0
 * This class tests the WindSensor class for the VantagePro2 Weather Station.
 */
class WindSensorTest {


	/*
	 * Ensures WindSensor is setting the correct max wind speed based on the given cable length of 230FT.
	 */
	@Test
	void testCalcMaxWindSpeed100() {
		WindSensor wind = new WindSensor(Controller.WINDSPEED_FILE, 230);
		int maxWindSpeed = wind.getMaxWindSpeed();
		assertEquals(100, maxWindSpeed);
	}

	/*
	 * Ensures WindSensor is setting the correct max wind speed based on the given cable length of 135FT.
	 */
	@Test
	void testCalcMaxWindSpeed135() {
		WindSensor wind = new WindSensor(Controller.WINDSPEED_FILE, 1);
		int maxWindSpeed = wind.getMaxWindSpeed();
		assertEquals(135, maxWindSpeed);
	}
	
	/*
	 * Ensures WindSensor is throwing an exception for the given cable length of -10FT.
	 */
	@Test
	void testCalcMaxWindSpeedLessThanOrEqualToZero() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		new WindSensor(Controller.WINDSPEED_FILE, -10);
		            });
	}
	
	/*
	 * Ensures WindSensor is throwing an exception for the given cable length of 240FT (greater than max allowed).
	 */
	@Test
	void testCalcMaxWindSpeedGreaterThan240() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		new WindSensor(Controller.WINDSPEED_FILE, 250);
		            });
	}
	
	/*
	 * Triggers IllegalArgumentException for file parameter in constructor.
	 */
	@Test
	void testConstructorNullFileException() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		new WindSensor(null, 50);

		            });
	}
}
