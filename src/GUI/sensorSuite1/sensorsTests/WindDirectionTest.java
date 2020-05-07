/*
 * WindDirection Test class for Weather Station TCSS 360 		
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
import GUI.sensorSuite1.sensors.WindDirection;

/**
 * 
 * @author Melinda Tran
 * @author Gregory Hablutzel
 * @version 1.0
 * This class tests the WindDirection class for the VantagePro2 Weather Station.
 */
class WindDirectionTest {

	
	/*
	 * Ensures the WindDirection is generating the correct pseudo-random values.
	 */
	@Test
	void testGeneratedValues() {
		int[] generatedValues = {236, 341, 217};

		//ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		WindDirection windDirection = new WindDirection(Controller.WINDDIRECTION_FILE);
		windDirection.run();
		windDirection.run();
		windDirection.run();
		
		int i = 0;
		for (DataPacket<Integer> dp : windDirection.getSet()) {
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
		        		new WindDirection(null); 
		            });
	}
}
