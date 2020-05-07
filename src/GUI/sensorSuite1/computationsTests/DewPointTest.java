/*
 * DewPoint Test class for Weather Station TCSS 360 		
 *  
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.computationsTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import GUI.sensorSuite1.computations.DewPoint;
import GUI.sensorSuite1.controller.Controller;
import GUI.sensorSuite1.controller.DataPacket;
import GUI.sensorSuite1.sensors.HumiditySensor;
import GUI.sensorSuite1.sensors.TemperatureSensor;


/**
 * 
 * @author Cade Reynoldson
 * @author Gregory Hablutzel
 * @version 1.0
 * This class tests the DewPoint class for the VantagePro2 Weather Station.
 */
class DewPointTest {

	/*
	 * Generates a DewPoint < -105, to check if its gets caught.
	 * Checking if dew point value gets set back to -105F.
	 */
	@Test
	void testDewpointLessThanNegative105() {
		
		TemperatureSensor temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
		HumiditySensor humidity = new HumiditySensor(Controller.HUMIDITY_FILE);
		DewPoint dewPoint = new DewPoint(Controller.DEWPOINT_FILE, temp.getSet(), humidity.getSet());

		ZonedDateTime eventTime = ZonedDateTime.now();
		DataPacket<Double> tempDP = new DataPacket<Double>(eventTime, "temp", "temp", -200.0); // -200.0F
		temp.getSet().add(tempDP);
		
		DataPacket<Integer> humidDP = new DataPacket<Integer>(eventTime, "humid", "humid", 100); // 100% humidity
		humidity.getSet().add(humidDP);
		dewPoint.run(); 	
		int dewPointVal = -105;
		assertEquals(dewPoint.getSet().last().getValue(), dewPointVal);
	}

	/*
	 * Generates a DewPoint over 130, to check if its gets caught, and set to 130.
	 * Checking if dew point value gets set back to 130F.
	 */
	@Test
	void testDewpointGreaterThan130() {
		
		TemperatureSensor temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
		HumiditySensor humidity = new HumiditySensor(Controller.HUMIDITY_FILE);
		DewPoint dewPoint = new DewPoint(Controller.DEWPOINT_FILE, temp.getSet(), humidity.getSet());

		ZonedDateTime eventTime = ZonedDateTime.now();
		DataPacket<Double> tempDP = new DataPacket<Double>(eventTime, "temp", "temp", 200.0); // 200.0F
		temp.getSet().add(tempDP);
		
		DataPacket<Integer> humidDP = new DataPacket<Integer>(eventTime, "humid", "humid", 100); // 100% humidity
		humidity.getSet().add(humidDP);
		dewPoint.run(); 
		int dewPointVal = 130; 
		assertEquals(dewPoint.getSet().last().getValue(), dewPointVal);

	}
	
	/*
	 * Generates a temperature of 80.1F, humidity of 56%, and dew point of 66F.
	 * Ensure dew point is generating the right value. 
	 */
	@Test
	void testGeneratedValues() {	
		TemperatureSensor temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
		HumiditySensor humidity = new HumiditySensor(Controller.HUMIDITY_FILE);
		DewPoint dewPoint = new DewPoint(Controller.DEWPOINT_FILE, temp.getSet(), humidity.getSet());

		temp.run(); // 80.1
		humidity.run(); // 56
		dewPoint.run(); // 66
		int dewPointVal = 66; 
		assertEquals(dewPoint.getSet().last().getValue(), dewPointVal);

	
	}
	
	/*
	 * Triggers IllegalArgumentException for file parameter in constructor.
	 */
	@Test
	void testConstructorNullFileException() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		new DewPoint(null, new TreeSet<DataPacket<Double>>(), new TreeSet<DataPacket<Integer>>()); 
		            });
	}
	
	/*
	 * Triggers IllegalArgumentException for first data set parameter in constructor.
	 */
	@Test
	void testConstructorNullSetInputException1() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		new DewPoint(new File("test.txt"), null, new TreeSet<DataPacket<Integer>>()); 
		            });
	}
	
	/*
	 * Triggers IllegalArgumentException for second data set parameter in constructor.
	 */
	@Test
	void testConstructorNullSetInputException2() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		new DewPoint(new File("test.txt"), new TreeSet<DataPacket<Double>>(), null); 
		            });
	}
}
