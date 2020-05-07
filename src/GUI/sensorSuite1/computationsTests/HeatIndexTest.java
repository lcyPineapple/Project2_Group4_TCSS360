/*
 * HeatIndex Test class for Weather Station TCSS 360 		
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

import GUI.sensorSuite1.computations.HeatIndex;
import GUI.sensorSuite1.controller.Controller;
import GUI.sensorSuite1.controller.DataPacket;
import GUI.sensorSuite1.sensors.HumiditySensor;
import GUI.sensorSuite1.sensors.TemperatureSensor;

/**
 * 
 * @author Cade Reynoldson
 * @author Gregory Hablutzel
 * @version 1.0
 * This class tests the Heat Index class for the VantagePro2 Weather Station.
 */
class HeatIndexTest {

	/*
	 * Generates a Heat Index less than -40.
	 * Checks to see if the value gets set to -40.
	 */
	@Test
	void testHeatIndexLessThanNegative40() {
		
		TemperatureSensor temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
		HumiditySensor humidity = new HumiditySensor(Controller.HUMIDITY_FILE);

		ZonedDateTime eventTime = ZonedDateTime.now();		
		DataPacket<Integer> humidDP = new DataPacket<Integer>(eventTime, "humid", "humid", 50); // 100% humidity
		humidity.getSet().add(humidDP);
		DataPacket<Double> tempDP = new DataPacket<Double>(eventTime, "temp", "temp", -100.0); // -200.0F
		temp.getSet().add(tempDP);
		HeatIndex heatIndex = new HeatIndex(Controller.HEATINDEX_FILE, temp.getSet(), humidity.getSet());
		heatIndex.run(); 
		int heatIndexVal = -40; 
		assertEquals(heatIndex.getSet().last().getValue(), heatIndexVal);

	
	}

	/*
	 * Generates a Heat Index over 165.
	 * Checks to see if the value gets set to 165.
	 */
	@Test
	void testDewpointGreaterThan165() {
		
		TemperatureSensor temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
		HumiditySensor humidity = new HumiditySensor(Controller.HUMIDITY_FILE);

		ZonedDateTime eventTime = ZonedDateTime.now();
		DataPacket<Integer> humidDP = new DataPacket<Integer>(eventTime, "humid", "humid", 50); // 100% humidity
		humidity.getSet().add(humidDP);
		
		DataPacket<Double> tempDP = new DataPacket<Double>(eventTime, "temp", "temp", 200.0); // 200.0F
		temp.getSet().add(tempDP);
		HeatIndex heatIndex = new HeatIndex(Controller.HEATINDEX_FILE, temp.getSet(), humidity.getSet());
		heatIndex.run(); 
		int heatIndexVal = 165;
		assertEquals(heatIndex.getSet().last().getValue(), heatIndexVal);

	}
	
	/*
	 * Inputs a humidity of 50%, temperature of 60F, checks to see if correct Heat Index of 58 is returned.
	 */
	@Test
	void testNormalValue() {	
		TemperatureSensor temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
		HumiditySensor humidity = new HumiditySensor(Controller.HUMIDITY_FILE);

		ZonedDateTime eventTime = ZonedDateTime.now();
		
		DataPacket<Integer> humidDP = new DataPacket<Integer>(eventTime, "humid", "humid", 50); // 100% humidity
		humidity.getSet().add(humidDP);
		DataPacket<Double> tempDP = new DataPacket<Double>(eventTime, "temp", "temp", 60.0); // 200.0F
		temp.getSet().add(tempDP);
		HeatIndex heatIndex = new HeatIndex(Controller.HEATINDEX_FILE, temp.getSet(), humidity.getSet());
		heatIndex.run();
		int heatIndexVal = 58; 
		assertEquals(heatIndex.getSet().last().getValue(), heatIndexVal);
	}
	
	/*
	 * Triggers Regression Equations "if" case using a temperature of 90F, humidity of 12%.
	 * Checks to see if correct 66F Heat Index is generated.
	 */
	@Test
	void testRegressionIfCase() {	
		TemperatureSensor temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
		HumiditySensor humidity = new HumiditySensor(Controller.HUMIDITY_FILE);

		ZonedDateTime eventTime = ZonedDateTime.now();
		
		DataPacket<Integer> humidDP = new DataPacket<Integer>(eventTime, "humid", "humid", 12); // 100% humidity
		humidity.getSet().add(humidDP);
		DataPacket<Double> tempDP = new DataPacket<Double>(eventTime, "temp", "temp", 90.0); // 200.0F
		temp.getSet().add(tempDP);
		HeatIndex heatIndex = new HeatIndex(Controller.HEATINDEX_FILE, temp.getSet(), humidity.getSet());
		heatIndex.run(); 
		int heatIndexVal = 86; 
		assertEquals(heatIndex.getSet().last().getValue(), heatIndexVal);

	
	}
	
	/*
	 * Triggers Regression Equations "elseif" case using a temperature of 85F, humidity of 90%.
	 * Checks to see if correct 102F Heat Index is generated.
	 */
	@Test
	void testRegressionElseIfCase() {	
		TemperatureSensor temp = new TemperatureSensor(Controller.TEMPERATURE_FILE);
		HumiditySensor humidity = new HumiditySensor(Controller.HUMIDITY_FILE);

		ZonedDateTime eventTime = ZonedDateTime.now();
		
		DataPacket<Integer> humidDP = new DataPacket<Integer>(eventTime, "humid", "humid", 90); // 100% humidity
		humidity.getSet().add(humidDP);
		DataPacket<Double> tempDP = new DataPacket<Double>(eventTime, "temp", "temp", 85.0); // 200.0F
		temp.getSet().add(tempDP);
		HeatIndex heatIndex = new HeatIndex(Controller.HEATINDEX_FILE, temp.getSet(), humidity.getSet());
		heatIndex.run();
		int heatIndexVal = 102; 
		assertEquals(heatIndex.getSet().last().getValue(), heatIndexVal);

	
	}
	
	/*
	 * Triggers IllegalArgumentException for file parameter in constructor.
	 */
	@Test
	void testConstructorNullFileException() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		new HeatIndex(null, new TreeSet<DataPacket<Double>>(), new TreeSet<DataPacket<Integer>>()); 
		            });
	}
	
	/*
	 * Triggers IllegalArgumentException if first data set parameter is null in constructor.
	 */
	@Test
	void testConstructorNullSetInputException1() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		new HeatIndex(new File("test.txt"), null, new TreeSet<DataPacket<Integer>>()); 
		            });
	}
	
	/*
	 * Triggers IllegalArgumentException if second data set parameter is null in constructor.
	 */
	@Test
	void testConstructorNullSetInputException2() {
		  assertThrows(IllegalArgumentException.class,
		            ()->{
		        		new HeatIndex(new File("test.txt"), new TreeSet<DataPacket<Double>>(), null); 
		            });
	}
}
