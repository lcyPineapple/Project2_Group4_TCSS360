/*
 * RainSensor class for Weather Station TCSS 360 		
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

import GUI.sensorSuite1.controller.DataPacket;

/**
 * 
 * @author Gregory Hablutzel
 * @version 1.0
 * This class represents the rainfall sensor for the VantagePro2 Weather Station.
 * It generates rainfall data at a given interval in data packets, stores these in a
 *  tree set, and serializes it to the console.
 * 
 * Note:
 * Rainfall sensor is rated to a max accuracy of of 10.00"/hr.
 * To make computations easier and ensure we don't go over this value:
 * 10.00"/3600) = 0.002777"/sec max rate (per measurement)
 * (10.00"/3600) * 20 = 0.0.555"/20sec max rate (per measurement)
 * So we are having a max value of 0.05" per measurement (per 20 seconds).
 */


public class RainSensor extends AbstractSensor<Double> implements Runnable {
	private String sensorName = "Rain";
	private String measurementDescription = "rainfall";

	private static final int maxVal = 5; // 0.05"
	private static final int minVal = 0; // 0"

	/**
	 * Constructs a RainfallRate object.
	 * @param file: output file.
	 * @throws IllegalArgumentException if file is null
	 */
	public RainSensor(File file) {
		if (file == null) {
			throw new IllegalArgumentException("file cannot be null");
		}
		 dataSet = new TreeSet<DataPacket<Double>>();
		 this.file = file;
	}

	/**
	 * 
	 * @return
	 */
	public double getRain() {
		double randomNumber = (rand.nextInt(maxVal + 1 - minVal) + minVal) / 100.0;  
		return randomNumber;
	}
	
	/**
	 * Method that executes in Runnable thread.
	 * Generates a new data point, adds it to the data set, and serializes last 60 seconds of data set to the Console.
	 */
	public void run() {
		super.run(dataSet, file, 0.0, getRain(), sensorName, measurementDescription);
	}
}
