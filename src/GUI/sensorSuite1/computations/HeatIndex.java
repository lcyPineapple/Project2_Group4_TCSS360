/*
 * HeatIndex class for Weather Station TCSS 360
 *
 * Class: TCSS 360
 * Professor: Kivanç A. DINCER
 * Assignment: #1 Weather Station
 * Due Date: 4/19/20
 * Year: Spring 2020
 * School: UW-Tacoma
 */

package GUI.sensorSuite1.computations;

import GUI.sensorSuite1.controller.Controller;
import GUI.sensorSuite1.controller.DataPacket;
import GUI.sensorSuite1.sensors.AbstractSensor;

import java.io.File;
import java.util.TreeSet;

/**
 * @author Cade Reynoldson
 * @author Gregory Hablutzel
 * @version 1.0
 * This class represents the Heat Index calculation for the VantagePro2 Weather Station.
 * HeatIndex calculates the heat index given the most recent relative humidity and temperature measurements.
 */
public class HeatIndex extends AbstractSensor<Integer> implements Runnable {

    private static final int MIN = -40; // -40F
    private static final int MAX = 165; // -165F
    TreeSet<DataPacket<Double>> tempDataSet;
    TreeSet<DataPacket<Integer>> humidityDataSet;
    private String sensorName = "HeatIndex";
    private String measurementDescription = "heat index";

    /**
     * Constructs a HeatIndex object.
     *
     * @param file:            input file.
     * @param tempDataSet:     temperature data.
     * @param humidityDataSet: humidity data.
     * @throws IllegalArgumentException if file is null
     * @throws IllegalArgumentException if tempDataSet is null
     * @throws IllegalArgumentException if humidityDataaSet is null
     */
    public HeatIndex(File file, TreeSet<DataPacket<Double>> tempDataSet, TreeSet<DataPacket<Integer>> humidityDataSet) {
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
     * Calculates the heat index in cases that the temperature is greater than 80 degrees.
     *
     * @param relativeHumidity the relative humidity.
     * @param temperature      the temperature.
     * @return the heat index.
     */
    private static double regressionEquation(double relativeHumidity, double temperature) {
        double temperatureSquared = temperature * temperature; //Save the temperature squared
        double relativeHumiditySquared = relativeHumidity * relativeHumidity; //Save the RH squared
        double heatIndex =
                -42.379
                        + (2.04901523 * temperature)
                        + (10.14333127 * relativeHumidity)
                        - (0.22475541 * temperature * relativeHumidity)
                        - (0.00683783 * temperatureSquared)
                        - (0.05481717 * relativeHumiditySquared)
                        + (0.00122874 * temperatureSquared * relativeHumidity)
                        + (0.00085282 * temperature * relativeHumiditySquared)
                        - (0.00000199 * temperatureSquared * relativeHumiditySquared);
        if ((relativeHumidity < 13) && (80 <= temperature) && (temperature <= 112)) { //if the relative humidity is less than 13% and temperature is between 80 and 112 degrees farenheit, make an adjustment.
            double adjustment = ((13 - relativeHumidity) / 4) * Math.sqrt((17 - Math.abs(temperature - 95)) / 17);
            heatIndex -= adjustment;
        } else if ((relativeHumidity > 85) && (80 <= temperature) && (temperature <= 87)) { //if the relative humidity is greater than 85% and temp is between 80 and 87 degress, make an adjustment
            double adjustment = ((relativeHumidity - 85) / 10) * ((87 - temperature) / 5);
            heatIndex += adjustment;
        }
        return heatIndex;
    }

    /**
     * The simple equation for calculating the heat index.
     *
     * @param relativeHumidity the relative humidity.
     * @param temperature      the temperature.
     * @return the "simple" calculation of heat index.
     */
    private static double simpleEquation(double relativeHumidity, double temperature) {
        return 0.5 * (temperature + 61.0 + ((temperature - 68.0) * 1.2) + (relativeHumidity * 0.094));
    }

    /**
     * Calculates the heat index given the relative humidity and temperature (in fahrenheit).
     *
     * @param relativeHumidity the relative humidity. NOTE: for 80% humidity, plug in 80, not 0.80
     * @param temperature      the temperature.
     * @return the heat index.
     */

    public int calculateHeatIndex() {
        double temperature = tempDataSet.last().getValue(); // get most recent temp value
        int relativeHumidity = humidityDataSet.last().getValue(); // get most recent humidity value
        double heatIndex = simpleEquation(relativeHumidity, temperature);
        if (heatIndex >= 80.0) {
            heatIndex = regressionEquation(relativeHumidity, temperature);
        }
        if (heatIndex > MAX) {
            heatIndex = MAX;
        }
        if (heatIndex < MIN) {
            heatIndex = MIN;
        }
        return (int) Math.round(heatIndex);
    }

    /**
     * Method that executes in Runnable thread.
     * Generates a new data point, adds it to the data set, and serializes last 60 seconds of data set to the Console.
     */
    public void run() {
        super.run(dataSet, Controller.HEATINDEX_FILE, 0, calculateHeatIndex(), sensorName, measurementDescription);
    }
}