/*
 * DewPoint class for Weather Station TCSS 360 		
 *  
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.computations;

import java.io.File;	
import java.util.TreeSet;

import GUI.sensorSuite1.controller.DataPacket;
import GUI.sensorSuite1.sensors.AbstractSensor;

/**
 * 
 * @author Cade Reynoldson
 * @author Gregory Hablutzel
 * @version 1.0
 * This class represents the DewPoint calculation for the VantagePro2 Weather Station.
 * DewPoint receives temperature and relative humidity data, and calculates and serializes the dewpoint.
 *
 */

public class DewPoint extends AbstractSensor<Integer> implements Runnable {


	private String sensorName = "Dewpoint";
	private String measurementDescription = "dewpoint";
	private static final int MIN = -105; // -105F
	private static final int MAX = 130; // 130F
	TreeSet<DataPacket<Double>> tempDataSet;
	TreeSet<DataPacket<Integer>> humidityDataSet;

	/**
	 * 
	 * @param file, output file.
	 * @param tempDataSet, temperature data set.
	 * @param humidityDataSet, humidity data set.
	 * @throws IllegalArgumentException if file is null
	 * @throws IllegalArgumentException if tempDataSet is null
	 * @throws IllegalArgumentException if humidityDataaSet is null
	 */
	public DewPoint(File file, TreeSet<DataPacket<Double>> tempDataSet, TreeSet<DataPacket<Integer>> humidityDataSet) {
		if (file == null) {
			throw new IllegalArgumentException("file cannot be null");
		}
		if (tempDataSet == null) {
			throw new IllegalArgumentException("input set cannot be null");
		}
		if (humidityDataSet == null) {
			throw new IllegalArgumentException("input set cannot be null");
		}
		dataSet = new TreeSet<DataPacket<Integer>>();
		this.file = file;
		this.tempDataSet = tempDataSet;
		this.humidityDataSet = humidityDataSet;
	}


	/**
	 * Calculates the dew point given an air temperature and relative humidity.
	 * @return returns dew point.
	 */
	public int calculateDewPoint() {
		double airTemperature = tempDataSet.last().getValue(); // get most recent temperature value.
		int relativeHumidity = humidityDataSet.last().getValue(); // get most recent humidity value.
		double lnHumidity = Math.log(relativeHumidity / 100.0);
		double tMult = 17.27 * airTemperature;
		double tPlus = 237.3 + airTemperature;
		double dewPoint = (237.3 * (lnHumidity + (tMult / tPlus))) / (17.27 - (lnHumidity + (tMult / tPlus))); 
		if (dewPoint > MAX) {
			dewPoint = MAX;
		}
		if (dewPoint < MIN) {
			dewPoint = MIN;
		}
		return (int) Math.round(dewPoint);
	}

	/**
	 * Method that executes in Runnable thread.
	 * Generates a new data point, adds it to the data set, and serializes last 60 seconds of data set to the Console.
	 */
	public void run() {
		super.run(dataSet, file, 0, calculateDewPoint(), sensorName, measurementDescription);
	}
}