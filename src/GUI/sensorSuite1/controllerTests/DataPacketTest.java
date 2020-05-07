/*
 * DataPacket Test class for Weather Station TCSS 360 		
 *  
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.controllerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

import GUI.sensorSuite1.controller.DataPacket;

/**
 * 
 * @author Gregory Hablutzel
 * @version 1.0
 * This class tests the DatPacket class for the VantagePro2 Weather Station.
 */
class DataPacketTest {


	/**
	 * Ensures that the getSensor() method functions properly.
	 */
	@Test
	void testGetSensor() {
		ZonedDateTime eventTime = ZonedDateTime.now();
		DataPacket<Integer> dp = new DataPacket<Integer>(eventTime, "sensor", "measurementName", 0);
		assertTrue("sensor".equals(dp.getSensor()));
	}
	
	/**
	 * Ensures that the getEventTime() method functions properly.
	 */
	@Test
	void testGetEventTime() {
		ZonedDateTime eventTime = ZonedDateTime.now();
		DataPacket<Integer> dp = new DataPacket<Integer>(eventTime, "sensor", "measurementName", 0);
		assertTrue(eventTime.equals(dp.getEventTime()));
	}
	
	/**
	 * Ensures that an IllegalArgumentException is thrown if a null Event Time is passed.
	 */
	@Test
	void testConstructorNullEventTime() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		new DataPacket<Integer>(null, "sensor", "measurement", 0); 
		            });
	}
	
	/**
	 * Ensures that an IllegalArgumentException is thrown if a null sensor name is passed.
	 */
	@Test
	void testConstructorNullSensor() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		ZonedDateTime eventTime = ZonedDateTime.now();

		        		new DataPacket<Integer>(eventTime, null, "measurement", 0); 
		            });
	}
	

	/**
	 * Ensures that an IllegalArgumentException is thrown if a null measurement name is passed.
	 */
	@Test
	void testConstructorNullMeasurement() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		ZonedDateTime eventTime = ZonedDateTime.now();

		        		new DataPacket<Integer>(eventTime, "sensor", null, 0); 
		            });
	}
}
