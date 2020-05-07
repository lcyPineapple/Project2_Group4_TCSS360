/*
 * WindChill class for Weather Station TCSS 360 			
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

import GUI.sensorSuite1.controller.Controller;
import GUI.sensorSuite1.controller.DataPacket;
import GUI.sensorSuite1.sensors.AbstractSensor;


/**
 * @author Cade Reynoldson
 * @author Gregory Hablutzel
 * @version 1.0
 * This class represents the WindChill Rate calculation for the VantagePro2 Weather Station.
 * WindChill calculates the wind chill given the most recent temperature and humidity data.
 */

public class WindChill extends AbstractSensor<Integer> implements Runnable {
    
	private String sensorName = "WindChill";
	private String measurementDescription = "wind chill";
	private static final int MAX = 135; // 135 MPH
	private static final int MIN = -110; // -110 MPH
	
	TreeSet<DataPacket<Double>> tempDataSet;
	TreeSet<DataPacket<Integer>> windDataSet;
	
	/**
	 * Construct a WindChill object.
	 * @param file: input file.
	 * @param tempDataSet: temperature data.
	 * @param windDataSet: wind speed data.
	 */
	public WindChill(File file, TreeSet<DataPacket<Double>> tempDataSet, TreeSet<DataPacket<Integer>> windDataSet) {
		if (file == null) {
			throw new IllegalArgumentException("file cannot be null");
		}
		if (tempDataSet == null) {
			throw new IllegalArgumentException("input set cannot be null");
		}
		if (windDataSet == null) {
			throw new IllegalArgumentException("input set cannot be null");
		}
		 dataSet = new TreeSet<DataPacket<Integer>>();
		 this.file = file;
		 this.tempDataSet = tempDataSet;
		 this.windDataSet = windDataSet;
	}
	
    /**
     * Calculates the wind chill given a temperature (degrees farenheit) and a windspeed (miles per hour). 
     * @param temperature the temperature in farenheit. 
     * @param windspeed the windspeed in miles per hour.
     * @return the calculated wind chill.
     */
    public int calculateWindChill() {
    	double temperature = tempDataSet.last().getValue(); // get most recent temp value
	  	int windspeed = windDataSet.last().getValue(); // get most recent humidity value
        if (windspeed < 3) {
            throw new IllegalArgumentException("Windspeed cannot be <3.");
        }
        double windChill = 35.74 + (0.6215 * temperature) - (35.75 * Math.pow(windspeed, 0.16)) + (0.4275 * temperature * Math.pow(windspeed, 0.16));
        
        if(windChill > MAX) {
        	windChill = MAX;
        }
        if (windChill < MIN) {
        	windChill = MIN;
        }
        return (int) Math.round(windChill);
    }
    
	/**
	 * Method that executes in Runnable thread.
	 * Generates a new data point, adds it to the data set, and serializes last 60 seconds of data set to the Console.
	 */
	public void run() {
		super.run(dataSet, Controller.WINDCHILL_FILE, 0, calculateWindChill(), sensorName, measurementDescription);
	}
}