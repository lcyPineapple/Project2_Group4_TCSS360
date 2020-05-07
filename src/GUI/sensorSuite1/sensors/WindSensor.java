/*
 * WindSensor class for Weather Station TCSS 360 		
 *  
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.sensors;

import java.io.File;
import java.util.TreeSet;

import GUI.sensorSuite1.controller.Controller;
import GUI.sensorSuite1.controller.DataPacket;

/**
 * @author Melinda Tran
 * @author Gregory Hablutzel
 * @version 1.0
 * This class is the Wind Sensor class for the VantagePro2 Weather Station.
 * It generates wind speed data at a given interval in data packets, stores these in a
 *  tree set, and serializes it to the console.
 
    Cable Length, Anemometer: 40 feet (12 m) (included) 240 feet (73 m) (maximum recommended)
    Note: Maximum display-able wind decreases as the length of cable increases. At 140’ (42 m) of cable, 
    	the maximum wind speed displayed is 135 mph (60 m/s); at 240’ (73 m), the maximum wind speed 
    	displayed is 100 mph (34 m/s).
         - When calculating the max wind speed for our random input generator, we need to factor in the 
         cable length as a field.
 */
public class WindSensor extends AbstractSensor<Integer> implements Runnable{
	
	/** The type of sensor. **/
	private String sensorName = "Wind";
	
	/** The type of measurement for this sensor. **/
	private String measurementDescription = "wind speed";
	
	private int theCableLength;

	
	/** The max wind speed using cable length. **/
	private final int maxWindSpeed; // MPH
	
	/**
	 * Constructs a WindSensor object.
	 * @param file: output file.
	 * @param theCableLength: WindSensor cable length.
	 * @throws IllegalArgumentException if file is null
	 */
	public WindSensor(File file, int theCableLength) {
		if (file == null) {
			throw new IllegalArgumentException("file cannot be null");
		}
		 dataSet = new TreeSet<DataPacket<Integer>>();
		 this.file = file;
		this.theCableLength = theCableLength;
		maxWindSpeed = calcMaxWindSpeed();
	}
	
	/**
	 * calcMaxWindSpeed() calculate the max wind speed knowing that the 
	 * longer the cable length the shorter the max wind speed will be.
	 * 
	 * @param cableLength is the length of the anemometer.
	 * @return an illegal argument exception when  0 >= cableLength > 240 and a wind speed otherwise.
	 */
	public int calcMaxWindSpeed() {
		if (theCableLength <= 0) {
			throw new IllegalArgumentException("Cable Length cannot be <= 0 ft for wind sensor");
		} else if (theCableLength <= 140) {
			return 135;
		} else if (theCableLength <= 240) {
			return 100;
		} else {
			throw new IllegalArgumentException("Cable Length cannot be > 240ft for wind sensor");
		}
	}
	
	/**
	 * Generate a random integer for the wind speed with a range of [0, maxWindSpeed]
	 * @return returns the wind speed.
	 */
	public int getWindSpeed() {
		return rand.nextInt(maxWindSpeed + 1); // [0, maxWindSpeed]
	}
	/**
	 * Generate a random integer for the wind speed with a range of [0, maxWindSpeed]
	 * @return returns the max wind speed.
	 */
	public int getMaxWindSpeed() {
		return maxWindSpeed;
	}
	

	/**
	 * Method that executes in Runnable thread.
	 * Generates a new data point, adds it to the data set, and serializes last 60 seconds of data set to the Console.
	 */
	public void run() {
		super.run(dataSet, Controller.WINDSPEED_FILE, 0, getWindSpeed(), sensorName, measurementDescription);
	}
}
