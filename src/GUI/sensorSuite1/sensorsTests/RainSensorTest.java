/*
 * RainSensor Test class for Weather Station TCSS 360 		
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
import GUI.sensorSuite1.controller.DataPacket;
import GUI.sensorSuite1.sensors.RainSensor;

/**
 * 
 * @author Gregory Hablutzel
 * @version 1.0
 * This class tests the RainSensor class for the VantagePro2 Weather Station.
 */
class RainSensorTest {

	/*
	 * Ensures the rain sensor is generating the correct values each time.
	 */
	@Test
	void testGeneratedValues() {
		Double[] rainGeneratedValues = {0.01, 0.04 ,0.0};

		//ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		RainSensor rain = new RainSensor(Controller.RAINFALL_FILE);
		rain.run();
		rain.run();
		rain.run();
		
		int i = 0;
		for (DataPacket<Double> dp : rain.getSet()) {
			if (Double.compare(dp.getValue(), rainGeneratedValues[i]) != 0) {
				fail("values dont match");
			}
			i++;
		}
	}
	
	/*
	 * Triggers IllegalArgumentException for file parameter in constructor.
	 */
	@Test
	void testConstructorNullFileException() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		new RainSensor(null); 
		            });
	}
}
