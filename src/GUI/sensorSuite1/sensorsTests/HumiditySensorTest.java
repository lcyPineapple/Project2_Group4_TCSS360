/*
 * HumiditySensor Test class for Weather Station TCSS 360 		
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
import GUI.sensorSuite1.sensors.HumiditySensor;

/**
 * 
 * @author Egor Maksimenka
 * @author Gregory Hablutzel
 * @version 1.0
 * This class tests the HumiditySensor class for the VantagePro2 Weather Station.
 */
class HumiditySensorTest {



	/*
	 * Ensures humidity is generated the correct values each time.
	 */
	@Test
	void testGeneratedValues() {
		int[] generatedValues = {56, 41, 77};

		//ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		HumiditySensor humidity = new HumiditySensor(Controller.HUMIDITY_FILE);
		humidity.run();
		humidity.run();
		humidity.run();
		
		int i = 0;
		for (DataPacket<Integer> dp : humidity.getSet()) {
			if (dp.getValue() != generatedValues[i]) {
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
		        		new HumiditySensor(null); 
		            });
	}
}
